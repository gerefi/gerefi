package com.gerefi.f4discovery;

import com.devexperts.logging.Logging;
import com.gerefi.IoUtil;
import com.gerefi.GerefiTestBase;
import com.gerefi.Timeouts;
import com.gerefi.common.PwmHardwareTestLogic;
import com.gerefi.config.generated.Integration;
import com.gerefi.core.Sensor;
import com.gerefi.core.SensorCentral;
import com.gerefi.enums.engine_type_e;
import org.junit.Test;

import static com.devexperts.logging.Logging.getLogging;
import static com.gerefi.IoUtil.getDisableCommand;
import static com.gerefi.IoUtil.getEnableCommand;
import static com.gerefi.binaryprotocol.BinaryProtocol.sleep;
import static com.gerefi.config.generated.Integration.CMD_BURNCONFIG;
import static com.gerefi.config.generated.Integration.CMD_EXTERNAL_STIMULATION;
import static org.junit.Assert.assertEquals;

/**
 * This test relies on jumpers connecting physical pins on Discovery:
 * PD1<>PC6
 * PD2<>PA5
 */

public class DiscoveryPwmHardwareTest extends GerefiTestBase {
    private static final Logging log = getLogging(DiscoveryPwmHardwareTest.class);

    @Override
    protected boolean needsHardwareTriggerInput() {
        // This test uses hardware trigger input!
        return true;
    }

    @Test
    public void scheduleBurnDoesNotAffectTriggerIssue2839() {
        ecu.setEngineType(engine_type_e.FORD_ASPIRE_1996);
        ecu.sendCommand(IoUtil.setTriggerType(com.gerefi.enums.trigger_type_e.TT_TOOTHED_WHEEL_60_2));
        ecu.sendCommand(getDisableCommand(Integration.CMD_SELF_STIMULATION));
        ecu.sendCommand(getEnableCommand(CMD_EXTERNAL_STIMULATION));
        ecu.changeRpm(1200);
        nextChart();
        nextChart();
        int triggerErrors = (int) SensorCentral.getInstance().getValueSource(Sensor.totalTriggerErrorCounter).getValue();
        log.info("triggerErrors " + triggerErrors);
        for (int i = 0; i < 10; i++) {
            ecu.sendCommand(CMD_BURNCONFIG);
            sleep(5 * Timeouts.SECOND);
        }
        int totalTriggerErrorsNow = (int) SensorCentral.getInstance().getValueSource(Sensor.totalTriggerErrorCounter).getValue();
        log.info("totalTriggerErrorsNow " + totalTriggerErrorsNow);

        assertEquals("totalTriggerErrorCounter", triggerErrors, totalTriggerErrorsNow);
    }

    @Test
    public void testIdlePin() {
        PwmHardwareTestLogic.runIdlePwmTest(ecu, "PD2", "PA5");
    }
}
