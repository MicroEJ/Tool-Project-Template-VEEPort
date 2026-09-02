@echo off

REM 'set_project_env.bat' implementation for IAR Embedded Workbench.

REM 'set_project_env' is responsible for
REM - checking the availability of required environment variables
REM - setting project local variables for 'build.bat' and 'run.bat'

REM Required Environment Variable
REM IAR Embedded Workbench installation directory (e.g.: C:\Program Files (x86)\IAR Systems\Embedded Workbench VERSION)
REM IAREW_CHANGEIT_INSTALLATION_DIR

IF "%IAREW_CHANGEIT_INSTALLATION_DIR%" == "" (
    ECHO Please set the environment variable 'IAREW_CHANGEIT_INSTALLATION_DIR'
    exit /B -1
)

SET IAREW_INSTALLATION_DIR=%IAREW_CHANGEIT_INSTALLATION_DIR%

REM ---------------------------------------------------------------------------
REM Project
REM ---------------------------------------------------------------------------

REM Set the project variables required by the build script for IAR Embedded Workbench
REM IAREW project directory that contains the project file .ewp (e.g.: %~dp0..\)
SET IAREW_PROJECT_DIR=CHANGEIT
REM IAREW project file name without the extension .ewp (e.g.: Project)
SET IAREW_PROJECT_NAME=CHANGEIT
REM IAREW project output folder configuration (can be the project configuration or a combination with the project name)
SET IAREW_PROJECT_OUTPUT_FOLDER=CHANGEIT
REM IAREW project configuration (e.g.: Debug or Release)
SET IAREW_PROJECT_CONFIGURATION=CHANGEIT
REM IAREW description file (.ddf format) relative to IAREW_INSTALLATION_DIR (e.g.: arm\config\debugger\ST\STM32N657X0.ddf)
SET IAREW_PROJECT_DESCRIPTION_FILE=CHANGEIT
REM IAREW device name
SET IAREW_PROJECT_DEVICE_NAME=CHANGEIT

REM Name of the executable produced by the IAREW project, without the extension .out
SET IAREW_OUT_FILENAME=%IAREW_PROJECT_NAME%

REM Path of the binary file to flash into the board
SET IAREW_PROJECT_EXECUTABLE_FILE="%IAREW_PROJECT_DIR%\%IAREW_PROJECT_OUTPUT_FOLDER%\Exe\%IAREW_OUT_FILENAME%.out"

REM ---------------------------------------------------------------------------
REM Target architecture
REM
REM Passed to the cspybat backend by 'run.bat'. Must match the code generation
REM settings of the IAREW project, otherwise the debugger rejects the image.
REM ---------------------------------------------------------------------------

REM Byte order of the target (e.g.: little or big)
SET IAREW_PROJECT_ENDIAN=CHANGEIT
REM Target core (e.g.: Cortex-M4, Cortex-M7, Cortex-M55)
SET IAREW_PROJECT_CPU=CHANGEIT
REM Target floating point unit (e.g.: VFPv4_SP, VFPv5_SP, none)
SET IAREW_PROJECT_FPU=CHANGEIT

REM ---------------------------------------------------------------------------
REM Debug probe
REM
REM The driver DLL and the driver options below are probe specific: switching
REM between ST-LINK and J-Link means changing both, and nothing else.
REM IAR writes the equivalent command line in the project 'settings' directory,
REM in '*.general.xcl' and '*.driver.xcl', whenever a debug session is started.
REM Those two files are the reference when filling this section.
REM ---------------------------------------------------------------------------

REM Probe driver library relative to IAREW_INSTALLATION_DIR (e.g.: arm\bin\armjlink2.dll, arm\bin\armstlink.dll)
SET IAREW_PROJECT_DRIVER_DLL=CHANGEIT
REM cspybat plugin library relative to IAREW_INSTALLATION_DIR (e.g.: arm\bin\armbat.dll, arm\bin\armLibSupportUniversal.dll)
SET IAREW_PROJECT_PLUGIN_DLL=CHANGEIT
REM Device flash loader file relative to IAREW_INSTALLATION_DIR (e.g.: arm\config\flashloader\NXP\FlashNXPLPC5460xM4F512K.board)
SET IAREW_PROJECT_FLASH_LOADER=CHANGEIT
REM Probe specific options appended to the cspybat backend command line
REM (e.g.: --semihosting --drv_interface=SWD --drv_communication=USB0 --jlink_initial_speed=1000 --jlink_reset_strategy=0,0)
SET IAREW_PROJECT_DRIVER_OPTIONS=CHANGEIT

REM ---------------------------------------------------------------------------
REM Debugger macros (optional)
REM
REM Both variables below are optional: leave them empty when the project does
REM not use the corresponding macro, and 'run.bat' omits the matching cspybat
REM option.
REM ---------------------------------------------------------------------------

REM Device macro file shipped with IAR, relative to IAREW_INSTALLATION_DIR (e.g.: arm\config\debugger\NXP\LPC5460x.dmac)
SET IAREW_PROJECT_DEVICE_MACRO=
REM Device setup macro of the project, executed by the debugger before downloading the image (e.g.: %IAREW_PROJECT_DIR%\setup.mac)
SET IAREW_PROJECT_SETUP_MACRO=

ECHO IAREW_INSTALLATION_DIR=%IAREW_INSTALLATION_DIR%
ECHO IAREW_PROJECT_DIR=%IAREW_PROJECT_DIR%
ECHO IAREW_PROJECT_NAME=%IAREW_PROJECT_NAME%
ECHO IAREW_PROJECT_CONFIGURATION=%IAREW_PROJECT_CONFIGURATION%
ECHO IAREW_PROJECT_DESCRIPTION_FILE=%IAREW_PROJECT_DESCRIPTION_FILE%
ECHO IAREW_PROJECT_DEVICE_NAME=%IAREW_PROJECT_DEVICE_NAME%
ECHO IAREW_PROJECT_CPU=%IAREW_PROJECT_CPU%
ECHO IAREW_PROJECT_FPU=%IAREW_PROJECT_FPU%
ECHO IAREW_PROJECT_DRIVER_DLL=%IAREW_PROJECT_DRIVER_DLL%

exit /B 0
