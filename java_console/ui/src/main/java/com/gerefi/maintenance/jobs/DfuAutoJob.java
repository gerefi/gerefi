package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;
import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.DfuFlasher;

import javax.swing.*;

public class DfuAutoJob extends AsyncJobWithContext<SerialPortWithParentComponentJobContext> {
    public DfuAutoJob(final SerialPortScanner.PortResult port, final JComponent parent) {
        super("DFU update", new SerialPortWithParentComponentJobContext(port, parent));
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        JobHelper.doJob(
            () -> {
                if (DfuFlasher.doAutoDfu(context.getParent(), context.getPort(), callbacks)) {
                    callbacks.done();
                } else {
                    callbacks.error();
                }
            },
            onJobFinished
        );
    }
}
