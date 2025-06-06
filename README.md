<div align="center">

<img src="https://raw.githubusercontent.com/wiki/gerefi/gerefi/Images/logo_new.png" alt="gerEFI" width="600" />

<b>GPL open-source ECU</b>

[![Release](https://img.shields.io/github/v/release/gerefi/gerefi?style=flat)](https://github.com/gerefi/gerefi/releases/latest)![Last Commit](https://img.shields.io/github/last-commit/gerefi/gerefi?style=flat)
![GitHub commits since latest release (by date)](https://img.shields.io/github/commits-since/gerefi/gerefi/latest?color=blueviolet&label=Commits%20Since%20Release)
</div>

# gerEFI

See [gcc_version_check.c](https://github.com/gerefi/gerefi/blob/master/firmware/gcc_version_check.c) for recommended version of GCC.

Current binaries are always available on our [build server](http://gerefi.com/build_server/)

[shop.gerEFI.com](https://www.shop.gerefi.com/)

# Cloning the repository
`git clone https://github.com/gerefi/gerefi.git`
Important note - we now use submodules:

`git submodule update --init`

# What do we have here?
 * [Firmware](/firmware) Source code for open source engine control unit for stm32 chips including [implementation overview](https://github.com/gerefi/gerefi/blob/master/firmware/readme.md)
 * [Hardware](/hardware) KiCAD files for our older PCBs
 * [gerEFI console](/java_console) gerEFI own native tuning software
 * [Simulator](/simulator) win32 or posix version of firmware allows to explore without any hardware
 * [Unit Tests](unit_tests) Unit of firmware pieces
 * [Misc tools](/java_tools) Misc development utilities
 * [Software Development process](misc/SoftwareDevelopmentProcess.md)

# External Links

 * [Forum](http://gerefi.com/forum)
 * [Documentation](https://github.com/gerefi/gerefi/wiki)
 * [Doxygen documentation](http://gerefi.com/docs/html)
 * [General source code Q&A](http://gerefi.com/forum/viewtopic.php?f=5&t=10)
 * [Facebook](https://www.facebook.com/gerEfiECU)
 * [YouTube](https://www.youtube.com/user/gerefi)
 * [Patreon](https://www.patreon.com/gerefi)

# Building the Code
Check out https://gerefi.com/forum/viewtopic.php?f=5&t=9

# Release Notes

See [the changelog](firmware/CHANGELOG.md)

