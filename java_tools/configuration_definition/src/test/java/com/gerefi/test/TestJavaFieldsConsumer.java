package com.gerefi.test;

import com.gerefi.ReaderStateImpl;
import com.gerefi.output.JavaFieldsConsumer;

public class TestJavaFieldsConsumer extends JavaFieldsConsumer {
    public TestJavaFieldsConsumer(ReaderStateImpl state) {
        super(state, 0);
    }

    @Override
    public void endFile() {
    }
}
