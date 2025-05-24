package com.gerefi.maintenance;

import com.gerefi.io.UpdateOperationCallbacks;

import static com.gerefi.maintenance.MaintenanceUtil.detectPcan;
import static com.gerefi.maintenance.StLinkFlasher.detectStLink;

public class DfuFlasherSandbox {
    public static void main(String[] args) {
        System.out.println("detectStLink " + detectStLink(UpdateOperationCallbacks.DUMMY));
        System.out.println("detectPcan " + detectPcan(UpdateOperationCallbacks.DUMMY));
    }
}
