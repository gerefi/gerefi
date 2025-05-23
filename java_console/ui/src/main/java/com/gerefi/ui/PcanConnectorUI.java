package com.gerefi.ui;

import com.devexperts.logging.Logging;
import com.gerefi.NamedThreadFactory;
import com.gerefi.config.generated.VariableRegistryValues;
import com.gerefi.core.ui.AutoupdateUtil;
import com.gerefi.io.can.PCanIoStream;
import com.gerefi.tools.CANConnectorStartup;
import com.gerefi.core.ui.FrameHelper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static com.devexperts.logging.Logging.getLogging;

public class PcanConnectorUI {
    private static final Logging log = getLogging(PcanConnectorUI.class);

    public static void show() {
        FrameHelper frame = new FrameHelper(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Running PCAN connector for TS: RX on " + Integer.toString(VariableRegistryValues.CAN_ECU_SERIAL_RX_ID, 16)), BorderLayout.NORTH);
        JTextArea logTextArea = new JTextArea();
        JPanel panelForScroll = new JPanel(new BorderLayout());
        panelForScroll.add(logTextArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(panelForScroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane, BorderLayout.CENTER);

        StatusConsumer statusConsumer = (string) -> SwingUtilities.invokeLater(() -> {
            log.info(string);
            String stringForTextArea = string;
            stringForTextArea += "\r\n";
            logTextArea.append(stringForTextArea);
            AutoupdateUtil.trueLayout(logTextArea);
        });

        new NamedThreadFactory("PCAN-connector").newThread(() -> {
            PCanIoStream stream = PCanIoStream.createStream(statusConsumer);
            try {
                if (stream != null)
                    CANConnectorStartup.start(stream, statusConsumer);
            } catch (IOException e) {
                statusConsumer.logLine("Error " + e);
            }
        }).start();

        frame.showFrame(panel);
    }
}
