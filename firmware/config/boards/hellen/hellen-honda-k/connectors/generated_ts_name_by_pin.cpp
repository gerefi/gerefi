//DO NOT EDIT MANUALLY, let automation work hard.

// auto-generated by PinoutLogic.java based on config/boards/hellen/hellen-honda-k/connectors/main.yaml
#include "pch.h"

// see comments at declaration in pin_repository.h
const char * getBoardSpecificPinName(brain_pin_e brainPin) {
	switch(brainPin) {
		case Gpio::A6: return "E22 Brake";
		case Gpio::A8: return "B6 Radiator Relay";
		case Gpio::C13: return "A30 - IGN1";
		case Gpio::C7: return "C13 Aux Low 1";
		case Gpio::C9: return "B23 VTC VVT";
		case Gpio::D10: return "B2 - Injector 4";
		case Gpio::D11: return "B3 - Injector 3";
		case Gpio::D12: return "C11 Aux Low 3";
		case Gpio::D14: return "B21 - EVAP";
		case Gpio::D15: return "C12 Aux Low 2";
		case Gpio::D2: return "AuxHigh1";
		case Gpio::D9: return "E18 - AC Relay";
		case Gpio::E3: return "A27 - IGN4";
		case Gpio::E4: return "A28 - IGN3";
		case Gpio::E5: return "A29 - IGN2";
		case Gpio::F10: return "A26 Cam Sync";
		case Gpio::F11: return "A18 - VSS";
		case Gpio::F12: return "A12 Idle Air Control";
		case Gpio::F13: return "B22 Intake Runner";
		case Gpio::F14: return "B7 Lockout Solenoid";
		case Gpio::F3: return "C21 Aux Digital 1";
		case Gpio::F4: return "C19 Aux Digital 2";
		case Gpio::F5: return "C18 Aux Digital 3";
		case Gpio::F8: return "A25 VTT CAM";
		case Gpio::F9: return "A7 Crankshaft Sensor";
		case Gpio::G11: return "B15 VTEC/VTS Output";
		case Gpio::G12: return "E26 Tachometer";
		case Gpio::G13: return "B18 Alternator Control";
		case Gpio::G14: return "E7 Main Relay Control";
		case Gpio::G2: return "AuxPP1";
		case Gpio::G3: return "E31 Check Engine";
		case Gpio::G6: return "E1 Fuel Relay";
		case Gpio::G7: return "B5 - Injector 1";
		case Gpio::G8: return "B4 - Injector 2";
		default: return nullptr;
	}
	return nullptr;
}
