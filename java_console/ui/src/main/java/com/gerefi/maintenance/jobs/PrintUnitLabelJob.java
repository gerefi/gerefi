package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;
import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.ui.basic.UnitLabelPrinter;

import javax.swing.*;

public class PrintUnitLabelJob extends  AsyncJobWithContext<SerialPortWithParentComponentJobContext> {
    public PrintUnitLabelJob(final SerialPortScanner.PortResult port, final JComponent parent) {
        super("Print unit label", new SerialPortWithParentComponentJobContext(port, parent));
    }

    @Override
    public void doJob(UpdateOperationCallbacks callbacks, Runnable onJobFinished) {
        JobHelper.doJob(
            () -> {
                if (UnitLabelPrinter.INSTANCE.printUnitLabel(context.getParent(), context.getPort(), callbacks)) {
                    callbacks.done();
                } else {
                    callbacks.error();
                }
            },
            onJobFinished
        );
    }
}
