package com.gerefi.output;

import com.gerefi.ReaderState;
import com.gerefi.ReaderStateImpl;
import com.gerefi.ToolUtil;
import com.gerefi.util.LazyFile;

import java.io.IOException;

public class FileJavaVariableRegistryConsumer implements ConfigurationConsumer {
    private final String className;

    private final LazyFile java;
    private final ReaderStateImpl state;

    public FileJavaVariableRegistryConsumer(ReaderStateImpl state, String folderName, LazyFile.LazyFileFactory fileFactory, String className) {
        this.state = state;
        this.className = className;
        java = fileFactory.create(folderName + className + ".java");
    }

    @Override
    public void startFile() {
        FileJavaFieldsConsumer.startJavaFile(java, className, state, getClass());
    }

    @Override
    public void handleEndStruct(ReaderState readerState, ConfigStructure structure) {

    }

    public void endFile() throws IOException {
        java.write(state.getVariableRegistry().getJavaConstants());
        java.write("}" + ToolUtil.EOL);
        java.close();
    }
}
