REM This script can be run on a slave machine (as part of the Windows Task Scheduler) to automatically restore
REM a backup from a transfer folder (which is assumed to be place there by the 'master' server.)


REM ######### Variables #############
set SHARED_DIR=C:\path\to\google-drive\DDMS

REM Note that the log file should not be the same folder as the backups
set LOGFILE=%SHARED_DIR%\logs\ddms-mirror-slave.log
set BACKUPFOLDER=%SHARED_DIR%\transfer-archive

REM The transfer folder is the folder used by a file transfer tool
set TRANSFERFOLDER=%SHARED_DIR%\transfer

REM The archive folder is where files are stored that should not be overwritten by the restore process
set ARCHIVEFOLDER=%SHARED_DIR%\transfer-archive

REM Line 36 assumes your backup folder is on C drive. Change that line if it is not.

REM ########## Variables ##############



@echo off
PATH=%PATH%;C:\MDSS\manager
:loop
if not exist "%BACKUPFOLDER%" mkdir "%BACKUPFOLDER%"
echo: 1>> "%LOGFILE%" 2>&1
echo %DATE% %TIME% 1>> "%LOGFILE%" 2>&1
echo Backup folder is %BACKUPFOLDER% 1>> "%LOGFILE%" 2>&1
echo Creating backup folder %BACKUPFOLDER%... 1>> "%LOGFILE%" 2>&1
mkdir "%BACKUPFOLDER%" 1>> "%LOGFILE%" 2>&1
echo Transfer folder is %TRANSFERFOLDER% 1>> "%LOGFILE%" 2>&1
echo Creating transfer folder %TRANSFERFOLDER%... 1>> "%LOGFILE%" 2>&1
mkdir "%TRANSFERFOLDER%" 1>> "%LOGFILE%" 2>&1
echo Archive folder is %ARCHIVEFOLDER% 1>> "%LOGFILE%" 2>&1
echo Creating archive folder %ARCHIVEFOLDER%... 1>> "%LOGFILE%" 2>&1
mkdir "%ARCHIVEFOLDER%" 1>> "%LOGFILE%" 2>&1
:: Change to the drive letter where the backup is happening or the cd command can fail
C: 1>> "%LOGFILE%" 2>&1
cd "%BACKUPFOLDER%" 1>> "%LOGFILE%" 2>&1
echo Current folder is %cd% 1>> "%LOGFILE%" 2>&1
echo Checking if there is a backup in the transfer folder.  If not, this process will exit... 1>> "%LOGFILE%" 2>&1
dir /a-d "%TRANSFERFOLDER%\*.zip" || exit 2>&1
echo Moving backup file(s) from the transfer folder to the backup folder... 1>> "%LOGFILE%" 2>&1
move "%TRANSFERFOLDER%"\* "%BACKUPFOLDER%" 1>> "%LOGFILE%" 2>&1
echo Finding latest backup... 1>> "%LOGFILE%" 2>&1
FOR /F "delims=|" %%I IN ('DIR "*.*" /B /A-D /O:D') DO SET NEWESTFILE="%%I" 1>> "%LOGFILE%" 2>&1
echo Newest file found is %NEWESTFILE% 1>> "%LOGFILE%" 2>&1
echo Archiving GeoServer properties for application %1... 1>> "%LOGFILE%" 2>&1
copy C:\MDSS\tomcat\webapps\%1\WEB-INF\classes\GeoServer.properties "%ARCHIVEFOLDER%"  1>> "%LOGFILE%" 2>&1
echo Starting the restore process for application %1... 1>> "%LOGFILE%" 2>&1
powershell -Command "powershell -ExecutionPolicy ByPass -File C:\MDSS\manager\manager.ps1 -stopApp %1" 1>> "%LOGFILE%" 2>&1
powershell -Command "powershell -ExecutionPolicy ByPass -File C:\MDSS\manager\manager.ps1 -restore %1 -filename %NEWESTFILE%" 1>> "%LOGFILE%" 2>&1
powershell -Command "powershell -ExecutionPolicy ByPass -File C:\MDSS\manager\manager.ps1 -startApp %1" 1>> "%LOGFILE%" 2>&1
echo Restoring archived GeoServer.properties file... 1>> "%LOGFILE%" 2>&1
copy "%ARCHIVEFOLDER%\GeoServer.properties" C:\MDSS\tomcat\webapps\%1\WEB-INF\classes\ 1>> "%LOGFILE%" 2>&1
echo Removing sync file %NEWESTFILE%... 1>> "%LOGFILE%" 2>&1
del "%NEWESTFILE%" 1>> "%LOGFILE%" 2>&1
echo: 1>> "%LOGFILE%" 2>&1
echo Process completed for %1 %DATE% %TIME% 1>> "%LOGFILE%" 2>&1
echo: 1>> "%LOGFILE%" 2>&1
shift
if "%1"=="" goto end
goto loop
:end
