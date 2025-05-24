package com.gerefi.maintenance.jobs;

import com.gerefi.SerialPortScanner;

import javax.swing.*;

class SerialPortWithParentComponentJobContext extends SerialPortJobContext {
    private final JComponent parent;

    SerialPortWithParentComponentJobContext(final SerialPortScanner.PortResult port, final JComponent parent) {
        super(port);
        this.parent = parent;
    }

    JComponent getParent() {
        return parent;
    }
}
