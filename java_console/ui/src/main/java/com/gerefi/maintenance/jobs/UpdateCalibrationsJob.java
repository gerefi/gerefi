package com.gerefi.maintenance.jobs;

import com.opensr5.ConfigurationImageWithMeta;
import com.gerefi.SerialPortScanner;
import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.CalibrationsUpdater;

public class UpdateCalibrationsJob extends AsyncJobWithContext<UpdateCalibrationsJobContext> {
    public UpdateCalibrationsJob(final SerialPortScanner.PortResult port, final ConfigurationImageWithMeta calibrations) {
        super("Update calibrations", new UpdateCalibrationsJobContext(port, calibrations));
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        CalibrationsUpdater.INSTANCE.updateCalibrations(
            context.getPort().port,
            context.getCalibrations().getConfigurationImage(),
            callbacks,
            onJobFinished
        );
    }
}
