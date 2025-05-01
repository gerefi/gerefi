package com.gerefi.maintenance.jobs;

import com.gerefi.maintenance.StLinkFlasher;
import com.gerefi.core.FindFileHelper;
import com.gerefi.io.UpdateOperationCallbacks;

import javax.swing.*;

public class StLinkJob extends AsyncJobWithContext<ParentComponentContext> {

    public StLinkJob(final JComponent parent) {
        super("", new ParentComponentContext(parent));
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        // todo: add ST-LINK no-assert mode? or not?
        StLinkFlasher.doUpdateFirmware(FindFileHelper.FIRMWARE_BIN_FILE, callbacks, onJobFinished);
    }
}
