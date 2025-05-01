#!/bin/bash

cd console
java -jar gerefi_autoupdate.jar release
echo Release update done.

# https://github.com/gerefi/gerefi/issues/2601
chmod +x ../bin/*.sh
