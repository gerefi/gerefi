package com.gerefi.ui.livedata;

import com.gerefi.config.Field;

public interface VariableValueSource {
    VariableValueSource VOID = name -> null;

    VariableState getValue(String name);

    class VariableState {
        private final Field field;
        private final double value;

        public VariableState(Field field, double value) {
            this.field = field;
            this.value = value;
        }

        public Field getField() {
            return field;
        }

        public double getValue() {
            return value;
        }
    }
}
