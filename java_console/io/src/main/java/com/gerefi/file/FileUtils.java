package com.gerefi.file;

import com.gerefi.core.EngineState;
import com.gerefi.io.LinkManager;

import java.io.*;
import java.util.List;

/**
 * Date: 3/8/13
 * Andrey Belomutskiy, (c) 2013-2020
 */
public class FileUtils {
    public static void readFile2(String filename, EngineState engineState) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String packed = EngineState.packString(line);
                engineState.processNewData(packed + "\r\n", LinkManager.ENCODER);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void saveList(String filename, List<Double> values) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(filename));


            for (Double v : values)
                w.write(v + "\r\n");

            w.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
