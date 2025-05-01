package com.gerefi.proxy.client;

import com.gerefi.Timeouts;

import java.io.IOException;

public interface LocalApplicationProxyContext {
    String executeGet(String url) throws IOException;

    /**
     * port on which gerEFI proxy accepts authenticator connections
     */
    int serverPortForRemoteApplications();

    /**
     * local port on which authenticator accepts connections from Tuner Studio
     */
    int authenticatorPort();

    default int startUpIdle() {
        return 6 * Timeouts.MINUTE;
    }

    default int gaugePokingPeriod() {
        return 5 * Timeouts.SECOND;
    }
}
