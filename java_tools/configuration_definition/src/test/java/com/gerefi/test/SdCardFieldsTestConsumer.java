package com.gerefi.test;

import com.gerefi.ReaderState;
import com.gerefi.output.ConfigStructure;
import com.gerefi.output.ConfigurationConsumer;
import com.gerefi.output.DataLogConsumer;
import com.gerefi.output.SdCardFieldsContent;
import com.gerefi.util.LazyFile;
import com.gerefi.util.LazyFileImpl;

import java.io.IOException;

/**
 * @see DataLogConsumer
 */
public class SdCardFieldsTestConsumer implements ConfigurationConsumer {

    private final SdCardFieldsContent content = new SdCardFieldsContent();
    private final LazyFile output;

    public SdCardFieldsTestConsumer(String outputFileName, boolean isPtr) {
        output = new LazyFileImpl(outputFileName);
        content.isPtr = isPtr;
    }

    @Override
    public void endFile() throws IOException {
        SdCardFieldsContent.wrapContent(output, getBody());
        output.close();
    }

    @Override
    public void handleEndStruct(ReaderState state, ConfigStructure structure) throws IOException {
        content.handleEndStruct(state, structure);
    }

    public String getBody() {
        return content.getBody();
    }
}
