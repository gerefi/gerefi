package com.gerefi;

import com.gerefi.common.MiscTest;
import com.gerefi.f4discovery.CompositeLoggerTest;
import com.gerefi.f4discovery.HighRevTest;
import com.gerefi.f4discovery.PTraceTest;
import com.gerefi.proteus.ProteusAnalogTest;

/**
 * See ProteusAnalogTest for jumper configuration documentation
 */
public class HwCiProteus {
    public static void main(String[] args) {
        CmdJUnitRunner.runHardwareTestAndExit(new Class[]{
            PTraceTest.class,
                CompositeLoggerTest.class,
                HighRevTest.class,
                MiscTest.class,
                ProteusAnalogTest.class,
        });
    }
}
