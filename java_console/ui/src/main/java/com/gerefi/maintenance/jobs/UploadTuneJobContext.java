package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;

public class UploadTuneJobContext extends SerialPortJobContext {
    private final String panamaUrl;

    UploadTuneJobContext(final SerialPortScanner.PortResult port, final String panamaUrl) {
        super(port);
        this.panamaUrl = panamaUrl;
    }

    public String getPanamaUrl() {
        return panamaUrl;
    }
}
