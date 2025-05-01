open source SAE J2716 stm32 implementation

https://github.com/gerefi/gerefi/wiki/SENT-ETB-Electronic-Throttle-Body

for unit test see https://github.com/gerefi/gerefi/tree/master/unit_tests/tests/sent

On microGerEFI only "23 - AN temp 2" could be used for SENT since that's the only pin suitable RC filter and input capture peripheral (PA1/ICU2)


On microGerEFI 0.5.2 and newer C54 would need to be removed. https://gerefi.com/docs/ibom/micro_gerEFI_R0.6.0.html

On microGerEFI 0.5.0 unforunatelly complete CN17 array on the back would need to be removed.



https://www.youtube.com/watch?v=AqfWQeWSuPA
https://www.youtube.com/watch?v=juON_deDLA4
