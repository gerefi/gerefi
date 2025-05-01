/**
 * @file	main.cpp
 * @brief C++ main entry point
 *
 * See gerefi.cpp for implementation details notes.
 *
 * @date Nov 29, 2012
 * @author Bruno Ferreira, (c) 2012-2023
 *      http://gerefi.com/
 */

#include "pch.h"

#include "gerefi.h"
#include "mpu_util.h"

int main(void) {
	// Maybe your board needs to do something special before HAL init
	preHalInit();

	/*
	 * ChibiOS/RT initialization
	 */
	halInit();
	chSysInit();

	/**
	 * most basic MCU initialization - no configuration access, no external hardware access
	 */
	baseMCUInit();

	runGerEfi();
	return 0;
}

// Weak linked default implementation (not necessarily required for all boards)
__attribute__((weak)) void preHalInit() { }
