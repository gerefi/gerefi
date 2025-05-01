#
# this tool might have enough flexibility to generate both C# and java class depending on specified "top line" and "stringClassName" parameters?
#
java -jar ../console/gerefi_console.jar ptrace_enums development/perf_trace.h ../java_console/models/src/main/java/com/gerefi/tracing/EnumNames.java "package com.gerefi.tracing;" "public static final String"

