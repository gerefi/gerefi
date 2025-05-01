package com.gerefi.maintenance.jobs;

import com.gerefi.maintenance.DfuFlasher;
import com.gerefi.io.UpdateOperationCallbacks;

public class DfuEraseJob extends AsyncJob {
    public DfuEraseJob() {
        super("DFU erase");
    }

    @Override
    public void doJob(UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        DfuFlasher.runDfuEraseAsync(callbacks, onJobFinished);
    }
}
