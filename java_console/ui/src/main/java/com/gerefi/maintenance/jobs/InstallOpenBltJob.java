package com.gerefi.maintenance.jobs;

import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.DfuFlasher;

public class InstallOpenBltJob extends AsyncJob {
    public InstallOpenBltJob() {
        super("OpenBLT Initial Programming");
    }

    @Override
    public void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        DfuFlasher.runOpenBltInitialProgramming(callbacks, onJobFinished);
    }
}
