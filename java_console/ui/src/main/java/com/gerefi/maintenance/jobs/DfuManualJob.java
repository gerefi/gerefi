package com.gerefi.maintenance.jobs;

import com.gerefi.maintenance.DfuFlasher;
import com.gerefi.io.UpdateOperationCallbacks;

public class DfuManualJob extends AsyncJob {
    public DfuManualJob() {
        super("DFU update");
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        DfuFlasher.runDfuProgramming(callbacks, onJobFinished);
    }
}
