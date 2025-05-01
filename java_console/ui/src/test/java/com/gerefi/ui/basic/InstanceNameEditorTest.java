package com.gerefi.ui.basic;

import org.junit.jupiter.api.Test;

import com.gerefi.ui.basic.PanamaArgumentValidator;

import static org.junit.Assert.assertEquals;

public class InstanceNameEditorTest {
    @Test
    public void test() {
        assertEquals(PanamaArgumentValidator.secureInstanceName("\\sd##%$4566  .. ... .. //x"), "sd4566     x");
    }
}
