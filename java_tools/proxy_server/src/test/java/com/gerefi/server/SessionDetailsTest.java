package com.gerefi.server;

import com.gerefi.TestHelper;
import com.gerefi.proxy.NetworkConnector;
import com.gerefi.core.gerEFIVersion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionDetailsTest {

    @Test
    public void testSerialization() {
        SessionDetails sd = new SessionDetails(NetworkConnector.Implementation.Unknown, TestHelper.CONTROLLER_INFO, "auth", 123, gerEFIVersion.CONSOLE_VERSION);

        String json = sd.toJson();
        SessionDetails fromJson = SessionDetails.valueOf(json);
        assertEquals(sd, fromJson);
    }

    @Test
    public void testApplicationRequest() {
        SessionDetails sd = new SessionDetails(NetworkConnector.Implementation.Unknown, TestHelper.CONTROLLER_INFO, "auth", 123, gerEFIVersion.CONSOLE_VERSION);
        ApplicationRequest ar = new ApplicationRequest(sd, new UserDetails("", 321));

        String json = ar.toJson();
        ApplicationRequest fromJson = ApplicationRequest.valueOf(json);
        assertEquals(ar, fromJson);
    }
}
