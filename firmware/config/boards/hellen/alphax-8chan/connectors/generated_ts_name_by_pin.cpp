//DO NOT EDIT MANUALLY, let automation work hard.

// auto-generated by PinoutLogic.java based on config/boards/hellen/alphax-8chan/connectors/8chan-A-B.yaml
// auto-generated by PinoutLogic.java based on config/boards/hellen/alphax-8chan/connectors/8chan-C.yaml
// auto-generated by PinoutLogic.java based on config/boards/hellen/alphax-8chan/connectors/8chan-D.yaml
#include "pch.h"

// see comments at declaration in pin_repository.h
const char * getBoardSpecificPinName(brain_pin_e brainPin) {
	switch(brainPin) {
		case Gpio::A10: return "1B - VR1-/Hall Input";
		case Gpio::A15: return "31C - Ignition 12";
		case Gpio::A6: return "3B/4B - VR2";
		case Gpio::A9: return "6C - High Side 2";
		case Gpio::B8: return "28C - Ignition 6";
		case Gpio::B9: return "21C - Ignition 7";
		case Gpio::C1: return "22A - Switch 3";
		case Gpio::C10: return "29C - Ignition 9";
		case Gpio::C11: return "23C - Ignition 10";
		case Gpio::C12: return "30C - Ignition 11";
		case Gpio::C13: return "11C - Ignition 1";
		case Gpio::C6: return "14D - VVT 2";
		case Gpio::C7: return "20D - VVT 3";
		case Gpio::C8: return "21D - VVT 4";
		case Gpio::C9: return "22C - Boost";
		case Gpio::D10: return "4D - Injector 4";
		case Gpio::D11: return "3D - Injector 3";
		case Gpio::D12: return "5C - High Side 1";
		case Gpio::D13: return "8D - VVT 1";
		case Gpio::D14: return "16C - Low Side 1";
		case Gpio::D15: return "23C - Low Side 2";
		case Gpio::D2: return "17C - Low Side 5";
		case Gpio::D3: return "7C - High Side 3";
		case Gpio::D4: return "11D - Main Relay";
		case Gpio::D7: return "10D - Fan";
		case Gpio::D9: return "5D - Injector 5";
		case Gpio::E11: return "14A - Switch 2";
		case Gpio::E12: return "15A - Hall Input 1";
		case Gpio::E13: return "23A - Hall Input 2";
		case Gpio::E14: return "24A - Hall Input 3 / SENT";
		case Gpio::E15: return "9A - Hall Input 4";
		case Gpio::E2: return "14C - Ignition 5";
		case Gpio::E3: return "20C - Ignition 4";
		case Gpio::E4: return "13C - Ignition 3";
		case Gpio::E5: return "19C - Ignition 2";
		case Gpio::E6: return "15C - Ignition 8";
		case Gpio::E9: return "6A - Switch 1";
		case Gpio::F11: return "26A - VSS Hall";
		case Gpio::F12: return "6D - Injector 6";
		case Gpio::F13: return "7D - Injector 7";
		case Gpio::F14: return "13D - Injector 8";
		case Gpio::F3: return "5A - Switch 4";
		case Gpio::F4: return "13A - Hall Input 5";
		case Gpio::F5: return "21B - Flex";
		case Gpio::F6: return "21B - Knock2 Input";
		case Gpio::G10: return "9D - Fuel Pump";
		case Gpio::G11: return "25C - Low Side 6";
		case Gpio::G14: return "8C - Low Side 3";
		case Gpio::G3: return "34C - Low Side 7";
		case Gpio::G4: return "12D - NOS";
		case Gpio::G5: return "9C - Low Side 4";
		case Gpio::G6: return "15D - Tachometer";
		case Gpio::G7: return "1D - Injector 1";
		case Gpio::G8: return "2D - Injector 2";
		case Gpio::H13: return "5B/6B - VR3";
		case Gpio::H2: return "24D - Injector 12";
		case Gpio::H3: return "25D - Injector 11";
		case Gpio::H4: return "26D - Injector 10";
		case Gpio::H5: return "19D - Injector 9";
		case Gpio::H8: return "7B/13B - VR4";
		default: return nullptr;
	}
	return nullptr;
}