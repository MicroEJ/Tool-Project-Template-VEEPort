@echo off

REM 'run.bat' implementation for STM32 Cube Programmer.
REM 'run.bat' is responsible for flashing the executable file on the target device then resetting target device

CALL "%~dp0\set_project_env.bat"
IF %ERRORLEVEL% NEQ 0 (
	exit /B %ERRORLEVEL%
)

IF "%~1"=="" (
	SET APPLICATION_FILE=%cd%\application.out
) ELSE (
	SET APPLICATION_FILE=%~1
)

IF NOT EXIST "%APPLICATION_FILE%" (
	echo FAILED - file '%APPLICATION_FILE%' does not exist
	exit /B 1
)

REM Copy the binary file generated by build.bat
REM IMPORTANT: Update the environment variable TOOLCHAIN_PROJECT_EXECUTABLE_FILE to match with yours!
copy /Y "%APPLICATION_FILE%" %TOOLCHAIN_PROJECT_EXECUTABLE_FILE%

@echo on

"%STLINK_CUBE_PROGRAMMER_INSTALL_DIR%\bin\STM32_Programmer_CLI.exe" -c port="%STLINK_TARGET_INTERFACE%" mode="%STLINK_MODE%" -d "%APPLICATION_FILE%" "%STLINK_START_ADDRESS%" -v -rst
