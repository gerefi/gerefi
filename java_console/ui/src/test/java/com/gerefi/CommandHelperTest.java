package com.gerefi;

import com.gerefi.tools.CommandHelper;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CommandHelperTest {
    @Test
    public void test() {
        String[] args = {"exec", "set", "date", "123"};
        String command = CommandHelper.assembleCommand(args);
        assertEquals("set date 123", command);
    }
}
