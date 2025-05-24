package com.gerefi.nucleo;

import com.devexperts.logging.Logging;
import com.gerefi.GerefiTestBase;
import com.gerefi.f4discovery.DiscoveryPwmHardwareTest;
import com.gerefi.common.VssHardwareTestLogic;
import org.junit.Test;

import static com.devexperts.logging.Logging.getLogging;

public class NucleoVssHardwareTest extends GerefiTestBase {
    private static final Logging log = getLogging(DiscoveryPwmHardwareTest.class);

    @Override
    protected boolean needsHardwareTriggerInput() {
        // This test uses hardware trigger input!
        return true;
    }

    @Test
    public void testIdlePin() {
        VssHardwareTestLogic.runIdleVssTest(ecu, "PD2", "PA6");
    }
}
