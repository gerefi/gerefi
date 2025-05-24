package com.gerefi;

import com.gerefi.binaryprotocol.BinaryProtocol;
import com.gerefi.core.MessagesCentral;
import com.gerefi.io.commands.PTraceHelper;
import com.gerefi.tracing.Entry;
import com.gerefi.tracing.JsonOutput;
import com.gerefi.ui.RpmModel;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.gerefi.tools.ConsoleTools.startAndConnect;

public class PerformanceTraceHelper {
    public static void grabPerformanceTrace(JComponent parent, BinaryProtocol bp) {
        if (bp == null) {
            String msg = "Failed to locate serial ports";
            JOptionPane.showMessageDialog(parent, msg, msg, JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            List<Entry> data = PTraceHelper.requestWaitAndGetPTrace(bp);
            if (data.isEmpty()) {
                String msg = "Empty PERF_TRACE response";
                JOptionPane.showMessageDialog(parent, msg, msg, JOptionPane.ERROR_MESSAGE);
                return;
            }
            MessagesCentral.getInstance().postMessage(PerformanceTraceHelper.class, "Got " + data.size() + " PTrace entries");
            int rpm = RpmModel.getInstance().getValue();
            String fileName = FileLog.getDate() + "_rpm_" + rpm + "_gerEFI_trace" + ".json";

            File outputFile = new File(fileName);
            JsonOutput.writeToStream(data, new FileOutputStream(outputFile));
            MessagesCentral.getInstance().postMessage(PerformanceTraceHelper.class, "Saved to " + outputFile.getAbsolutePath());
            MessagesCentral.getInstance().postMessage(PerformanceTraceHelper.class, "See https://github.com/gerefi/gerefi/wiki/Developer-Performance-Tracing");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void getPerformanceTune() {
        startAndConnect(linkManager -> {
            BinaryProtocol binaryProtocol = linkManager.getConnector().getBinaryProtocol();
            grabPerformanceTrace(null, binaryProtocol);
            System.exit(0);
            return null;
        });
    }
}
