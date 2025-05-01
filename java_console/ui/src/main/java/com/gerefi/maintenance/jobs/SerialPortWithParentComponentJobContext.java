package com.gerefi.maintenance.jobs;

import javax.swing.*;

import com.gerefi.SerialPortScanner;

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
