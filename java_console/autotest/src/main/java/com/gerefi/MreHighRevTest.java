package com.gerefi;

import com.gerefi.enums.engine_type_e;
import com.gerefi.f4discovery.HighRevTest;
import org.junit.Test;

public class MreHighRevTest extends GerefiTestBase {
    @Test
    public void runMreTest() {
        HighRevTest.runHighRevTest(engine_type_e.MRE_SUBARU_EJ18, ecu);
    }
}
