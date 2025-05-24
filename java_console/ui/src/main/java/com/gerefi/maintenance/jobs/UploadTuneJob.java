package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;
import com.gerefi.io.UpdateOperationCallbacks;
import com.gerefi.maintenance.TuneUploader;

public class UploadTuneJob extends AsyncJobWithContext<UploadTuneJobContext> {
    public UploadTuneJob(final SerialPortScanner.PortResult port, final String panamaUrl) {
        super("Upload Tune", new UploadTuneJobContext(port, panamaUrl));
    }

    @Override
    public void doJob(UpdateOperationCallbacks callbacks, Runnable onJobFinished) {
        JobHelper.doJob(() -> {
            if (TuneUploader.INSTANCE.uploadTune(context.getPort(), context.getPanamaUrl(), callbacks)) {
                callbacks.done();
            } else {
                callbacks.error();
            }
        }, onJobFinished);

    }
}
