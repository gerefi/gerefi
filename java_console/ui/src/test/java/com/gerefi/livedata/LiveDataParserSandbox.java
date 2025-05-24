package com.gerefi.livedata;

import com.gerefi.config.Field;
import com.gerefi.config.FieldType;
import com.gerefi.ui.UIContext;
import com.gerefi.ui.livedata.VariableValueSource;
import com.gerefi.core.ui.FrameHelper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class LiveDataParserSandbox {
    public static void main(String[] args) {
        Map<String, Double> values = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        values.put("engineTooSlow", 1.0);
        values.put("engineTooFast", 0.0);

        VariableValueSource valueSource = getVariableValueSource(values);

        new FrameHelper().showFrame(new LiveDataParserPanel(new UIContext(), valueSource, "ac_control.cpp").getContent());
    }

    @Nullable
    public static VariableValueSource getVariableValueSource(Map<String, Double> values) {
        return name -> {
            Double value = values.get(name);
            if (value == null)
                return null;
            return new VariableValueSource.VariableState(new Field(name, 0, FieldType.BIT), value);
        };
    }
}
