#!/bin/bash

# Generate human-readable version of the .map memory usage report
java -jar ../java_tools/gcc_map_reader.jar build/gerefi.map > gerefi_ram_report.txt
