package com.gerefi;

import com.devexperts.logging.FileLogger;
import com.devexperts.logging.Logging;
import com.gerefi.autodetect.PortDetector;
import com.gerefi.binaryprotocol.BinaryProtocolLogger;
import com.gerefi.core.MessagesCentral;
import com.gerefi.core.net.ConnectionAndMeta;
import com.gerefi.io.CommandQueue;
import com.gerefi.io.LinkManager;
import com.gerefi.io.serial.BaudRateHolder;
import com.gerefi.maintenance.StLinkFlasher;
import com.gerefi.tools.TunerStudioHelper;
import com.gerefi.ui.*;
import com.gerefi.ui.console.MainFrame;
import com.gerefi.ui.console.TabbedPanel;
import com.gerefi.ui.engine.EngineSnifferPanel;
import com.gerefi.ui.lua.LuaScriptPanel;
import com.gerefi.ui.util.DefaultExceptionHandler;
import com.gerefi.ui.util.JustOneInstance;
import com.gerefi.core.ui.AutoupdateUtil;
import com.gerefi.util.LazyFile;
import com.gerefi.util.LazyFileImpl;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.devexperts.logging.Logging.getLogging;
import static com.gerefi.StartupFrame.setFrameIcon;
import static com.gerefi.core.preferences.storage.PersistentConfiguration.getConfig;
import static com.gerefi.core.gerEFIVersion.CONSOLE_VERSION;
import static com.gerefi.ui.util.UiUtils.createOnTopParent;

/**
 * @see StartupFrame
 */
public class ConsoleUI {
    private static final Logging log = getLogging(ConsoleUI.class);
    private static final int DEFAULT_TAB_INDEX = 0;
    private static final String WIKI_URL = "https://github.com/gerefi/gerefi/wiki/gerEFI-logs-folder";

    public static final String TAB_INDEX = "main_tab";
    protected static final String PORT_KEY = "port";
    protected static final String SPEED_KEY = "speed";
    public static final String TITLE = "gerEFI";
    public static EngineSnifferPanel engineSnifferPanel;

    static Frame staticFrame;

    private final TabbedPanel tabbedPane;
    private final String port;

    public final UIContext uiContext = new UIContext();

    /**
     * We can listen to tab activation event if we so desire
     */
    private final Map<Component, ActionListener> tabSelectedListeners = new HashMap<>();

    public static Frame getFrame() {
        return staticFrame;
    }

