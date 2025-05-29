package com.gerefi.output;

import com.gerefi.ReaderState;
import com.gerefi.ToolUtil;
import com.gerefi.util.LazyFile;
import org.jetbrains.annotations.NotNull;

import static com.gerefi.ToolUtil.EOL;

/**
 * This class generates java representation of gerEfi data structures used by gerEfi console
 */
public class FileJavaFieldsConsumer {
    private static final String JAVA_PACKAGE = "com.gerefi.config.generated";

    @NotNull
    public static String remoteExtension(String fileNameWithExtension) {
        return fileNameWithExtension.substring(0, fileNameWithExtension.indexOf('.'));
    }

    static void startJavaFile(LazyFile file, String className, ReaderState state, Class<?> clazz) {
        writePackageLine(file);
        file.write("// this file " + state.getHeader() + ToolUtil.EOL + EOL);
        file.write("// by " + clazz + EOL);
        file.write("import com.gerefi.config.*;" + EOL + EOL);
        writeClassOpenLine(file, className);
    }

    public static void writeClassOpenLine(LazyFile lazyFile, String className) {
        lazyFile.write("public class " + className + " {" + ToolUtil.EOL);
    }

    public static void writePackageLine(LazyFile lazyFile) {
        lazyFile.write("package " + JAVA_PACKAGE + ";" + ToolUtil.EOL + ToolUtil.EOL);
    }
}
