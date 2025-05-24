package com.gerefi;

import com.gerefi.autotest.ControllerConnectorState;
import com.gerefi.config.generated.Integration;
import com.gerefi.enums.engine_type_e;
import com.gerefi.functional_tests.EcuTestHelper;
import com.gerefi.io.CommandQueue;
import com.gerefi.io.LinkManager;

import static com.gerefi.IoUtil.*;

/**
 * this command utility confirms that gerEFI hardware stays alive for long periods of time
 */
public class EnduranceTestUtility {

    private static final int DEFAULT_COUNT = 2000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int count = parseCount(args);
        AutotestLogging.INSTANCE.logLine("Running " + count + " cycles");
        try {

            LinkManager linkManager = ControllerConnectorState.getLinkManager();
            CommandQueue commandQueue = linkManager.getCommandQueue();

            for (int i = 0; i < count; i++) {
                EcuTestHelper.currentEngineType = engine_type_e.FORD_ASPIRE_1996.ordinal();
                sendBlockingCommand("set " + Integration.CMD_ENGINE_TYPE + " " + 3, Timeouts.SET_ENGINE_TIMEOUT, commandQueue);
                sleepSeconds(2);
                sendBlockingCommand(getEnableCommand("self_stimulation"), commandQueue);
//                IoUtil.changeRpm(1200);
                EcuTestHelper.currentEngineType = engine_type_e.DEFAULT_FRANKENSO.ordinal();
                sendBlockingCommand("set " + Integration.CMD_ENGINE_TYPE + " " + 28, Timeouts.SET_ENGINE_TIMEOUT, commandQueue);
                sleepSeconds(2);
                AutotestLogging.INSTANCE.logLine("++++++++++++++++++++++++++++++++++++  " + i + "   +++++++++++++++");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(-1);
        }
        AutotestLogging.INSTANCE.logLine("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        AutotestLogging.INSTANCE.logLine("++++++++++++++++++++++++++++++++++++  YES YES YES " + count + "   +++++++++++++++");
        AutotestLogging.INSTANCE.logLine("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        long totalTime = System.currentTimeMillis() - start;
        long minutes = totalTime / 1000 / 60;
        AutotestLogging.INSTANCE.logLine("In " + minutes + " minutes");
    }

    private static int parseCount(String[] args) {
        if (args.length == 2) {
            return Integer.parseInt(args[1]);
        }
        return DEFAULT_COUNT;
    }
}
