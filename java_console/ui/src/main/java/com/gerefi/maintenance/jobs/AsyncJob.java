package com.gerefi.maintenance.jobs;

import com.gerefi.io.UpdateOperationCallbacks;

public abstract class AsyncJob {
    private final String name;

    protected AsyncJob(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void doJob(final UpdateOperationCallbacks callbacks, final Runnable onJobFinished);
}
