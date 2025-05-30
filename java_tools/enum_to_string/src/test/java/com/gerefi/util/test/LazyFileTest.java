package com.gerefi.util.test;

import com.gerefi.util.LazyFileImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LazyFileTest {
    @Test
    public void testUnifySpaces() {
        assertEquals("abc", LazyFileImpl.unifySpaces("a\r\n\r\nb\n\n\nc"));
    }
}
