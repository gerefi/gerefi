package com.gerefi.maintenance.jobs;

import com.gerefi.maintenance.DfuFlasher;
import com.gerefi.io.UpdateOperationCallbacks;

public class InstallOpenBltJob extends AsyncJob {
    public InstallOpenBltJob() {
        super("OpenBLT Initial Programming");
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        DfuFlasher.runOpenBltInitialProgramming(callbacks, onJobFinished);
    }
}
