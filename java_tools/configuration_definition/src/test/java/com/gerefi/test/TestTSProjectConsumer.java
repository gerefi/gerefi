package com.gerefi.test;

import com.gerefi.ReaderStateImpl;
import com.gerefi.output.TSProjectConsumer;

public class TestTSProjectConsumer extends TSProjectConsumer {
    public TestTSProjectConsumer(ReaderStateImpl state) {
        super(null, state);
    }

    @Override
    public void endFile() {
    }
}
