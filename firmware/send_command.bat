rem
rem auto-detects connected running gerEfi serial port and send text 'reboot' command
rem

set command=%1
echo "Command: [%command%]"

java -jar ../console/gerefi_console.jar send_command %command%
