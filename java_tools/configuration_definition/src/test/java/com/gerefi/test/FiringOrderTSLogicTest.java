package com.gerefi.test;

import com.gerefi.FiringOrderTSLogic;
import org.junit.jupiter.api.Test;

public class FiringOrderTSLogicTest {
    @Test
    public void parseFiringOrderLine() {
        FiringOrderTSLogic.parseLine("FO_1_3_4_2 = 1, // typical inline 4", new FiringOrderTSLogic.State());
    }
}
