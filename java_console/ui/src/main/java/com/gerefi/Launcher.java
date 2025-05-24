package com.gerefi;

import com.devexperts.logging.FileLogger;
import com.devexperts.logging.Logging;
import com.gerefi.core.gerEFIVersion;
import com.gerefi.tools.ConsoleTools;
import com.gerefi.ui.engine.EngineSnifferPanel;
import com.gerefi.core.preferences.storage.PersistentConfiguration;

import java.util.Date;

import static com.devexperts.logging.Logging.getLogging;

/**
 * this is the main entry point of gerEfi ECU console
 * <p/>
 * <p/>
 * 12/25/12
 * Andrey Belomutskiy, (c) 2013-2020
 *
 * @see Autoupdate#COM_GEREFI_LAUNCHER which invokes this class via reflection!
 * @see StartupFrame
 * @see EngineSnifferPanel
 */
public class Launcher implements gerEFIVersion {
    private static final Logging log = getLogging(Launcher.class);
    public static final String TOOLS_PATH = System.getProperty("tools_path", ".");

    /**
     * gerEfi console entry point
     *
     * @see StartupFrame if no parameters specified
     */
    public static void main(final String[] args) throws Exception {
        FileLogger.init();
        log.info("gerEFI UI console " + CONSOLE_VERSION);
        log.info("Compiled " + new Date(gerEFIVersion.classBuildTimeMillis()));
        log.info("\n\n");
        PersistentConfiguration.registerShutdownHook();

        if (ConsoleTools.runTool(args)) {
            return;
        }

        ConsoleTools.printTools();

        ConsoleUI.startUi(args);
    }
}
