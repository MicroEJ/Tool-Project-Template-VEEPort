@echo off

REM 'run.bat' implementation for Keil µVision5.

REM 'run.bat' is responsible for flashing the executable file on the target device 
REM then resetting target device

CALL "%~dp0\set_project_env.bat"
IF %ERRORLEVEL% NEQ 0 (
	exit /B %ERRORLEVEL%
)

@echo on 

copy /Y application.out %KEIL_PROJECT_EXECUTABLE_FILE%
"%KEIL_INSTALLATION_DIR%\UV4\UV4.exe" -f "%KEIL_PROJECT_DIR%\%KEIL_PROJECT_NAME%.uvprojx" -t "%KEIL_TARGETNAME%" -j0 -l %TEMP%\%KEIL_PROJECT_NAME%_build_log.txt

REM Print last command logs and delete associated temporary file
type %TEMP%\%KEIL_PROJECT_NAME%_build_log.txt
del %TEMP%\%KEIL_PROJECT_NAME%_build_log.txt
