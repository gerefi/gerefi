package com.gerefi.ui.basic;

import com.gerefi.*;
import com.gerefi.core.net.ConnectionAndMeta;
import com.gerefi.core.ui.FrameHelper;
import com.gerefi.maintenance.StatusAnimation;
import com.gerefi.tools.TunerStudioHelper;
import com.gerefi.ui.BasicLogoHelper;
import com.gerefi.ui.util.DefaultExceptionHandler;
import com.gerefi.ui.util.UiUtils;
import com.gerefi.ui.widgets.StatusPanel;

import javax.swing.*;

import java.lang.reflect.InvocationTargetException;

/**
 * Focuses on firmware updater
 * Much simpler than {@link com.gerefi.StartupFrame}
 */
public class BasicStartupFrame {
    private final String whiteLabel = ConnectionAndMeta.getWhiteLabel(ConnectionAndMeta.getProperties());

    private final StatusPanel statusPanel = new StatusPanel();
    private final BasicUpdaterPanel basicUpdaterPanel = new BasicUpdaterPanel(
        ConnectionAndMeta.isDefaultWhitelabel(whiteLabel),
        statusPanel
    );
    private final FrameHelper frame = FrameHelper.createFrame(
        whiteLabel + " basic console " + Launcher.CONSOLE_VERSION
    );

    private final StatusAnimation status = new StatusAnimation(this::updateStatus, StartupFrame.SCANNING_PORTS);

    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        runTool(null);
    }

    public static void runTool(String[] args) throws InterruptedException, InvocationTargetException {
        DefaultExceptionHandler.install();
        SwingUtilities.invokeAndWait(BasicStartupFrame::new);
    }

    public BasicStartupFrame() {
        final JPanel panel = new JPanel();
        panel.add(basicUpdaterPanel.getContent());
        panel.add(statusPanel);
        TunerStudioHelper.maybeCloseTs();

        SerialPortScanner.INSTANCE.addListener(currentHardware -> SwingUtilities.invokeLater(() -> {
            onHardwareUpdated(currentHardware);
        }));

        BasicLogoHelper.setGenericFrameIcon(frame.getFrame());
        frame.showFrame(panel, false);
        UiUtils.centerWindow(frame.getFrame());
        packFrame();
    }

    private void packFrame() {
        frame.getFrame().pack();
    }

    private void updateStatus(final String niceStatus) {
        basicUpdaterPanel.updateStatus(niceStatus);

        // I'm not sure why it works, but it looks like the following frame packing helps to avoid displaying of logo on
        // the right side of frame
        packFrame();
    }

    public void onHardwareUpdated(final AvailableHardware currentHardware) {
        status.stop();

        basicUpdaterPanel.onHardwareUpdated(currentHardware);

        // I'm not sure if the following frame packing is really necessary, but I'm adding it just in case if frame was
        // not packed in updateStatus method
        packFrame();
    }
}
