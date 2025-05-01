package com.gerefi.ldmp;

import com.gerefi.util.LazyFile;

import java.util.HashMap;
import java.util.Map;

import static com.gerefi.AssertCompatibility.assertEquals;
import static com.gerefi.AssertCompatibility.assertNotNull;

public class TestFileCaptor implements LazyFile.LazyFileFactory {
    Map<String, StringBufferLazyFile> fileCapture = new HashMap<>();

    void assertOutput(String expected, String key) {
        StringBufferLazyFile stringBufferLazyFile = fileCapture.get(key);
        assertNotNull("Not found " + key, stringBufferLazyFile);
        assertEquals(expected, stringBufferLazyFile.sb.toString());
    }

    @Override
    public LazyFile create(String fileName) {
        StringBufferLazyFile file = new StringBufferLazyFile();
        fileCapture.put(fileName, file);
        return file;
    }

}
