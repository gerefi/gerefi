package com.gerefi.test;

import com.gerefi.EnumsReader;
import com.gerefi.VariableRegistry;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.gerefi.AssertCompatibility.assertNotNull;
import static com.gerefi.AssertCompatibility.assertTrue;

public class ConfigDefinitionTest {
    public static final String FIRMWARE = "../../firmware";

    @Test
    public void testEnumIntoType() throws IOException {
        EnumsReader enumsReader = new EnumsReader();
        enumsReader.read(new FileReader(FIRMWARE + File.separator + "controllers/algo/engine_type_e.h"));

        VariableRegistry variableRegistry = readRealConfig();


        String sb = variableRegistry.getEnumOptionsForTunerStudio(enumsReader, "engine_type_e");

        System.out.println(sb);
        assertNotNull(sb);
        assertTrue("Seems too long" + sb, sb.length() < 100000);
    }

    @NotNull
    public static VariableRegistry readRealConfig() {
        VariableRegistry variableRegistry = new VariableRegistry();

        variableRegistry.readPrependValues(FIRMWARE + File.separator + "integration/gerefi_config.txt", true);
        return variableRegistry;
    }
}
