package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;
import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.ProgramSelector;

import javax.swing.*;

public class DfuSwitchJob extends AsyncJobWithContext<SerialPortWithParentComponentJobContext> {
    public DfuSwitchJob(final SerialPortScanner.PortResult port, final JComponent parent) {
        super("DFU switch", new SerialPortWithParentComponentJobContext(port, parent));
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        JobHelper.doJob(
            () -> ProgramSelector.rebootToDfu(context.getParent(), context.getPort().port, callbacks),
            onJobFinished
        );
    }
}
