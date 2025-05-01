rem
rem auto-detects connected running gerEfi serial port and send text 'reboot' command
rem flashes DFU
rem

echo Sending gerEFI DFU request
java -jar ../console/gerefi_console.jar reboot_dfu
echo Now sleeping before DFU
sleep 5
echo Invoking DFU process
call flash_dfu.bat
