package com.gerefi.tools;

/*
import com.gerefi.dfu.BinaryImage;
import com.gerefi.dfu.DfuImage;
import com.gerefi.dfu.DfuLogic;
import com.gerefi.dfu.HexImage;
import com.gerefi.dfu.usb4java.DfuDeviceLocator;
import com.gerefi.dfu.usb4java.USBDfuConnection;
import cz.jaybee.intelhex.IntelHexException;

import java.io.IOException;
*/

/**
 * prototype of our own DFU frontend, not used in real life
 */
/*
public class DfuTool {
    public static void run(String[] args) throws IOException, IntelHexException {
        if (args.length < 2) {
            System.err.println(".dfu or .hex filename parameter expected");
            return;
        }
        String fileName = args[1];

        DfuLogic.Logger logger = DfuLogic.Logger.CONSOLE;
        USBDfuConnection device = DfuDeviceLocator.findDevice(logger);
        if (device == null) {
            System.err.println("No DFU devices found");
            return;
        }

        BinaryImage image = fileName.toLowerCase().trim().endsWith(".dfu") ? new DfuImage().read(fileName) : HexImage.loadHexToBuffer(fileName, device.getFlashRange());

        DfuLogic.uploadImage(logger, device, image, device.getFlashRange());

        logger.info("DfuSe DFU " + device);
    }
}
*/
