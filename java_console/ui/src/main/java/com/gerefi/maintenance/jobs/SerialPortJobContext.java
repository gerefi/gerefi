package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;

class SerialPortJobContext {
    private final SerialPortScanner.PortResult port;

    SerialPortJobContext(final SerialPortScanner.PortResult port) {
        this.port = port;
    }

    SerialPortScanner.PortResult getPort() {
        return port;
    }
}
