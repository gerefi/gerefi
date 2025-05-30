#!/bin/bash
echo Stopping...
./stop_server.sh

echo Downloading...
rm -rf gerefi_server.jar
wget https://gerefi.com/build_server/autoupdate/gerefi_server.jar

echo Starting...
./start_server.sh
