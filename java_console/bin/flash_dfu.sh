#!/bin/bash

sudo dfu-util --alt 0 --download gerefi.bin -s 0x8000000:leave
