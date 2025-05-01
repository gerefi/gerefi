package com.gerefi.output;

import com.gerefi.ReaderState;

import java.io.IOException;

public interface ConfigurationConsumer {
    default void startFile() {

    }

    default void endFile() throws IOException {

    }

    void handleEndStruct(ReaderState readerState, ConfigStructure structure) throws IOException;
}
