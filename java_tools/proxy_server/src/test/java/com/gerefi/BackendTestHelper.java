package com.gerefi;

import com.gerefi.binaryprotocol.BinaryProtocolLocalCache;
import com.gerefi.io.tcp.TcpConnector;
import com.gerefi.proxy.MockIniFileProvider;
import com.gerefi.server.Backend;
import com.gerefi.server.UserDetails;
import com.gerefi.server.UserDetailsResolver;
import com.gerefi.server.gerEFISSLContext;
import com.gerefi.tools.online.HttpUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.CountDownLatch;

import static com.gerefi.TestHelper.assertLatch;

public class BackendTestHelper {
    public static void runApplicationConnectorBlocking(Backend backend, int serverPortForRemoteUsers) throws InterruptedException {
        CountDownLatch applicationServerCreated = new CountDownLatch(1);
        try {
            backend.runApplicationConnector(serverPortForRemoteUsers, parameter -> applicationServerCreated.countDown());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        assertLatch(applicationServerCreated);
    }

    public static void runControllerConnectorBlocking(Backend backend, int serverPortForControllers) throws InterruptedException {
        CountDownLatch controllerServerCreated = new CountDownLatch(1);
        try {
            backend.runControllerConnector(serverPortForControllers, parameter -> controllerServerCreated.countDown());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        assertLatch(controllerServerCreated);
    }

    @NotNull
    public static UserDetailsResolver createTestUserResolver() {
        return authToken -> new UserDetails(authToken.substring(0, 5), authToken.charAt(6));
    }

    public static void commonServerTest() throws MalformedURLException {
        MockIniFileProvider.install();
        HttpUtil.GEREFI_PROXY_HOSTNAME = TcpConnector.LOCALHOST;
        BinaryProtocolLocalCache.DISABLE_LOCAL_CONFIGURATION_CACHE = true;

        gerEFISSLContext.init("certificate/test_pkcs12.jks", "password");
    }
}
