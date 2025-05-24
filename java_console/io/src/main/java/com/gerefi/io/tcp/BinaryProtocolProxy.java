package com.gerefi.io.tcp;

import com.devexperts.logging.Logging;
import com.gerefi.CompatibleFunction;
import com.gerefi.Listener;
import com.gerefi.Timeouts;
import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.binaryprotocol.IncomingDataBuffer;
import com.gerefi.binaryprotocol.IoHelper;
import com.gerefi.config.generated.Integration;
import com.gerefi.io.IoStream;
import com.gerefi.proxy.NetworkConnector;
import com.gerefi.ui.StatusConsumer;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static com.devexperts.logging.Logging.getLogging;
import static com.gerefi.config.generated.Integration.TS_PROTOCOL;
import static com.gerefi.core.FileUtil.close;

/**
 * Takes any IoStream and exposes it as local TCP/IP server socket
 */
public class BinaryProtocolProxy {
    private static final Logging log = getLogging(BinaryProtocolProxy.class);
    /**
     * we expect server to at least request output channels once in a while
     * it could be a while between user connecting authenticator and actually connecting application to authenticator
     * See Backend#APPLICATION_INACTIVITY_TIMEOUT
     */
    public static final int USER_IO_TIMEOUT = 10 * Timeouts.MINUTE;

    /**
     * @return starts a thread and returns a reference to ServerSocketReference
     */
    public static ServerSocketReference createProxy(IoStream targetEcuSocket, int serverProxyPort, ClientApplicationActivityListener clientApplicationActivityListener, StatusConsumer statusConsumer) throws IOException {
        CompatibleFunction<Socket, Runnable> clientSocketRunnableFactory = clientSocket -> () -> {
            TcpIoStream clientStream = null;
            try {
                clientStream = new TcpIoStream("[[proxy]] ", clientSocket);
                runProxy(targetEcuSocket, clientStream, clientApplicationActivityListener, USER_IO_TIMEOUT);
            } catch (IOException e) {
                statusConsumer.logLine("ERROR BinaryProtocolProxy::run " + e);
                close(clientStream);
            }
        };
        return BinaryProtocolServer.tcpServerSocket(serverProxyPort, "proxy", clientSocketRunnableFactory, Listener.empty(), statusConsumer);
    }

    public interface ClientApplicationActivityListener {
        ClientApplicationActivityListener VOID = (BinaryProtocolServer.Packet clientRequest) -> {
        };

        void onActivity(BinaryProtocolServer.Packet clientRequest);
    }

    public static void runProxy(IoStream targetEcu, IoStream clientStream, ClientApplicationActivityListener listener, int timeoutMs) throws IOException {
        /*
         * Each client socket is running on it's own thread
         */
        while (!targetEcu.isClosed()) {
            byte firstByte = clientStream.getDataBuffer().readByte(timeoutMs);
            if (firstByte == Integration.TS_GET_PROTOCOL_VERSION_COMMAND_F) {
                log.info("Responding to GET_PROTOCOL_VERSION with " + TS_PROTOCOL);
                clientStream.write(TS_PROTOCOL.getBytes());
                clientStream.flush();
                continue;
            }
            BinaryProtocolServer.Packet clientRequest = readClientRequest(clientStream.getDataBuffer(), firstByte);
            byte[] packet = clientRequest.getPacket();
            if (packet.length > 1 && packet[0] == Integration.TS_ONLINE_PROTOCOL && packet[1] == NetworkConnector.DISCONNECT)
                throw new IOException("User requested disconnect");
            listener.onActivity(clientRequest);

            /**
             * Two reasons for synchronization:
             * - we run gauge poking thread until TunerStudio connects
             * - technically there could be two parallel connections to local application port
             */
            BinaryProtocolServer.Packet controllerResponse;
            synchronized (targetEcu) {
                sendToTarget(targetEcu, clientRequest);
                controllerResponse = targetEcu.readPacket();
            }

            if (log.debugEnabled())
                log.debug("Relaying controller response length=" + controllerResponse.getPacket().length);
            clientStream.sendPacket(controllerResponse);
        }
    }

    @NotNull
    private static BinaryProtocolServer.Packet readClientRequest(IncomingDataBuffer in, byte firstByte) throws IOException {
        byte secondByte = in.readByte();
        int length = IoHelper.getInt(firstByte, secondByte);

        return BinaryProtocolServer.readPromisedBytes(in, length);
    }

    private static void sendToTarget(IoStream targetOutputStream, BinaryProtocolServer.Packet packet) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.getPacket()));
        byte command = (byte) dis.read();

        if (log.debugEnabled())
            log.debug("Relaying client command " + BinaryProtocol.findCommand(command));
        // sending proxied packet to controller
        targetOutputStream.sendPacket(packet);
    }
}
