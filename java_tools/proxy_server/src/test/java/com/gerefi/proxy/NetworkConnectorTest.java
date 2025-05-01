package com.gerefi.proxy;

import com.devexperts.logging.Logging;
import com.opensr5.ConfigurationImage;
import com.gerefi.BackendTestHelper;
import com.gerefi.TestHelper;
import com.gerefi.Timeouts;
import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.io.tcp.BinaryProtocolServer;
import com.gerefi.io.tcp.TcpConnector;
import com.gerefi.server.Backend;
import com.gerefi.server.ControllerConnectionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.concurrent.CountDownLatch;

import static com.devexperts.logging.Logging.getLogging;
import static com.gerefi.TestHelper.assertLatch;
import static com.gerefi.binaryprotocol.BinaryProtocol.sleep;

public class NetworkConnectorTest {
    private static final Logging log = getLogging(NetworkConnectorTest.class);

    @BeforeEach
    public void setup() throws MalformedURLException {
        BackendTestHelper.commonServerTest();
    }

    @Test
    public void testReconnect() throws InterruptedException {
        int serverPortForControllers = 7504;
        int httpPort = 7503;

        // create virtual controller to which "gerEFI network connector" connects to
        int controllerPort = 7502;
        ConfigurationImage controllerImage = new ConfigurationImage(BinaryProtocol.iniFileProvider.provide(null).getMetaInfo().getTotalSize());
        BinaryProtocolServer.Context patientController = new BinaryProtocolServer.Context() {
            @Override
            public int getTimeout() {
                // we need controller to not timeout while we are playing with all backend shutdowns
                return 5 * Timeouts.MINUTE;
            }
        };
        TestHelper.createVirtualController(controllerPort, controllerImage, patientController);

        NetworkConnectorContext connectorContext = new NetworkConnectorContext() {
            @Override
            public int reconnectDelay() {
                return 3;
            }

            @Override
            public int firstPacketTimeout() {
                return 3 * Timeouts.SECOND;
            }

            @Override
            public int consecutivePacketTimeout() {
                return 3 * Timeouts.SECOND;
            }

            @Override
            public int serverPortForControllers() {
                return serverPortForControllers;
            }
        };

        CountDownLatch reconnectCounter = new CountDownLatch(1);

        // start "gerEFI network connector" to connect controller with backend since in real life controller has only local serial port it does not have network
        NetworkConnector.ReconnectListener reconnectListener = new NetworkConnector.ReconnectListener() {
            @Override
            public void onReconnect() {
                reconnectCounter.countDown();
            }
        };
        NetworkConnector networkConnector = new NetworkConnector();
        networkConnector.start(NetworkConnector.Implementation.Unknown, TestHelper.TEST_TOKEN_1, TcpConnector.LOCALHOST + ":" + controllerPort, connectorContext, reconnectListener);

        assertLatch(reconnectCounter);

        // start backend, assert connection, stop backend
        log.info("First backend instance");
        assertWouldConnect(serverPortForControllers, httpPort);

        // giving http server time to shut down
        sleep(3 * Timeouts.SECOND);

        // now let's do this again with a new backend instance
        log.info("Second backend instance");
        assertWouldConnect(serverPortForControllers, httpPort);
        networkConnector.close();
    }

    private void assertWouldConnect(int serverPortForControllers, int httpPort) throws InterruptedException {
        CountDownLatch onControllerRegistered = new CountDownLatch(1);
        Backend backend = new Backend(BackendTestHelper.createTestUserResolver(), httpPort) {
            @Override
            public void register(ControllerConnectionState controllerConnectionState) {
                super.register(controllerConnectionState);
                onControllerRegistered.countDown();
            }
        };
        BackendTestHelper.runControllerConnectorBlocking(backend, serverPortForControllers);

        // assert that reconnect actually happened
        assertLatch(onControllerRegistered);
        backend.close();
    }
}
