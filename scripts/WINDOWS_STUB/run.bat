@echo off

REM 'run.bat' implementation stub.

REM 'run.bat' is responsible for flashing the executable file on the target device 
REM then resetting target device

IF "%~1"=="" (
	SET APPLICATION_FILE=%cd%\application.out
) ELSE (
	SET APPLICATION_FILE=%~f1
)

IF NOT EXIST "%APPLICATION_FILE%" (
	echo FAILED - file '%APPLICATION_FILE%' does not exist
	exit /B 1
)

echo PASSED
