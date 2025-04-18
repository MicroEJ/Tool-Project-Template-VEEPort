@echo off

REM 'build.bat' implementation for Keil µVision5.

REM 'build.bat' is responsible for producing the executable file 
REM then copying this executable file to the current directory where it has been executed to a file named 'application.out'

CALL "%~dp0\set_project_env.bat"
IF %ERRORLEVEL% NEQ 0 (
	exit /B %ERRORLEVEL%
)

@echo on

"%KEIL_INSTALLATION_DIR%\UV4\UV4.exe" -b "%KEIL_PROJECT_DIR%\%KEIL_PROJECT_NAME%.uvprojx" -t "%KEIL_TARGETNAME%" -j0 -l %TEMP%\%KEIL_PROJECT_NAME%_build_log.txt

REM Print last command logs and delete associated temporary file
type %TEMP%\%KEIL_PROJECT_NAME%_build_log.txt
del %TEMP%\%KEIL_PROJECT_NAME%_build_log.txt

copy /Y %KEIL_PROJECT_EXECUTABLE_FILE% application.out
