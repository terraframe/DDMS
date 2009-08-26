ECHO Press CTRL+C now to cancel uninstall
pause
C:

echo removing postgres... 
net stop postgresql-8.3
rem echo y|cacls c:\MDSS\PostgreSQL\8.3\data /E /T /C /G "Users":F
C:\MDSS\PostgreSQL\8.3\uninstall-postgresql.exe
pause

net user postgres /DEL

rem ---------------------------------------------------------------------------
rem INSTALL TOMCAT SERVICES
rem ---------------------------------------------------------------------------

echo removing tomcat...

cd C:\MDSS\malawi\bin
call service.bat remove MDSSMalawi

cd C:\MDSS\mozambique\bin
call service.bat remove MDSSMozambique

cd C:\MDSS\zambia\bin
call service.bat remove MDSSZambia 

cd C:\

del "%HOMEPATH%\Desktop\MDSS Control Panel.lnk" /Q
del "%HOMEPATH%\Desktop\BRIT.lnk" /Q
rmdir C:\MDSS /S /Q

ECHO MDSS has been removed...
pause
