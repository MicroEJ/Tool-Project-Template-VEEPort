@echo off

:: Build: 7E4D1F7C

REM 'run.bat' implementation for IAR Embedded Workbench.

REM 'run.bat' is responsible for flashing the executable file on the target device
REM then resetting target device.

REM Every board specific value used below comes from 'set_project_env.bat': target
REM architecture, probe driver library and probe options. This script itself is board
REM agnostic. IAR writes the equivalent command line in the project 'settings' directory,
REM in '*.general.xcl' and '*.driver.xcl', whenever a debug session is started; those two
REM files are the reference when filling 'set_project_env.bat'. They are usually not
REM versioned, as they hold machine specific absolute paths.

CALL "%~dp0set_project_env.bat"
IF %ERRORLEVEL% NEQ 0 (
	exit /B %ERRORLEVEL%
)

REM 'IAREW_PROJECT_EXECUTABLE_FILE' carries its own quotes; strip them so the path can be
REM quoted at each use site instead.
SET IAREW_OUT_FILE=%IAREW_PROJECT_EXECUTABLE_FILE:"=%

IF "%~1"=="" (
	SET APPLICATION_FILE=%IAREW_OUT_FILE%
) ELSE (
	SET APPLICATION_FILE=%~1
)

IF NOT EXIST "%APPLICATION_FILE%" (
	echo FAILED - file '%APPLICATION_FILE%' does not exist
	exit /B 1
)

REM Skipped when the application already is the project executable, as copying a file onto
REM itself is an error.
IF /I NOT "%APPLICATION_FILE%"=="%IAREW_OUT_FILE%" (
	copy /Y "%APPLICATION_FILE%" "%IAREW_OUT_FILE%"
	IF %ERRORLEVEL% NEQ 0 (
		echo FAILED - could not copy '%APPLICATION_FILE%' to '%IAREW_OUT_FILE%'
		exit /B 1
	)
)

REM Optional device macro shipped with IAR.
SET IAREW_CSPY_MACRO_OPTIONS=
IF NOT "%IAREW_PROJECT_DEVICE_MACRO%"=="" SET IAREW_CSPY_MACRO_OPTIONS=--device_macro="%IAREW_INSTALLATION_DIR%\%IAREW_PROJECT_DEVICE_MACRO%"

REM Optional project setup macro. A setup macro typically reloads the image with
REM __loadImage("$EXE_DIR$\\$TARGET_FNAME$"), and that reload is what repairs the part of
REM the image erased by the reset. Both are argument variables that only the IDE expands,
REM so cspybat needs a copy whose image path is resolved. It is generated here instead of
REM being versioned, to keep machine specific absolute paths out of the repository, and
REM derived from the setup macro so the memory addresses stay defined in one place only.
REM The backslashes of the resolved path are doubled, because the IAR macro language
REM applies C string escaping.
IF "%IAREW_PROJECT_SETUP_MACRO%"=="" GOTO :flash

SET IAREW_CSPY_MACRO=%IAREW_PROJECT_DIR%\%IAREW_PROJECT_OUTPUT_FOLDER%\Exe\cspy.setup.mac
powershell -NoProfile -ExecutionPolicy Bypass -Command "$img='%IAREW_OUT_FILE:\=\\%'; $s=[IO.File]::ReadAllText('%IAREW_PROJECT_SETUP_MACRO%'); $s=$s.Replace('$EXE_DIR$\\$TARGET_FNAME$',$img); [IO.File]::WriteAllText('%IAREW_CSPY_MACRO%',$s)"
IF %ERRORLEVEL% NEQ 0 (
	echo FAILED - could not generate '%IAREW_CSPY_MACRO%' from '%IAREW_PROJECT_SETUP_MACRO%'
	exit /B 1
)
SET IAREW_CSPY_MACRO_OPTIONS=%IAREW_CSPY_MACRO_OPTIONS% --macro="%IAREW_CSPY_MACRO%"

:flash

@echo on

"%IAREW_INSTALLATION_DIR%\common\bin\cspybat" --download_only "%IAREW_INSTALLATION_DIR%\arm\bin\armproc.dll" "%IAREW_INSTALLATION_DIR%\%IAREW_PROJECT_DRIVER_DLL%" "%IAREW_OUT_FILE%" --plugin="%IAREW_INSTALLATION_DIR%\%IAREW_PROJECT_PLUGIN_DLL%" %IAREW_CSPY_MACRO_OPTIONS% --flash_loader="%IAREW_INSTALLATION_DIR%\%IAREW_PROJECT_FLASH_LOADER%" --backend "--endian=%IAREW_PROJECT_ENDIAN%" "--cpu=%IAREW_PROJECT_CPU%" "--fpu=%IAREW_PROJECT_FPU%" "-p" "%IAREW_INSTALLATION_DIR%\%IAREW_PROJECT_DESCRIPTION_FILE%" "--device=%IAREW_PROJECT_DEVICE_NAME%" %IAREW_PROJECT_DRIVER_OPTIONS%
