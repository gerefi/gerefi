package com.gerefi;

import com.devexperts.logging.Logging;
import com.opensr5.ConfigurationImage;
import com.opensr5.ini.field.ScalarIniField;
import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.binaryprotocol.BinaryProtocolState;
import com.gerefi.config.Field;
import com.gerefi.config.generated.VariableRegistryValues;
import com.gerefi.core.gerEFIVersion;
import com.gerefi.io.IoStream;
import com.gerefi.io.LinkConnector;
import com.gerefi.io.LinkManager;
import com.gerefi.io.tcp.BinaryProtocolServer;
import com.gerefi.io.tcp.TcpIoStream;
import com.gerefi.proxy.NetworkConnector;
import com.gerefi.server.ControllerInfo;
import com.gerefi.server.SessionDetails;
import com.gerefi.server.gerEFISSLContext;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.devexperts.logging.Logging.getLogging;
import static com.gerefi.Timeouts.READ_IMAGE_TIMEOUT;
import static com.gerefi.io.tcp.TcpConnector.LOCALHOST;

public class TestHelper extends MockitoTestHelper {
    private static final Logging log = getLogging(TestHelper.class);
    public static final String TEST_SIGNATURE_1 = "gerEFI master.2020.07.06.frankenso_na6.2468827536";
    public static final String TEST_SIGNATURE_2 = "gerEFI master.2020.07.11.proteus_f4.1986715563";
    public static final ControllerInfo CONTROLLER_INFO = new ControllerInfo("name", "make", "code", VariableRegistryValues.TS_SIGNATURE);
    public static final String TEST_TOKEN_1 = "00000000-1234-1234-1234-123456789012";
    public static final String TEST_TOKEN_3 = "33333333-3333-1234-1234-123456789012";

    @NotNull
    public static ScalarIniField createIniField(Field field) {
        return new ScalarIniField(field.getName(), field.getOffset(), "", field.getType(), 1, "0");
    }

    @NotNull
    public static ConfigurationImage prepareImage(int input) {
        ConfigurationImage ci = new ConfigurationImage(BinaryProtocol.iniFileProvider.provide(null).getMetaInfo().getTotalSize());

  //      scalarIniField.setValue(ci, new Constant(scalarIniField.getName(), "", Integer.toString(input), scalarIniField.getDigits()));
        return ci;
    }

    @NotNull
    public static BinaryProtocolServer createVirtualController(ConfigurationImage ci, int port, Listener serverSocketCreationCallback, BinaryProtocolServer.Context context) throws IOException {
        BinaryProtocolState state = new BinaryProtocolState();
        state.setConfigurationImage(ci);
        byte[] currentOutputs = new byte[VariableRegistryValues.TS_TOTAL_OUTPUT_SIZE];
        state.setCurrentOutputs(currentOutputs);

        LinkManager linkManager = new LinkManager();
        linkManager.setConnector(LinkConnector.getDetachedConnector(state));
        BinaryProtocolServer server = new BinaryProtocolServer();
        server.start(linkManager, port, serverSocketCreationCallback, context);
        return server;
    }

    @NotNull
    public static IoStream secureConnectToLocalhost(int controllerPort) {
        IoStream targetEcuSocket;
        try {
            targetEcuSocket = new TcpIoStream("[local]", gerEFISSLContext.getSSLSocket(LOCALHOST, controllerPort));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to connect to controller " + LOCALHOST + ":" + controllerPort);
        }
        return targetEcuSocket;
    }

    @NotNull
    public static IoStream connectToLocalhost(int controllerPort) {
        IoStream targetEcuSocket;
        try {
            targetEcuSocket = new TcpIoStream("[local]", new Socket(LOCALHOST, controllerPort));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to connect to controller " + LOCALHOST + ":" + controllerPort);
        }
        return targetEcuSocket;
    }

    public static BinaryProtocolServer createVirtualController(int controllerPort, ConfigurationImage controllerImage, BinaryProtocolServer.Context context) throws InterruptedException {
        CountDownLatch controllerCreated = new CountDownLatch(1);
        try {
            BinaryProtocolServer server = createVirtualController(controllerImage, controllerPort, parameter -> controllerCreated.countDown(), context);
            assertLatch(controllerCreated);
            return server;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static SessionDetails createTestSession(String authToken, String signature) {
        ControllerInfo ci = new ControllerInfo("vehicle", "make", "code", signature);

        return new SessionDetails(NetworkConnector.Implementation.Unknown, ci, authToken, SessionDetails.createOneTimeCode(), gerEFIVersion.CONSOLE_VERSION);
    }

    public static void assertLatch(String message, CountDownLatch reconnectCounter) throws InterruptedException {
        assertLatch(message, reconnectCounter, READ_IMAGE_TIMEOUT);
    }

    public static void assertLatch(String message, CountDownLatch reconnectCounter, int timeout) throws InterruptedException {
        Assertions.assertTrue(reconnectCounter.await(timeout, TimeUnit.MILLISECONDS), message);
        log.info("*******************");
        log.info(message + " is good");
        log.info("*******************");
    }

    public static void assertLatch(CountDownLatch reconnectCounter) throws InterruptedException {
      Assertions.assertTrue(reconnectCounter.await(READ_IMAGE_TIMEOUT, TimeUnit.MILLISECONDS));
    }
}
