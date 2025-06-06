package com.gerefi.autodetect;

import com.devexperts.logging.Logging;
import com.gerefi.binaryprotocol.IncomingDataBuffer;
import com.gerefi.config.generated.Integration;
import com.gerefi.core.net.ConnectionAndMeta;
import com.gerefi.io.IoStream;
import com.gerefi.io.commands.HelloCommand;
import com.gerefi.io.serial.BufferedSerialIoStream;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static com.gerefi.binaryprotocol.IoHelper.checkResponseCode;

public class SerialAutoChecker {
    private final static Logging log = Logging.getLogging(SerialAutoChecker.class);
    private final String serialPort;
    private final CountDownLatch portFound;

    public SerialAutoChecker(String serialPort, CountDownLatch portFound) {
        this.serialPort = serialPort;
        this.portFound = portFound;
    }

    /**
     * @return ECU signature from specified stream
     */
    public static String checkResponse(IoStream stream, Function<CallbackContext, Void> callback) {
        if (stream == null)
            return null;
//        if (mode == PortDetector.DetectorMode.DETECT_ELM327) {
//            if (Elm327Connector.checkConnection(serialPort, stream)) {
//                // todo: this method is supposed to return signature not serial port!
//                return serialPort;
//            }
//            return null;
//        }
        IncomingDataBuffer incomingData = stream.getDataBuffer();
        try {
            HelloCommand.send(stream);
            byte[] response = incomingData.getPacket("auto detect");
            if (!checkResponseCode(response))
                return null;
            String signature = new String(response, 1, response.length - 1);
            if (!isSignatureWithValidPrefix(signature)) {
                return null;
            }
            log.info("Got signature=" + signature + " from " + stream);
            if (callback != null) {
                callback.apply(new CallbackContext(stream, signature));
            }
            return signature;
        } catch (IOException ignore) {
            return null;
        }
    }

    private static boolean isSignatureWithValidPrefix(String signature) {
        if (signature.startsWith(Integration.PROTOCOL_SIGNATURE_PREFIX))
            return true;
        String signatureWhiteLabel = ConnectionAndMeta.getSignatureWhiteLabel();
        return signatureWhiteLabel != null && signature.startsWith(signatureWhiteLabel + " ");
    }

    public void openAndCheckResponse(AtomicReference<AutoDetectResult> result, Function<CallbackContext, Void> callback) {
        String signature;
        // java 101: just a reminder that try-with syntax would take care of closing stream and that's important here!
        try (IoStream stream = getStreamByMode()) {
            signature = checkResponse(stream, callback);
        }
        if (signature != null) {
            /**
             * propagating result after closing the port so that it could be used right away
             */
            AutoDetectResult value = new AutoDetectResult(serialPort, signature);
            log.info("Propagating " + value);
            result.set(value);
            portFound.countDown();
        }
    }

    @Nullable
    private IoStream getStreamByMode() {
        return BufferedSerialIoStream.openPort(serialPort);
    }

    public static class CallbackContext {
        private final IoStream stream;
        private final String signature;

        public CallbackContext(IoStream stream, String signature) {
            this.stream = stream;
            this.signature = signature;
        }

        public String getSignature() {
            return signature;
        }

        public IoStream getStream() {
            return stream;
        }
    }

    public static class AutoDetectResult {

        private final String serialPort;
        private final String signature;

        public AutoDetectResult(String serialPort, String signature) {
            this.serialPort = serialPort;
            this.signature = signature;
        }

        @Nullable
        public String getSerialPort() {
            return serialPort;
        }

        @Nullable
        public String getSignature() {
            return signature;
        }

        @Override
        public String toString() {
            return "AutoDetectResult{" +
                "serialPort='" + serialPort + '\'' +
                ", signature='" + signature + '\'' +
                '}';
        }
    }
}
