package com.gerefi.server;

import org.junit.jupiter.api.Test;

import static com.gerefi.Timeouts.MINUTE;
import static com.gerefi.Timeouts.SECOND;
import static com.gerefi.server.Birthday.humanReadableFormat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BirthdayTest {
    @Test
    public void testDurationString() {
        assertEquals("1s", humanReadableFormat(SECOND));
        assertEquals("1m", humanReadableFormat(MINUTE));
    }
}
