package com.gerefi.ui.widgets;

import com.gerefi.config.FieldType;
import com.gerefi.core.Sensor;
import com.gerefi.core.SensorCentral;

import javax.swing.*;

/**
 * Read-only text representation of an int {@link Sensor}
 * <p>
 * Andrey Belomutskiy, (c) 2013-2020
 * 8/5/2017
 */
public class IntGaugeLabel extends JLabel {
    public IntGaugeLabel(final String shortName, Sensor sensor) {
        if (sensor.getType() != FieldType.INT)
            throw new IllegalArgumentException(sensor.name());

        SensorCentral.getInstance().addListener(sensor, value -> setText(shortName + ": " + (int)value));
    }
}
