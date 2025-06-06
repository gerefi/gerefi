package com.gerefi.f4discovery;

import com.gerefi.GerefiTestBase;
import com.gerefi.common.VssHardwareTestLogic;
import org.junit.Test;

/**
 * This test relies on jumpers connecting physical pins on Discovery:
 * PD1<>PC6
 * PD2<>PA5
 */
public class VssHardwareLoopTest extends GerefiTestBase {
    @Override
    protected boolean needsHardwareTriggerInput() {
        // This test uses hardware trigger input!
        return true;
    }

    @Test
    public void test() {
        VssHardwareTestLogic.runIdleVssTest(ecu, "PD2", "PA5");
    }
}
