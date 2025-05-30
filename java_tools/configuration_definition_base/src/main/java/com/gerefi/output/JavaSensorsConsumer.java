package com.gerefi.output;

import com.gerefi.ConfigField;
import com.gerefi.ReaderState;
import com.gerefi.VariableRegistry;

import java.io.IOException;

/**
 * One day this generator should generate what is still manually maintained Sensor.java
 */
public class JavaSensorsConsumer implements ConfigurationConsumer {
    private final int sensorTsPosition;

    private final StringBuilder sb = new StringBuilder();
    private int structSize;

    public JavaSensorsConsumer(int sensorTsPosition) {
        this.sensorTsPosition = sensorTsPosition;
    }

    @Override
    public void startFile() {
    }

    @Override
    public void endFile() throws IOException {
    }

    @Override
    public void handleEndStruct(ReaderState readerState, ConfigStructure structure) throws IOException {
        FieldsStrategy fieldsStrategy = new FieldsStrategy() {
            public int writeOneField(FieldIterator iterator, String prefix, int tsPosition) {
                ConfigField configField = iterator.cf;
                ConfigField next = iterator.next;

                if (!configField.isBit()) {
                    sb.append(configField.getName()).append("(");

                    String string = configField.getCommentTemplated();
                    if (string == null || string.isEmpty()) {
                        string = quote(configField.getName());
                    } else if (string.charAt(0) != '"') {
                        // huh? weird conditional quoting?
                        string = quote(string);
                    }

                    sb.append(string).append(", ");

                    sb.append("SensorCategory.SENSOR_INPUTS, ");
                    sb.append(JavaFieldsConsumer.getJavaType(configField.getElementSize())).append(", ");
                    sb.append(tsPosition).append(", ");
                    sb.append(configField.autoscaleSpecNumber()).append(", "); // scale
                    sb.append(configField.getMin()).append(", ");
                    sb.append(configField.getMax()).append(", ");

                    sb.append(quote(configField.getUnits())).append(")");


                    sb.append(",");
                    sb.append("\n");


                }
                tsPosition = iterator.adjustSize(tsPosition);
                // this value would be consumed by LiveDataProcessor
                // todo: too many variables that's fragile shall we move tsPosition to iterator state?
                structSize = tsPosition - sensorTsPosition;

                return tsPosition;
            }
        };
        fieldsStrategy.run(readerState, structure, sensorTsPosition);
    }

    public static String quote(String string) {
        return VariableRegistry.quote(string);
    }

    public String getContent() {
        return sb.toString();
    }

    public int getStructSize() {
        return structSize;
    }
}
