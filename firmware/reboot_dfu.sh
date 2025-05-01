#!/bin/bash

# fail on error!
set -e

cd ../java_tools
./gradlew :ui:shadowJar
cd ../firmware

java -jar ../console/gerefi_console.jar reboot_dfu
