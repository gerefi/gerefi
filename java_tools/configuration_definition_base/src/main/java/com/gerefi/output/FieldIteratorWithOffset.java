package com.gerefi.output;

import com.gerefi.ConfigField;

import java.util.List;

public class FieldIteratorWithOffset extends FieldIterator {
    public int currentOffset;

    public FieldIteratorWithOffset(List<ConfigField> tsFields) {
        super(tsFields);
    }
}
