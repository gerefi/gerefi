// this section was generated automatically by gerEFI tool config_definition_base-all.jar based on (unknown script) hw_layer/drivers/gpio//mc33810_state.txt
// by class com.gerefi.output.CHeaderConsumer
// begin
#pragma once
#include "gerefi_types.h"
// start of mc33810_state_s
struct mc33810_state_s {
	/**
	 * Spark duration
	 * units: ms
	 * offset 0
	 */
	floatms_t sparkDuration[4] = {};
};
static_assert(sizeof(mc33810_state_s) == 16);

// end
// this section was generated automatically by gerEFI tool config_definition_base-all.jar based on (unknown script) hw_layer/drivers/gpio//mc33810_state.txt
