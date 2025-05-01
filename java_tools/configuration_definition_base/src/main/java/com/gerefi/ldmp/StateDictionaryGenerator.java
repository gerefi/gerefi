package com.gerefi.ldmp;

import com.gerefi.ToolUtil;
import com.gerefi.output.FileJavaFieldsConsumer;

import java.util.Date;

import static com.gerefi.VariableRegistry.quote;

/**
 * generator for {@link StateDictionaryFactory}
 */
 public class StateDictionaryGenerator {
    public final StringBuilder content = new StringBuilder();
    private final String yamlFileName;

    public StateDictionaryGenerator(String yamlFileName) {
        this.yamlFileName = yamlFileName;
    }

    public void onEntry(String name, String javaName, String[] outputNames, String cppFileName) {

        if (outputNames.length < 2) {
            append(name, javaName, cppFileName, "");
        } else {
            for (int i = 0; i < outputNames.length; i++) {
                append(name, javaName, cppFileName, Integer.toString(i));
            }
        }
    }

    private void append(String name, String javaName, String cppFileName, String suffix) {
        content.append("        stateDictionary.register(live_data_e.LDS_");
        content.append(name).append(suffix).append(", ");

 //       content.append(FileJavaFieldsConsumer.remoteExtension(javaName)).append(".VALUES, ");
        content.append(quote(cppFileName));
        content.append(");\n");
    }

    public String getCompleteClass() {
        ToolUtil.TOOL = getClass().getSimpleName();

        return "package com.gerefi.enums;\n" +
            "//" + ToolUtil.getGeneratedAutomaticallyTag() + yamlFileName + " on " + new Date() + "n" +
            "\n" +
            "import com.gerefi.config.generated.*;\n" +
            "import com.gerefi.ldmp.StateDictionary;\n" +
            "\n" +
            "public class StateDictionaryFactory {\n" +
            "    public static void initialize(StateDictionary stateDictionary) {\n"
            + content +
            "    }\n" +
            "}";

    }
}
