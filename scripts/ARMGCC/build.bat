@ECHO off

REM 'build.bat' implementation for GCC toolchain

REM 'build.bat' is responsible for producing the executable file 
REM then copying this executable file to the current directory where it has been executed to a file named 'application.out'

CALL "%~dp0\set_project_env.bat"
IF %ERRORLEVEL% NEQ 0 (
	ECHO "Error during set_project_env.bat"
	EXIT /B %ERRORLEVEL%
)

SET CURRENT_DIRECTORY=%CD%

CD %ARMGCC_PROJECT_MAKEFILE_DIR%
IF %ERRORLEVEL% NEQ 0 (
	ECHO "Can not open %ARMGCC_PROJECT_MAKEFILE_DIR%"
	EXIT /B %ERRORLEVEL%
)

%ARMGCC_PROJECT_BUILD_CMD% %ARMGCC_PROJECT_BUILD_CMD_OPTS%
IF %ERRORLEVEL% NEQ 0 (
	ECHO "Failed to build the firmware"
	exit /B %ERRORLEVEL%
)

ECHO "Copy binary from %ARMGCC_PROJECT_BINARY_DIR% to %CURRENT_DIRECTORY%"

copy /Y %ARMGCC_PROJECT_BINARY_DIR%\%ARMGCC_PROJECT_BINARY_NAME%.elf %CURRENT_DIRECTORY%\application.out
copy /Y %ARMGCC_PROJECT_BINARY_DIR%\%ARMGCC_PROJECT_BINARY_NAME%.elf %CURRENT_DIRECTORY%\application.elf
copy /Y %ARMGCC_PROJECT_BINARY_DIR%\%ARMGCC_PROJECT_BINARY_NAME%.hex %CURRENT_DIRECTORY%\application.hex
copy /Y %ARMGCC_PROJECT_BINARY_DIR%\%ARMGCC_PROJECT_BINARY_NAME%.bin %CURRENT_DIRECTORY%\application.bin

cd %CURRENT_DIRECTORY%
