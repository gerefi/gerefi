package com.gerefi.maintenance.jobs;

import com.gerefi.Launcher;
import com.gerefi.io.DoubleCallbacks;
import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.ExecHelper;
import com.gerefi.ui.StatusWindow;

import static com.gerefi.core.ui.FrameHelper.appendBundleName;

public enum AsyncJobExecutor {
    INSTANCE;

    public void executeJobWithStatusWindow(final AsyncJob job) {
        executeJobWithStatusWindow(job, UpdateOperationCallbacks.DUMMY, () -> {});
    }

    public void executeJobWithStatusWindow(
        final AsyncJob job,
        final UpdateOperationCallbacks secondary,
        final Runnable onJobFinished
    ) {
        final UpdateOperationCallbacks callbacks = StatusWindow.createAndShowFrame(appendBundleName(job.getName() + " " + Launcher.CONSOLE_VERSION));
        final UpdateOperationCallbacks doubleCallbacks = new DoubleCallbacks(callbacks, secondary);
        executeJob(job, doubleCallbacks, onJobFinished);
    }

    public void executeJob(final AsyncJob job, final UpdateOperationCallbacks callbacks, final Runnable onJobFinished) {
        final Runnable jobWithSuspendedPortScanning = () -> job.doJob(callbacks, onJobFinished);
        ExecHelper.submitAction(jobWithSuspendedPortScanning, "mx");
    }
}
