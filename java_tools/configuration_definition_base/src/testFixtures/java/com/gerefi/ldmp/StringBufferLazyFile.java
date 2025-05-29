package com.gerefi.ldmp;

import com.gerefi.util.LazyFile;

public class StringBufferLazyFile implements LazyFile {
    StringBuilder sb = new StringBuilder();

    @Override
    public void write(String line) {
        sb.append(line);
    }

    @Override
    public void close() {

    }
}
