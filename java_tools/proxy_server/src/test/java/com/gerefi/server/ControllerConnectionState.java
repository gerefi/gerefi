package com.gerefi.server;

import com.devexperts.logging.Logging;
import com.gerefi.config.generated.Integration;
import com.gerefi.config.generated.VariableRegistryValues;
import com.gerefi.core.SignatureHelper;
import com.gerefi.auth.AuthTokenUtil;
import com.gerefi.binaryprotocol.IncomingDataBuffer;
import com.gerefi.core.Pair;
import com.gerefi.core.SensorsHolder;
import com.gerefi.io.IoStream;
import com.gerefi.io.commands.GetOutputsCommandBrokenHelper;
import com.gerefi.io.commands.HelloCommand;
import com.gerefi.io.tcp.TcpIoStream;
import com.gerefi.core.FileUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.Socket;

import static com.devexperts.logging.Logging.getLogging;

public class ControllerConnectionState {
    private static final Logging log = getLogging(ControllerConnectionState.class);
    private final Socket clientSocket;
    private final UserDetailsResolver userDetailsResolver;

    private boolean isClosed;
    private IoStream stream;
    private IncomingDataBuffer incomingData;
    /**
     * Data from controller
     */
    private SessionDetails sessionDetails;
    /**
     * user info from gerEFI database based on auth token
     */
    private UserDetails userDetails;
    private ControllerKey controllerKey;

    private final TwoKindSemaphore twoKindSemaphore = new TwoKindSemaphore();
    private final SensorsHolder sensorsHolder = new SensorsHolder();
    private final Birthday birthday = new Birthday();
    private int outputRoundAroundDuration;

    public ControllerConnectionState(Socket clientSocket, UserDetailsResolver userDetailsResolver) {
        this.clientSocket = clientSocket;
        this.userDetailsResolver = userDetailsResolver;
        try {
            stream = new TcpIoStream("[backend-controller connector] ", clientSocket);
            incomingData = stream.getDataBuffer();
        } catch (IOException e) {
            close();
        }
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public int getOutputRoundAroundDuration() {
        return outputRoundAroundDuration;
    }

    public IoStream getStream() {
        return stream;
    }

    public ControllerKey getControllerKey() {
        return controllerKey;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void close() {
        isClosed = true;
        FileUtil.close(clientSocket);
    }

    @Override
    public String toString() {
        return "ControllerConnectionState{" +
                "userDetails=" + userDetails +
                ", sessionDetails=" + sessionDetails +
                ", controllerKey=" + controllerKey +
                ", isClosed=" + isClosed +
                ", twoKindSemaphore=" + twoKindSemaphore +
                '}';
    }

    public void requestControllerInfo() throws IOException {
        HelloCommand.send(stream);
        String jsonString = HelloCommand.getHelloResponse(incomingData);
        if (jsonString == null) {
            throw new IOException("Invalid HELLO response");
        }
        sessionDetails = SessionDetails.valueOf(jsonString);
        if (!AuthTokenUtil.isToken(sessionDetails.getAuthToken())) {
            throw new IOException("Invalid token in " + jsonString);
        }

        log.info(sessionDetails.getAuthToken() + " New client: " + sessionDetails.getControllerInfo());
        userDetails = userDetailsResolver.apply(sessionDetails.getAuthToken());
        if (userDetails == null) {
            throw new IOException("Unable to resolve " + sessionDetails.getAuthToken());
        }
        Pair<String, String> p = SignatureHelper.getUrl(sessionDetails.getControllerInfo().getSignature());
        if (p == null)
            throw new IOException("Invalid signature response");
//        todo: revisit https://github.com/gerefi/gerefi/issues/4462 if ever uncommenting
//        String localFileName = SignatureHelper.downloadIfNotAvailable(p);
//        if (localFileName == null)
//            throw new IOException("Unable to download " + p.second + " from " + p.first);
//        iniFileModel.readIniFile(localFileName);

        controllerKey = new ControllerKey(userDetails.getUserId(), sessionDetails.getControllerInfo());
        log.info("User " + userDetails);
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public SessionDetails getSessionDetails() {
        return sessionDetails;
    }

    public void getOutputs() throws IOException {
        // TODO: why is this logic duplicated from BinaryProtocol?
        byte[] commandPacket = new byte[5];
        commandPacket[0] = Integration.TS_OUTPUT_COMMAND;
        System.arraycopy(GetOutputsCommandBrokenHelper.createRequest(), 0, commandPacket, 1, 4);

        long start = System.currentTimeMillis();
        stream.sendPacket(commandPacket);

        byte[] packet = incomingData.getPacket("msg");
        outputRoundAroundDuration = (int) (System.currentTimeMillis() - start);
        if (packet == null)
            throw new IOException("getOutputs: No response");
        if (packet.length != 1 + VariableRegistryValues.TS_TOTAL_OUTPUT_SIZE)
            throw new IOException("getOutputs: unexpected package length " + packet.length);
        sensorsHolder.grabSensorValues(packet);
    }

    @NotNull
    public TwoKindSemaphore getTwoKindSemaphore() {
        return twoKindSemaphore;
    }

    @NotNull
    public SensorsHolder getSensorsHolder() {
        return sensorsHolder;
    }

    public void grabOutputs(Backend backend) {
        try {
            getOutputs();
        } catch (IOException e) {
            // todo: this is currently not covered by a unit test
            log.error("grabOutputs " + this, e);
            backend.close(this);
        }
    }

    public void invokeOnlineCommand(byte command) throws IOException {
        byte[] packet = new byte[2];
        packet[0] = Integration.TS_ONLINE_PROTOCOL;
        packet[1] = command;
        stream.sendPacket(packet);
    }
}
