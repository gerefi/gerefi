package com.gerefi.ui.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.gerefi.ui.RpmModel;

/**
 * Date: 12/27/12
 * Bruno Ferreira, (c) 2013-2020
 */
public class RpmModelTest {
    @Test
    public void testRpmSmoothing() {
        RpmModel r = RpmModel.getInstance();
        r.setValue(100);

        Assert.assertEquals(100, r.getSmoothedValue());

        r.setValue(104);
        Assert.assertEquals(100, r.getSmoothedValue());

        r.setValue(96);
        Assert.assertEquals(100, r.getSmoothedValue());

        r.setValue(200);
        Assert.assertEquals(200, r.getSmoothedValue());
    }
}
