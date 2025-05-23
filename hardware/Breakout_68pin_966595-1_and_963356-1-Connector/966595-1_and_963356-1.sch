EESchema Schematic File Version 2
LIBS:KICAD_Older_Version
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
LIBS:966595-1
LIBS:963356-1
LIBS:966595-1_and_963356-1-cache
EELAYER 25 0
EELAYER END
$Descr A 11000 8500
encoding utf-8
Sheet 1 1
Title "ECU adapter"
Date "2017-12-16"
Rev "R0.1"
Comp "gerEFI by DAECU"
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L 966595-1 P102
U 1 1 52230AB7
P 2925 4375
F 0 "P102" H 2925 4275 50  0000 C CNN
F 1 "966595-1" H 2925 4475 50  0000 C CNN
F 2 "966595-1" H 2925 4025 50  0001 C CNN
F 3 "DOCUMENTATION" H 2925 4125 50  0001 C CNN
	1    2925 4375
	1    0    0    -1  
$EndComp
$Comp
L 966595-1 P102
U 2 1 52230AB3
P 4650 2175
F 0 "P102" H 4650 2075 50  0000 C CNN
F 1 "966595-1" H 4650 2275 50  0000 C CNN
F 2 "966595-1" H 4650 1825 50  0001 C CNN
F 3 "DOCUMENTATION" H 4650 1925 50  0001 C CNN
	2    4650 2175
	1    0    0    -1  
$EndComp
$Comp
L 966595-1 P102
U 3 1 5A35A6A7
P 7150 4475
F 0 "P102" H 7150 4375 50  0000 C CNN
F 1 "966595-1" H 7150 4575 50  0000 C CNN
F 2 "966595-1" H 7150 4125 50  0001 C CNN
F 3 "DOCUMENTATION" H 7150 4225 50  0001 C CNN
	3    7150 4475
	1    0    0    -1  
$EndComp
$Comp
L 966595-1 P102
U 4 1 5A35A7A2
P 9550 2025
F 0 "P102" H 9550 1925 50  0000 C CNN
F 1 "966595-1" H 9550 2125 50  0000 C CNN
F 2 "966595-1" H 9550 1675 50  0001 C CNN
F 3 "DOCUMENTATION" H 9550 1775 50  0001 C CNN
	4    9550 2025
	1    0    0    -1  
$EndComp
$Comp
L 963356-1 P101
U 2 1 5A35AD66
P 3150 2175
F 0 "P101" H 3150 2075 50  0000 C CNN
F 1 "963356-1" H 3150 2275 50  0000 C CNN
F 2 "963356-1" H 3150 1825 50  0001 C CNN
F 3 "DOCUMENTATION" H 3150 1925 50  0001 C CNN
	2    3150 2175
	-1   0    0    -1  
$EndComp
$Comp
L 963356-1 P101
U 1 1 5A35AE82
P 1425 4375
F 0 "P101" H 1425 4275 50  0000 C CNN
F 1 "963356-1" H 1425 4475 50  0000 C CNN
F 2 "963356-1" H 1425 4025 50  0001 C CNN
F 3 "DOCUMENTATION" H 1425 4125 50  0001 C CNN
	1    1425 4375
	-1   0    0    -1  
$EndComp
$Comp
L 963356-1 P101
U 3 1 5A35AF46
P 5650 4475
F 0 "P101" H 5650 4375 50  0000 C CNN
F 1 "963356-1" H 5650 4575 50  0000 C CNN
F 2 "963356-1" H 5650 4125 50  0001 C CNN
F 3 "DOCUMENTATION" H 5650 4225 50  0001 C CNN
	3    5650 4475
	-1   0    0    -1  
$EndComp
$Comp
L 963356-1 P101
U 4 1 5A35AFDB
P 8050 2025
F 0 "P101" H 8050 1925 50  0000 C CNN
F 1 "963356-1" H 8050 2125 50  0000 C CNN
F 2 "963356-1" H 8050 1675 50  0001 C CNN
F 3 "DOCUMENTATION" H 8050 1775 50  0001 C CNN
	4    8050 2025
	-1   0    0    -1  
$EndComp
$Comp
L GND #PWR01
U 1 1 5A35D6DD
P 1225 6150
F 0 "#PWR01" H 1225 6150 30  0001 C CNN
F 1 "GND" H 1225 6080 30  0001 C CNN
F 2 "" H 1225 6150 60  0000 C CNN
F 3 "" H 1225 6150 60  0000 C CNN
	1    1225 6150
	1    0    0    -1  
$EndComp
$Comp
L TEST_1P W101
U 1 1 5A35D759
P 1225 6150
F 0 "W101" H 1225 6420 50  0000 C CNN
F 1 "TEST_1P" H 1225 6350 50  0001 C CNN
F 2 "Connect:1pin" H 1425 6150 50  0001 C CNN
F 3 "" H 1425 6150 50  0000 C CNN
	1    1225 6150
	1    0    0    -1  
$EndComp
$Comp
L TEST_1P W102
U 1 1 5A35D84D
P 1575 6150
F 0 "W102" H 1575 6420 50  0000 C CNN
F 1 "TEST_1P" H 1575 6350 50  0001 C CNN
F 2 "Connect:1pin" H 1775 6150 50  0001 C CNN
F 3 "" H 1775 6150 50  0000 C CNN
	1    1575 6150
	1    0    0    -1  
$EndComp
Wire Wire Line
	1575 6150 1225 6150
$EndSCHEMATC
