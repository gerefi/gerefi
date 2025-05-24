package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;
import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.ProgramSelector;

import javax.swing.*;

public class OpenBltSwitchJob extends AsyncJobWithContext<SerialPortWithParentComponentJobContext> {
    public OpenBltSwitchJob(final SerialPortScanner.PortResult port, final JComponent parent) {
        super("OpenBLT switch", new SerialPortWithParentComponentJobContext(port, parent));
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        JobHelper.doJob(
            () -> ProgramSelector.rebootToOpenblt(context.getParent(), context.getPort().port, callbacks),
            onJobFinished
        );
    }
}
