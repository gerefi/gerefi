package com.gerefi.io;

import com.devexperts.logging.Logging;
import com.gerefi.UiProperties;
import com.gerefi.core.GerEfiSignature;
import com.gerefi.core.SignatureHelper;
import com.gerefi.core.io.BundleUtil;
import com.gerefi.binaryprotocol.BinaryProtocol;

import javax.swing.*;
import java.io.IOException;

import static com.devexperts.logging.Logging.getLogging;
import static com.gerefi.Timeouts.SECOND;
import static com.gerefi.binaryprotocol.BinaryProtocol.sleep;

/**
 * this code is shared between DFU and OpenBLT tracks
 */
public class BootloaderHelper {
    private static final Logging log = getLogging(BootloaderHelper.class);

    private static void sendBootloaderRebootCommand(IoStream stream, UpdateOperationCallbacks callbacks, String cmd) {
        byte[] command = BinaryProtocol.getTextCommandBytes(cmd);
        try {
            stream.sendPacket(command);
            callbacks.logLine(String.format("Reboot command [%s] sent into %s!\n", cmd, stream));
        } catch (IOException e) {
            callbacks.logLine("Error " + e);
        }
    }

    public static boolean sendBootloaderRebootCommand(JComponent parent, String signature, IoStream stream, UpdateOperationCallbacks callbacks, String command) {
        GerEfiSignature controllerSignature = SignatureHelper.parse(signature);
        String fileSystemBundleTarget = BundleUtil.getBundleTarget();
        if (fileSystemBundleTarget != null && controllerSignature != null) {
            // hack: QC firmware self-identifies as "normal" not QC firmware :(
            if (!UiProperties.skipEcuTypeDetection() &&
                !fileSystemBundleTarget.equalsIgnoreCase(controllerSignature.getBundleTarget()) && !fileSystemBundleTarget.contains("_QC_")) {
                String message = String.format("You have \"%s\" controller does not look right to program it with \"%s\"", controllerSignature.getBundleTarget(), fileSystemBundleTarget);
                log.info(message);

                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(parent, message);
                    // in case of mismatched bundle type we are supposed do close connection
                    // and properly handle the case of user hitting "Update Firmware" again
                    // closing connection is a mess on Windows so it's simpler to just exit
                    new Thread(() -> {
                        // let's have a delay and separate thread to address
                        // "wrong bundle" warning text sometimes not visible #3267
                        sleep(5 * SECOND);
                        System.exit(-5);
                    }).start();
                });

                return false;
            }
        }

        sendBootloaderRebootCommand(stream, callbacks, command);
        return true;
    }
}
