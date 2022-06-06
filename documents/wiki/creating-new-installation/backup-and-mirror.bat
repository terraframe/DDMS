setlocal

REM This script can be used to automatically take periodic backups and place them in a transfer directory for mirroring. TODO : Not tested with multiple apps

REM ##### Variables #########
SET DDMS_DIR=C:\path\to\google-drive\DDMS
SET BACKUP_DIR=%DDMS_DIR%\backups
SET BACKUP_LOG=%DDMS_DIR%\logs\backup.log
SET TRANSFER_DIR=%DDMS_DIR%\transfer
REM ##### Variables #########

powershell -Command "powershell -ExecutionPolicy ByPass -File C:\MDSS\manager\manager.ps1 -backupAll \"%BACKUP_DIR%\" > \"%BACKUP_LOG%\""


set srcDir=%BACKUP_DIR%
set destdir=%TRANSFER_DIR%
set lastmod=
pushd %srcDir%
for /f "tokens=*" %%a in ('dir *.zip /b /o-d /a-d 2^>NUL') do set lastmod=%%a & goto :xx
:xx
if "%lastmod%"=="" echo Could not locate files.&goto :eof
:::
copy "%lastmod%" "%TRANSFER_DIR%"
