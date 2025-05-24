package com.gerefi.maintenance.jobs;

import com.opensr5.ConfigurationImageWithMeta;
import com.gerefi.SerialPortScanner;

public class UpdateCalibrationsJobContext extends SerialPortJobContext {
    private final ConfigurationImageWithMeta calibrations;

    UpdateCalibrationsJobContext(final SerialPortScanner.PortResult port, final ConfigurationImageWithMeta calibrations) {
        super(port);
        this.calibrations = calibrations;
    }

    public ConfigurationImageWithMeta getCalibrations() {
        return calibrations;
    }
}
