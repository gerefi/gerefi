package com.gerefi.autotune;

/**
 * Andrey Belomutskiy, (c) 2013-2020
 * 2/23/2016.
 */
public class Result {
    private final double[][] kgbcRES;

    public Result(double[][] kgbcRES) {
        this.kgbcRES = kgbcRES;
    }

    public double[][] getKgbcRES() {
        return kgbcRES;
    }
}
