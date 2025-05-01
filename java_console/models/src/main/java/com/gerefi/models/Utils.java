package com.gerefi.models;

/**
 * @author Bruno Ferreira
 *         1/29/13
 */
public class Utils {
    public static int parseIntWithReason(String number, String reason) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unexpected [" + number + "] for " + reason, e);
        }
    }
}
