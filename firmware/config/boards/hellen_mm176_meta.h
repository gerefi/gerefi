#pragma once

#define MM176_IGN1 C13
#define MM176_IGN2 E5
#define MM176_IGN3 E4
#define MM176_IGN4 E3
#define MM176_IGN5 E2
#define MM176_IGN6 B8
#define MM176_IGN7 B9
#define MM176_IGN8 E6

#define MM176_INJ1 G7
#define MM176_INJ2 G8
#define MM176_INJ3 D11
#define MM176_INJ4 D10
#define MM176_INJ5 D9
#define MM176_INJ6 F12
#define MM176_INJ7 F13
#define MM176_INJ8 F14

#define MM176_OUT_PWM1 D13
#define MM176_OUT_PWM2 C6
#define MM176_OUT_PWM3 C7
#define MM176_OUT_PWM4 C8
#define MM176_OUT_PWM5 C9
#define MM176_OUT_PWM6 D14
#define MM176_OUT_PWM7 D15
#define MM176_OUT_PWM8 D12
#define MM176_OUT_PWM9 H12
#define MM176_OUT_PWM10 H11
#define MM176_OUT_PWM11 H10
#define MM176_OUT_PWM12 H9
#define MM176_OUT_PWM13 H6
#define MM176_OUT_PWM14 I7
#define MM176_OUT_PWM15 I6
#define MM176_OUT_PWM16 I5
#define MM176_OUT_PWM17 I2
#define MM176_OUT_PWM18 I0

#define MM176_OUT_IO1 D3
#define MM176_OUT_IO2 A9
#define MM176_OUT_IO3 G14
#define MM176_OUT_IO4 G5
#define MM176_OUT_IO5 D2
#define MM176_OUT_IO6 G11
#define MM176_OUT_IO7 G3
#define MM176_OUT_IO8 G4
#define MM176_OUT_IO9 G13
#define MM176_OUT_IO10 G12
#define MM176_OUT_IO11 G2
#define MM176_OUT_IO12 A8
#define MM176_OUT_IO13 G6


#define MM176_IN_CRANK B1
#define MM176_IN_CRANK_ANALOG EFI_ADC_9
#define MM176_IN_CAM A6
#define MM176_IN_CAM_ANALOG EFI_ADC_6
#define MM176_IN_VSS F11
#define MM176_IN_TPS_ANALOG EFI_ADC_4
#define MM176_IN_TPS2_ANALOG EFI_ADC_20
#define MM176_IN_PPS1_ANALOG EFI_ADC_3
#define MM176_IN_PPS2_ANALOG EFI_ADC_19
#define MM176_IN_IAT_ANALOG EFI_ADC_13
#define MM176_IN_AT1_ANALOG EFI_ADC_29
#define MM176_IN_CLT_ANALOG EFI_ADC_12
#define MM176_IN_AT2_ANALOG EFI_ADC_28
#define MM176_IN_O2S_ANALOG EFI_ADC_1
#define MM176_IN_O2S2_ANALOG EFI_ADC_17
#define MM176_IN_MAP1_ANALOG EFI_ADC_10
#define MM176_IN_MAP2_ANALOG EFI_ADC_26
#define MM176_IN_AUX1_ANALOG EFI_ADC_8
#define MM176_IN_AUX2_ANALOG EFI_ADC_24
#define MM176_IN_AUX3_ANALOG EFI_ADC_15
#define MM176_IN_AUX4_ANALOG EFI_ADC_31
#define MM176_IN_MAP3 A2
#define MM176_IN_MAP3_ANALOG EFI_ADC_2
#define MM176_IN_AUX5 E9
#define MM176_IN_AUX5_ANALOG EFI_ADC_14
#define MM176_IN_AUX6 E11
#define MM176_IN_AUX6_ANALOG EFI_ADC_7
#define MM176_IN_AUX7 C1
#define MM176_IN_AUX7_ANALOG EFI_ADC_11
#define MM176_IN_AUX8 A0
#define MM176_IN_AUX8_ANALOG EFI_ADC_0

//RES1-RES3 SENS1-SENS4 no code to use ADC3 as analog
#define MM176_IN_RES1 F9
#define MM176_ADC_IN_RES1 ADC_CHANNEL_IN7
#define MM176_IN_RES2 F10
#define MM176_ADC_IN_RES2 ADC_CHANNEL_IN8
#define MM176_IN_RES3 F8
#define MM176_ADC_IN_RES3 ADC_CHANNEL_IN6
#define MM176_IN_SENS1 F3
#define MM176_IN_SENS2 F4
#define MM176_IN_SENS3 F5
#define MM176_IN_SENS4 F6
//KNOCK not defined

#define MM176_IN_D1 E12
#define MM176_IN_D2 E13
#define MM176_IN_D3 E14
#define MM176_IN_D4 E15

#define MM176_IN_VBATT EFI_ADC_5

#define MM176_SPI1_MOSI B5
#define MM176_SPI1_MISO B4
#define MM176_SPI1_SCK B3
//MM176_SPI1_CS1 matches H_SPI1_CS1
#define MM176_SPI1_CS2 B7

#define MM176_SPI2_MOSI B15
#define MM176_SPI2_MISO B14
#define MM176_SPI2_SCK B13
#define MM176_SPI2_CS B12

#define MM176_SPI3_MOSI C12
#define MM176_SPI3_MISO C11
#define MM176_SPI3_SCK C10
#define MM176_SPI3_CS A15

#define MM176_CAN_RX D0
#define MM176_CAN_TX D1

#define MM176_UART8_RX E0
#define MM176_UART8_TX E1

#define MM176_UART2_RX D6
#define MM176_UART2_TX D5

#define MM176_USB1ID A10

#define MM176_GP1 D4
#define MM176_GP2 D7
#define MM176_GP3 G10
#define MM176_GP4 G9
#define MM176_GP5 G15
#define MM176_GP6 D8
#define MM176_GP7 F15
//GP8 = PWR_EN
#define MM176_GP8 E10
#define MM176_EN_PIN MM176_GP8
//GP9 = MUX
#define MM176_GP9 F2
#define MM176_GP10 I3
#define MM176_GP11 I1
#define MM176_GP12 H15
#define MM176_GP13 H14
#define MM176_GP14 H13
#define MM176_GP15 H8
#define MM176_GP16 H7
#define MM176_GP17 H5
#define MM176_GP18 H4
#define MM176_GP19 H3
#define MM176_GP20 H2
#define MM176_GP21 I11
#define MM176_GP22 I10
#define MM176_GP23 I9
#define MM176_GP24 I8
#define MM176_GP25 I4

#define MM176_LED1_RED G0
#define MM176_LED2 G1
#define MM176_LED3 E7
#define MM176_LED4 E8
