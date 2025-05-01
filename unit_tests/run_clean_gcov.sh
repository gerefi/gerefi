#!/bin/bash

make clean
make -j$(nproc)
build/gerefi_test
bash ci_gcov.sh "$GEREFI_DOXYGEN_FTP_USER" "$GEREFI_DOXYGEN_FTP_PASS" "$GEREFI_FTP_SERVER"
