package com.gerefi;

public class HwCiMicroGerEFI {
    public static void main(String[] args) {
        CmdJUnitRunner.runHardwareTestAndExit(new Class[]{
                MreHighRevTest.class,
        });
    }
}