    public ConsoleUI(String port) {
        CommandQueue.ERROR_HANDLER = e -> SwingUtilities.invokeLater(() -> {
            throw new IllegalStateException("Connectivity error", e);
        });

        log.info("init...");
        tabbedPane = new TabbedPanel(uiContext);
        this.port = port;
        MainFrame mainFrame = new MainFrame(this, tabbedPane);
        ConsoleUI.staticFrame = mainFrame.getFrame().getFrame();
        setFrameIcon(ConsoleUI.staticFrame);
        log.info("Console " + CONSOLE_VERSION);

        log.info("Hardware: " + StLinkFlasher.getHardwareKind());

        getConfig().getRoot().setProperty(PORT_KEY, port);
        getConfig().getRoot().setProperty(SPEED_KEY, BaudRateHolder.INSTANCE.baudRate);

        LinkManager linkManager = uiContext.getLinkManager();
        // todo: this blocking IO operation should NOT be happening on the UI thread
        linkManager.start(port, mainFrame.listener);

        engineSnifferPanel = new EngineSnifferPanel(uiContext, getConfig().getRoot().getChild("digital_sniffer"));
        if (!LinkManager.isLogViewerMode(port))
            engineSnifferPanel.setOutpinListener(uiContext.getLinkManager().getEngineState());

        // what is LogViewer? is this all dead?
//        if (LinkManager.isLogViewerMode(port))
//            tabbedPane.addTab("Log Viewer", new LogViewer(uiContext, engineSnifferPanel));

        uiContext.DetachedRepositoryINSTANCE.init(getConfig().getRoot().getChild("detached"));
        uiContext.DetachedRepositoryINSTANCE.load();
        if (!linkManager.isLogViewer())
            tabbedPane.addTab("Gauges", new GaugesPanel(uiContext, getConfig().getRoot().getChild("gauges")).getContent());

        if (!linkManager.isLogViewer()) {
            MessagesPane messagesPane = new MessagesPane(uiContext, getConfig().getRoot().getChild("messages"));
            tabbedPaneAdd("Messages", messagesPane.getContent(), messagesPane.getTabSelectedListener());
        }
        if (!linkManager.isLogViewer()) {
            tabbedPane.addTab("Bench Test", new BenchTestPane(uiContext, getConfig()).getContent());
        }

        if (!linkManager.isLogViewer()) {
            LuaScriptPanel luaScriptPanel = new LuaScriptPanel(uiContext, getConfig().getRoot().getChild("lua"));
            tabbedPaneAdd("Lua Scripting", luaScriptPanel.getPanel(), luaScriptPanel.getTabSelectedListener());
        }

        tabbedPaneAdd("Engine Sniffer", engineSnifferPanel.getPanel(), engineSnifferPanel.getTabSelectedListener());



//        tabbedPane.addTab("LE controls", new FlexibleControls().getPanel());

//        tabbedPane.addTab("ADC", new AdcPanel(new BooleanInputsModel()).createAdcPanel());
//        if (tabbedPane.paneSettings.showStimulatorPane && !LinkManager.isSimulationMode && !LinkManager.isLogViewerMode(port)) {
//            // todo: rethink this UI? special command line key to enable it?
//            EcuStimulator stimulator = EcuStimulator.getInstance();
//            tabbedPane.addTab("ECU stimulation", stimulator.getPanel());
//        }
//        tabbedPane.addTab("live map adjustment", new Live3DReport().getControl());
//        tabbedPane.add("Wizards", new Wizard().createPane());

//        if (!linkManager.isLogViewer())
//            tabbedPane.addTab("Settings", tabbedPane.settingsTab.createPane());
        if (!linkManager.isLogViewer()) {
            tabbedPane.addTab("Live Data", LiveDataPane.createLazy(uiContext).getContent());
            tabbedPane.addTab("Sensors Live Data", new SensorsLiveDataPane(uiContext).getContent());
        }

        if (!linkManager.isLogViewer() && false) // todo: fix it & better name?
            tabbedPane.addTab("Logs Manager", tabbedPane.logsManager.getContent());


        MessagesCentral.getInstance().postMessage(ConsoleUI.class, "COMPOSITE_OFF_RPM=" + BinaryProtocolLogger.COMPOSITE_OFF_RPM);

        /*
        https://github.com/gerefi/gerefi/issues/5956
        tabbedPane.addTab("gerEFI Online", new OnlineTab(uiContext).getContent());
*/
        tabbedPane.addTab("Connection", new ConnectionTab(uiContext).getContent());

        if (false) {
            // this feature is not totally happy safer to disable to reduce user confusion
            // https://github.com/gerefi/gerefi/issues/5292
            uiContext.sensorLogger.init();
        }

        if (!LinkManager.isLogViewerMode(port)) {
            int selectedIndex = getConfig().getRoot().getIntProperty(TAB_INDEX, DEFAULT_TAB_INDEX);
            if (selectedIndex < tabbedPane.tabbedPane.getTabCount())
                tabbedPane.tabbedPane.setSelectedIndex(selectedIndex);
        }

        tabbedPane.tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (e.getSource() instanceof JTabbedPane) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    int selectedIndex = pane.getSelectedIndex();
                    System.out.println("Selected paneNo: " + selectedIndex);
                    ActionListener actionListener = tabSelectedListeners.get(pane.getComponentAt(selectedIndex));
                    if (actionListener != null)
                        actionListener.actionPerformed(null);
                }
            }
        });

        AutoupdateUtil.setAppIcon(mainFrame.getFrame().getFrame());
        log.info("showFrame");
        mainFrame.getFrame().showFrame(tabbedPane.tabbedPane);
    }

    public String getPort() {
        return port;
    }

    private static void writeReadmeFile() {
        LazyFile file = new LazyFileImpl(FileLogger.DIR + "README.html");
        file.write("<center>" + "<a href='" + WIKI_URL + "'>More info online<br/><img src=https://raw.githubusercontent.com/wiki/gerefi/gerefi/logo.gif></a>");
        try {
            file.close();
        } catch (IOException e) {
            // ignoring this one
        }
    }

    static void startUi(String[] args) throws InterruptedException, InvocationTargetException {
        if (ConnectionAndMeta.saveReadmeHtmlToFile()) {
            new Thread(ConsoleUI::writeReadmeFile).start();
        }

        log.info("OS name: " + FileLog.getOsName());
        log.info("OS version: " + System.getProperty(FileLog.OS_VERSION));

        getConfig().load();
        AutotestLogging.suspendLogging = getConfig().getRoot().getBoolProperty(GaugesPanel.DISABLE_LOGS);
        DefaultExceptionHandler.install();
// not very useful?        VersionChecker.start();
        SwingUtilities.invokeAndWait(() -> awtCode(args));
    }

    /**
     * Adds a tab with activation listener
     */
    private void tabbedPaneAdd(String title, JComponent component, ActionListener tabSelectedListener) {
        tabSelectedListeners.put(component, tabSelectedListener);
        tabbedPane.addTab(title, component);
    }

    private static void awtCode(String[] args) {
        if (JustOneInstance.isAlreadyRunning()) {
            int result = JOptionPane.showConfirmDialog(createOnTopParent(), "Looks like another instance is already running. Do you really want to start another instance?",
                TITLE, JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.NO_OPTION)
                System.exit(-1);
        }
        JustOneInstance.onStart();
        TunerStudioHelper.maybeCloseTs();

        try {
            boolean isPortDefined = args.length > 0;
            boolean isBaudRateDefined = args.length > 1;
            if (isBaudRateDefined)
                BaudRateHolder.INSTANCE.baudRate = Integer.parseInt(args[1]);

            String port = null;
            if (isPortDefined)
                port = args[0];

            if (isPortDefined) {
                port = PortDetector.autoDetectSerialIfNeeded(port);
                if (port == null) {
                    isPortDefined = false;
                }
            }

            if (isPortDefined) {
                new ConsoleUI(port);
            } else {
                for (String p : LinkManager.getCommPorts())
                    MessagesCentral.getInstance().postMessage(Launcher.class, "Available port: " + p);
                new StartupFrame().showUi();
            }

        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }
}
