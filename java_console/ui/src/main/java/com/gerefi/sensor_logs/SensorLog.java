package com.gerefi.sensor_logs;

public interface SensorLog {
    double getSecondsSinceFileStart();

    void writeSensorLogLine();

    void close();
}
