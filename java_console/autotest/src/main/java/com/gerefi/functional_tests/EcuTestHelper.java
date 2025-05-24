package com.gerefi.functional_tests;

import com.devexperts.logging.Logging;
import com.gerefi.autotest.ControllerConnectorState;
import com.gerefi.IoUtil;
import com.gerefi.Timeouts;
import com.gerefi.config.generated.Integration;
import com.gerefi.core.ISensorCentral;
import com.gerefi.core.Sensor;
import com.gerefi.core.SensorCentral;
import com.gerefi.enums.engine_type_e;
import com.gerefi.io.CommandQueue;
import com.gerefi.io.LinkManager;
import com.gerefi.waves.EngineReport;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static com.devexperts.logging.Logging.getLogging;
import static com.gerefi.IoUtil.*;
import static com.gerefi.waves.EngineReport.isCloseEnough;

public class EcuTestHelper {
    public static final Function<String, Object> FAIL = errorCode -> {
        if (errorCode != null)
            throw new IllegalStateException("Failed " + errorCode);
        return null;
    };
    private static final Logging log = getLogging(EcuTestHelper.class);

    public static int currentEngineType;
    public final CommandQueue commandQueue;
    @NotNull
    private final LinkManager linkManager;

    public EcuTestHelper(LinkManager linkManager) {
        this.commandQueue = linkManager.getCommandQueue();
        this.linkManager = linkManager;
    }

    public static void assertRpmDoesNotJump(int rpm, int settleTime, int testDuration, Function<String, Object> callback, CommandQueue commandQueue) {
        IoUtil.changeRpm(commandQueue, rpm);
        sleepSeconds(settleTime);
        AtomicReference<String> result = new AtomicReference<>();
        long start = System.currentTimeMillis();

        ISensorCentral.ListenerToken listener = SensorCentral.getInstance().addListener(Sensor.RPMValue, actualRpm -> {
            if (!isCloseEnough(rpm, actualRpm)) {
                long seconds = (System.currentTimeMillis() - start) / 1000;
                result.set("Got " + actualRpm + " while trying to stay at " + rpm + " after " + seconds + " seconds");
            }
        });

        sleepSeconds(testDuration);
        callback.apply(result.get());

        listener.remove();
    }

    @NotNull
    public LinkManager getLinkManager() {
        return linkManager;
    }

    public static void assertSomewhatClose(double expected, double actual) {
        EcuTestHelper.assertSomewhatClose("", expected, actual);
    }

    public static void assertSomewhatClose(String msg, double expected, double actual) {
        EcuTestHelper.assertSomewhatClose(msg, expected, actual, EngineReport.RATIO);
    }

    /**
     * todo: it's time to leverage junit a bit more properly
     */
    public static void assertSomewhatClose(String msg, double expected, double actual, double ratio) {
        if (!isCloseEnough(expected, actual, ratio))
            throw new IllegalStateException(msg + " Expected " + expected + " but got " + actual);
    }

    @NotNull
    public static EcuTestHelper createInstance(boolean allowHardwareTriggerInput) {
        EcuTestHelper ecu = new EcuTestHelper(ControllerConnectorState.getLinkManager());
        if (allowHardwareTriggerInput) {
            ecu.sendCommand(getEnableCommand(Integration.CMD_TRIGGER_HW_INPUT));
        } else {
            ecu.sendCommand(getDisableCommand(Integration.CMD_TRIGGER_HW_INPUT));
        }
        ecu.enableFunctionalMode();
        return ecu;
    }

    public void sendCommand(String command) {
        sendCommand(command, Timeouts.CMD_TIMEOUT);
    }

    public void sendCommand(String command, int timeoutMs) {
        TestHelper.INSTANCE.assertNotFatal();
        IoUtil.sendBlockingCommand(command, timeoutMs, commandQueue);
    }

    /**
     * this seem to adjust engine sniffer behaviour
     */
    public void enableFunctionalMode() {
        sendCommand(getEnableCommand(Integration.CMD_FUNCTIONAL_TEST_MODE));
    }

    public void changeRpm(final int rpm) {
        IoUtil.changeRpm(commandQueue, rpm);
    }

    public void setEngineType(engine_type_e engine_type_e) {
        int type = engine_type_e.ordinal();
        log.info("AUTOTEST setEngineType " + type);
        currentEngineType = type;
//        sendCommand(CMD_PINS);
        /*
         * we need to stop all activity - that means:
         * - stopping input event
         * - waiting for scheduled actuator actions to run out
         * - disabling PWM
         */
        sendCommand(getDisableCommand(Integration.CMD_SELF_STIMULATION));
        sendCommand(getDisableCommand(Integration.CMD_INJECTION));
        sendCommand(getDisableCommand(Integration.CMD_IGNITION));
        sendCommand(getDisableCommand(Integration.CMD_PWM));
        // changing engine type while engine is running does not work well - we rightfully
        // get invalid configuration critical errors
        sleepSeconds(2);
        sendCommand("set " + Integration.CMD_ENGINE_TYPE + " " + type, Timeouts.SET_ENGINE_TIMEOUT);
        // TODO: document the reason for this sleep?!
        sleepSeconds(1);
        sendCommand(getEnableCommand(Integration.CMD_PWM));
        sendCommand(getEnableCommand(Integration.CMD_SELF_STIMULATION));
//        // we need to skip one chart since it might have been produced with previous engine type
//        TestingUtils.nextChart(commandQueue);
    }
}
