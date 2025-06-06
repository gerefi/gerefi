/**
 * @file	gps_uart.cpp
 * @brief	GPS receiver hardware UART driver
 *
 * Tested and developed for NEO-6M
 * http://www.u-blox.com/en/gps-modules/pvt-modules/previous-generations/neo-6-family.html
 * Technically any UART GPS should work with this driver since NMEA protocol is pretty common anyway
 *
 * @date Dec 28, 2013
 * @author Andrey Belomutskiy, (c) 2012-2020
 * Kot_dnz 2014
 */

#include "pch.h"

#if EFI_UART_GPS
#include <string.h>

#include "gerefi_types.h"
#include "console_io.h"
#include "eficonsole.h"
#include "nmea.h"
#include "gps_uart.h"
#include "rtc_helper.h"

static SerialConfig GPSserialConfig = { GPS_SERIAL_SPEED, 0, USART_CR2_STOP1_BITS | USART_CR2_LINEN, 0 };
static THD_WORKING_AREA(gpsThreadStack, UTILITY_THREAD_STACK_SIZE);

// this field holds our current state
static loc_t GPSdata;
static efidatetime_t lastDateTime;

static int gpsMessageCount = 0;
static int uartErrors = 0;

// TODO: some data structure for coordinates location

float getCurrentSpeed(void) {
    // TODO: ???
	return GPSdata.speed;
}

static void printGpsInfo() {
	efiPrintf("GPS RX %s", hwPortname(engineConfiguration->gps_rx_pin));
	efiPrintf("GPS TX %s", hwPortname(engineConfiguration->gps_tx_pin));

	efiPrintf("m=%d,e=%d: vehicle speed = %.2f", gpsMessageCount, uartErrors, getCurrentSpeed());

	float sec = getTimeNowMs() / 1000.0;
	efiPrintf("communication speed: %.2f", gpsMessageCount / sec);

	efiPrintf("GPS latitude = %.2f\r\n", GPSdata.latitude);
	efiPrintf("GPS longitude = %.2f\r\n", GPSdata.longitude);
}

static void onGpsMessage(const char * const buffer) {
	gps_location(&GPSdata, buffer);

	if (GPSdata.quality == 4 && GPSdata.time.year > 0) {
		getRtcDateTime(&lastDateTime);
		if (GPSdata.time.second != dateTime.second) {
			// quality =4 (valid GxRMC), year > 0, and difference more than second
			setRtcDateTime(GPSdata.time);
		}
	}

	gpsMessageCount++;
}

// we do not want this on stack, right?
static char gps_str[GPS_MAX_STRING];

static THD_FUNCTION(GpsThreadEntryPoint, arg) {
	(void) arg;
	chRegSetThreadName("GPS thread");

	int count = 0;

	while (true) {
		msg_t charbuf = streamGet(GPS_SERIAL_DEVICE);
		if (charbuf == 10 || count == GPS_MAX_STRING) {					// if 0xD,0xA or limit
			if (count >= 1)
				gps_str[--count] = '\0';					// delete 0xD

			// scheduleMsg(&logger, "got GPS [%s]", gps_str);

			// 'gps_str' string completed
			onGpsMessage(gps_str);
			memset(&gps_str, '\0', GPS_MAX_STRING);			// clear buffer
			count = 0;
		} else {
			gps_str[count++] = charbuf;
		}
	}
}

static bool isGpsEnabled() {
	return (isBrainPinValid(engineConfiguration->gps_rx_pin) &&
			isBrainPinValid(engineConfiguration->gps_tx_pin));
}

void initGps(void) {
	if (!isGpsEnabled())
		return;


	sdStart(GPS_SERIAL_DEVICE, &GPSserialConfig);
//  GPS we have USART1: PB7 -> USART1_RX and PB6 -> USART1_TX
	efiSetPadMode("GPS tx", engineConfiguration->gps_tx_pin, PAL_MODE_ALTERNATE(7));
	efiSetPadMode("GPS rx", engineConfiguration->gps_rx_pin, PAL_MODE_ALTERNATE(7));

// todo: add a thread which would save location. If the GPS 5Hz - we should save the location each 200 ms
	chThdCreateStatic(gpsThreadStack, sizeof(gpsThreadStack), LOWPRIO, (tfunc_t)(void*) GpsThreadEntryPoint, NULL);

	addConsoleAction("gpsinfo", &printGpsInfo);
}

#endif // EFI_UART_GPS
