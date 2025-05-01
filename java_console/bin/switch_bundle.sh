#!/bin/bash

#
# switch from one kind of bundle to another, for instance from microGerEFI to Hellen72
#

if [ -z "$1" ]; then
	echo "New bundle name expected"
	exit 1
fi

BUNDLE=$1
CURRENT=${PWD##*/}
CURRENT=${CURRENT:-/}
CURRENT_BRANCH=$(echo "$CURRENT" | cut -d '.' -f 2)
CURRENT_BUNDLE=$(echo "$CURRENT" | cut -d '.' -f 3)
cd ..
mv "gerefi.${CURRENT_BRANCH}.${CURRENT_BUNDLE}" "gerefi.${CURRENT_BRANCH}.${BUNDLE}"
cd "gerefi.${CURRENT_BRANCH}.${BUNDLE}"

rm -rf gerefi*bin
rm -rf gerefi*hex
rm -rf gerefi*dfu
rm -rf gerefi*ini
bash
