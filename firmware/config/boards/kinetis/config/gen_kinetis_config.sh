#!/bin/bash
# This script files reads gerefi_config.txt and produces firmware persistent configuration headers
# the storage section of gerefi.ini is updated as well

BOARDNAME=kinetis
SHORT_BOARD_NAME=kin
BOARD_DIR=config/boards/${BOARDNAME}















  INI="gerefi_${SHORT_BOARD_NAME}.ini"




bash gen_signature.sh ${SHORT_BOARD_NAME}

source gen_config_common.sh
echo "Using COMMON_GEN_CONFIG [$COMMON_GEN_CONFIG]"

java \
 $COMMON_GEN_CONFIG_PREFIX \
 -tool kinetis_gen_config.bat \
 $COMMON_GEN_CONFIG \
 -c_defines controllers/generated/gerefi_generated_kin.h \
 -c_destination controllers/generated/engine_configuration_generated_structures_kin.h \
 -enumInputFile controllers/algo/gerefi_hw_enums.h

[ $? -eq 0 ] || { echo "ERROR generating TunerStudio config for ${BOARDNAME}"; exit 1; }
