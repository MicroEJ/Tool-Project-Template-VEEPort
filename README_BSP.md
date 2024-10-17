# <BOARD_NAME> BSP

This project contains the BSP sources of the VEE Port for the
<BOARD_NAME>. The BSP project is based on <BSP_FULL_NAME> (<BSP_SHORT_NAME>).

This document does not describe how to setup the VEE Port. Please
refer to the [README](./README.md) for that.

## Build & Run Scripts

In the directory `bsp/vee/scripts/` are scripts that can be
used to build and flash the BSP.  The `.bat` and `.sh` scripts are
meant to run in a Windows and Linux environment respectively.

- The `build*` scripts are used to compile and link the BSP with a
  MicroEJ Application to produce a MicroEJ Firmware
  (`application.out`) that can be flashed on a device.

  The `build*` scripts work out of the box, assuming the toolchain is
  installed in the default path.

- The `run*` scripts are used to flash a MicroEJ Firmware
  (`application.out`) on a device.

<The following environment variables are customizable:  

- `XXX`: Variable meaning.
- ...

See the comments for each variables in the scripts for a detailed
explanation.>

The environment variables can be defined globally by the user or in
the `set_local_env*` scripts.  When the `.bat` (`.sh`) scripts
are executed, the `set_local_env.bat` (`set_local_env.sh`) script
is executed if it exists.  Create and configure these files to
customize the environment locally.  Template files are provided as
example, see `set_local_env.bat.tpl` and `set_local_env.sh.tpl`.

## Customize BSP

<To change the <BSP_SHORT_NAME> BSP configuration, ...>

## Flash the Board

The `run*` scripts can also be used to flash the device with the
MicroEJ Firmware.

<list other flash methods here>

## Debugging with <BOARD_NAME>

<Describe debug setup and launch steps here>

## Dependencies

The following dependencies are included as part of the BSP:

<List dependencies here>

---
_Markdown_  
_Copyright 2024 MicroEJ Corp. All rights reserved._  
_Use of this source code is governed by a BSD-style license that can be found with this software._  