package com.gerefi.maintenance.jobs;

import com.gerefi.maintenance.ProgramSelector;
import com.gerefi.io.UpdateOperationCallbacks;

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
