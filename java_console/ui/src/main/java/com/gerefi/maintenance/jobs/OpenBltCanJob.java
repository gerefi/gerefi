package com.gerefi.maintenance.jobs;

import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.ProgramSelector;

import javax.swing.*;

public class OpenBltCanJob extends AsyncJobWithContext<ParentComponentContext> {
    public OpenBltCanJob(final JComponent parent) {
        super("OpenBLT via CAN", new ParentComponentContext(parent));
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        JobHelper.doJob(() -> ProgramSelector.flashOpenBltCan(context.getParent(), callbacks), onJobFinished);
    }
}
