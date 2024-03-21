<div align="center">

<img src="https://github.com/gerefi/DELTA_Pro_Case/blob/8cc30090da11b7ad1b4755dbd38c58cf7fddcf88/image.png" alt="gerefi" width="700" />


[![Release](https://img.shields.io/github/v/release/gerefi/gerefi?style=flat)](https://github.com/gerefi/gerefi/releases/latest)![Last Commit](https://img.shields.io/github/last-commit/gerefi/gerefi?style=flat)
![GitHub commits since latest release (by date)](https://img.shields.io/github/commits-since/gerefi/gerefi/latest?color=blueviolet&label=Commits%20Since%20Release)
</div>

# gerefi


See [gcc_version_check.c](https://github.com/gerefi/gerefi/blob/master/firmware/gcc_version_check.c) for recommended version of GCC.

Current binaries are always available on our [build server](http://gerefi.com/build_server/)



# Cloning the repository
`git clone https://github.com/gerefi/gerefi.git`
Important note - we now use submodules:

`git submodule update --init`

# What do we have here?
 * [Firmware](/firmware) Source code for open source engine control unit for stm32 chips incuding [implementation overview](https://github.com/gerefi/gerefi/blob/master/firmware/readme.md)
 * [Hardware](/hardware) KiCAD files for our older PCBs
 * [gerefi console](/java_console) gerefi own naive tuning software
 * [Simulator](/simulator) win32 or posix version of firmware allows to explore without any hardware 
 * [Unit Tests](/unit_tests) Unit tests of firmware pieces
 * [Misc tools](/java_tools) Misc development utilities
 * [Software Development process](misc/SoftwareDevelopmentProcess.md)



