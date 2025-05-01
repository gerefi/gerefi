package com.gerefi.ts_plugin;

import com.devexperts.logging.Logging;
import com.efiAnalytics.plugin.ApplicationPlugin;
import com.efiAnalytics.plugin.ecu.ControllerAccess;
import com.gerefi.core.gerEFIVersion;
import org.putgemin.VerticalFlowLayout;

import javax.swing.*;

import static com.devexperts.logging.Logging.getLogging;

/**
 * This class is the more permanent part of the plugin, it's responsible for refreshing and launcher PluginEntry via reflections.
 * which downloads the main more volatile UI part (PluginEntry)
 *
 * by the way TS installs stuff into %user%\.efianalytics\TunerStudio\plugins folder
 * @see PluginLauncherSandbox sandbox for this
 * @see PluginEntry
 * @see Updater
 */
public class TsPluginLauncher implements ApplicationPlugin {
    public static final int BUILD_VERSION = 6;
    static final String VERSION = "2025.alpha." + BUILD_VERSION;
    private static final Logging log = getLogging(TsPluginLauncher.class);
    private static final String HELP_URL = "https://github.com/gerefi/gerefi/wiki/TS-Plugin";

    private final JPanel content = new JPanel(new VerticalFlowLayout());

    public TsPluginLauncher() {
        log.info("init " + this);
    }

    @Override
    public String getIdName() {
        return "gerEFI_plugin";
    }

    @Override
    public int getPluginType() {
        return PERSISTENT_DIALOG_PANEL;
    }

    @Override
    public String getDisplayName() {
        return "gerEFI Plugin";
    }

    @Override
    public String getDescription() {
        return "A plugin for gerEFI integration";
    }

    @Override
    public void initialize(ControllerAccess controllerAccess) {
    }

    @Override
    public boolean displayPlugin(String signature) {
        log.info("displayPlugin " + signature);
        // todo: smarter implementation one day
        return true;
    }

    @Override
    public boolean isMenuEnabled() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "gerEFI LLC";
    }

    @Override
    public JComponent getPluginPanel() {
        synchronized (this) {
            // lazy initialization since TunerStudio creates one instance only to get version information without any
            // intentions to display the UI
            if (content.getComponents().length == 0) {
                log.info("Create Updater " + this + " " + gerEFIVersion.CONSOLE_VERSION);
                Updater updater = new Updater();
                content.add(updater.getContent());
            }
        }
        return content;
    }

    @Override
    public void close() {
        log.info("TsPluginLauncher.close " + this);
    }

    @Override
    public String getHelpUrl() {
        return HELP_URL;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public double getRequiredPluginSpec() {
        return PLUGIN_API_VERSION;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getVersion();
    }
}
