
// original mcu176 (not mm-mcu176)
Gpio getCommsLedPin() {
	return Gpio::H10;  // blue
}

Gpio getRunningLedPin() {
	return Gpio::H9;  // green
}

Gpio getWarningLedPin() {
	return Gpio::H11; // yellow
}
