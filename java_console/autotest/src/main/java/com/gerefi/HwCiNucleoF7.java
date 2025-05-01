package com.gerefi;

import com.gerefi.common.MiscTest;
import com.gerefi.f4discovery.*;
import com.gerefi.nucleo.NucleoPwmHardwareTest;
import com.gerefi.nucleo.NucleoVssHardwareTest;

public class HwCiNucleoF7 {
    public static void main(String[] args) {
        CmdJUnitRunner.runHardwareTestAndExit(new Class[]{
            PTraceTest.class,
            CompositeLoggerTest.class,
            HighRevTest.class,
            NucleoPwmHardwareTest.class,
			NucleoVssHardwareTest.class,
//            MiscTest.class,
            BurnCommandTest.class,
//            CommonFunctionalTest.class,
        });
    }
}
