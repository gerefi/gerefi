EESchema Schematic File Version 2
LIBS:KICAD_Older_Version
LIBS:power
LIBS:device
LIBS:conn
LIBS:linear
LIBS:analog_switches
LIBS:stm32
LIBS:art-electro-conn
LIBS:art-electro-ic
LIBS:art-electro-conn_2
LIBS:logo_flipped
LIBS:crystal(mc306)
LIBS:brain_board_STM32F407_SD_wing-it-cache
EELAYER 25 0
EELAYER END
$Descr A 11000 8500
encoding utf-8
Sheet 1 1
Title "brain_board_STM32F407_SD_wing-it"
Date "2017-01-22"
Rev "R0.1"
Comp "gerEFI by DAECU"
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
Text Notes 8125 5400 0    60   ~ 0
Right conn
$Comp
L GND #PWR01
U 1 1 52CFAC0C
P 8700 7000
F 0 "#PWR01" H 8700 7000 30  0001 C CNN
F 1 "GND" H 8700 6930 30  0001 C CNN
F 2 "" H 8700 7000 60  0000 C CNN
F 3 "" H 8700 7000 60  0000 C CNN
	1    8700 7000
	0    -1   -1   0   
$EndComp
Text Label 8750 5600 0    60   ~ 0
VDD
Text Label 8750 5800 0    60   ~ 0
PB5
Text Label 8750 5900 0    60   ~ 0
PB3
Text Label 8750 6100 0    60   ~ 0
PD4
Wire Wire Line
	8600 7000 8700 7000
Wire Wire Line
	8750 5600 8600 5600
Wire Wire Line
	8750 5800 8600 5800
Wire Wire Line
	8750 5900 8600 5900
Wire Wire Line
	8750 6100 8600 6100
Connection ~ -4675 3450
Text Label 7950 5800 2    60   ~ 0
PB4
Wire Wire Line
	8100 5800 7950 5800
Text Label 9625 6125 2    60   ~ 0
PB5
Text Label 9625 6650 2    60   ~ 0
PB3
Text Label 9625 6550 2    60   ~ 0
PD4
Text Label 9625 6225 2    60   ~ 0
VDD
Text Label 9625 6025 2    60   ~ 0
PB4
$Comp
L GND #PWR02
U 1 1 5861744D
P 9625 6450
F 0 "#PWR02" H 9625 6450 30  0001 C CNN
F 1 "GND" H 9625 6380 30  0001 C CNN
F 2 "" H 9625 6450 60  0000 C CNN
F 3 "" H 9625 6450 60  0000 C CNN
	1    9625 6450
	0    1    1    0   
$EndComp
Text Notes 9975 6350 0    60   ~ 0
SD Card
$Comp
L CONN_01X03 P5
U 1 1 58618F13
P 9825 6125
F 0 "P5" H 9825 6325 50  0000 C CNN
F 1 "CONN_01X03" V 9925 6125 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Angled_1x03" H 9825 6125 50  0001 C CNN
F 3 "" H 9825 6125 50  0000 C CNN
	1    9825 6125
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X03 P6
U 1 1 58619400
P 9825 6550
F 0 "P6" H 9825 6750 50  0000 C CNN
F 1 "CONN_01X03" V 9925 6550 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Angled_1x03" H 9825 6550 50  0001 C CNN
F 3 "" H 9825 6550 50  0000 C CNN
	1    9825 6550
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X01 P7
U 1 1 5884E491
P 8400 6100
F 0 "P7" H 8525 6100 50  0000 C CNN
F 1 "CONN_01X01" V 8500 6100 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x01_Pitch2.54mm" H 8400 6100 50  0001 C CNN
F 3 "" H 8400 6100 50  0000 C CNN
	1    8400 6100
	-1   0    0    1   
$EndComp
$Comp
L CONN_01X01 P8
U 1 1 5884E591
P 8400 7000
F 0 "P8" H 8525 7000 50  0000 C CNN
F 1 "CONN_01X01" V 8500 7000 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x01_Pitch2.54mm" H 8400 7000 50  0001 C CNN
F 3 "" H 8400 7000 50  0000 C CNN
	1    8400 7000
	-1   0    0    1   
$EndComp
$Comp
L CONN_01X01 P11
U 1 1 5884F69A
P 7925 6100
F 0 "P11" H 8050 6100 50  0000 C CNN
F 1 "CONN_01X01" V 8025 6100 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x01_Pitch2.54mm" H 7925 6100 50  0001 C CNN
F 3 "" H 7925 6100 50  0000 C CNN
	1    7925 6100
	1    0    0    -1  
$EndComp
$Comp
L CONN_01X01 P12
U 1 1 5884F6E2
P 7950 7000
F 0 "P12" H 8075 7000 50  0000 C CNN
F 1 "CONN_01X01" V 8050 7000 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_1x01_Pitch2.54mm" H 7950 7000 50  0001 C CNN
F 3 "" H 7950 7000 50  0000 C CNN
	1    7950 7000
	1    0    0    -1  
$EndComp
NoConn ~ 8100 5600
NoConn ~ 8100 5900
NoConn ~ 7725 6100
NoConn ~ 7750 7000
NoConn ~ 8100 5700
NoConn ~ 8600 5700
NoConn ~ 8725 6450
NoConn ~ 8725 6550
NoConn ~ 8725 6650
NoConn ~ 9225 6650
NoConn ~ 9225 6550
NoConn ~ 9225 6450
$Comp
L CONN_02X03 P2
U 1 1 58850D8E
P 8975 6550
F 0 "P2" H 8975 6750 50  0000 C CNN
F 1 "CONN_02X03" H 8975 6350 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_2x03_Pitch2.54mm" H 8975 5350 50  0000 C CNN
F 3 "" H 8975 5350 50  0000 C CNN
	1    8975 6550
	1    0    0    -1  
$EndComp
$Comp
L CONN_02X04 P1
U 1 1 58850F4A
P 8350 5750
F 0 "P1" H 8350 6000 50  0000 C CNN
F 1 "CONN_02X04" H 8350 5500 50  0001 C CNN
F 2 "Pin_Headers:Pin_Header_Straight_2x04_Pitch2.54mm" H 8350 4550 50  0001 C CNN
F 3 "" H 8350 4550 50  0000 C CNN
	1    8350 5750
	1    0    0    -1  
$EndComp
$EndSCHEMATC
