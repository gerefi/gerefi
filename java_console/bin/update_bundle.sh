#!/bin/bash

cd console
java -jar gerefi_autoupdate.jar version

# https://github.com/gerefi/gerefi/issues/2601
chmod +x ../bin/*.sh
