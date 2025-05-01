

rem on linux that would be
rem dfu-util -a 0 -D gerefi_no_asserts.dfu -R

"../misc/install/STM32_Programmer_CLI/bin/STM32_Programmer_CLI.exe" -c port=usb1 -w deliver/gerefi.hex --verify --start 0x08000000

