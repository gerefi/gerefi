#!/bin/bash

echo "Should be executed from project root folder. Will try to upload to $GEREFI_SSH_SERVER"
pwd
# ibom is part of Doxygen job simply in order to reduce workspace HDD usage on my tiny build server
bash misc/jenkins/InteractiveHtmlBom/run.sh

if [ -n "$GEREFI_SSH_SERVER" ]; then
  echo "Uploading IBOMs"
  cd hardware
  tar -czf - ibom | sshpass -p "$GEREFI_SSH_PASS" ssh -o StrictHostKeyChecking=no "$GEREFI_SSH_USER"@"$GEREFI_SSH_SERVER" "tar -xzf - -C docs"
fi
[ $? -eq 0 ] || { echo "upload FAILED"; exit 1; }
