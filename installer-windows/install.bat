ECHO off

ECHO IMPORANT: DISABLE User Access Control before continueing if installing on VISTA
pause

IF NOT EXIST "C:\Program Files\7-Zip\7z.exe" 7zip.exe /S

IF NOT EXIST C:\MDSS "C:\Program Files\7-Zip"\7z.exe x MDSS.7z -oC:\

copy uninstall.bat C:\MDSS

set JAVA_HOME = C:\MDSS\Java\jdk1.5.0_19

rem ---------------------------------------------------------------------------
rem INSTALL POSTGRES
rem ---------------------------------------------------------------------------

echo installing postgres...
C:\MDSS\PostgreSQL\8.3\installer\server>createuser.exe .\ postgres 8aBruD5c
echo y|cacls c:\MDSS\PostgreSQL\8.3\data /E /T /C /G "postgres":F
postgresql-8.3.7-2-windows --mode unattended  --prefix C:\MDSS\PostgreSql\8.3 --superpassword 8aBruD5c 
rmdir "%HOMEPATH%\..\All Users\Start Menu\Programs\PostgreSQL 8.3" \Q \S

rem ---------------------------------------------------------------------------
rem INSTALL TOMCAT SERVICES
rem ---------------------------------------------------------------------------
C:
cd C:\MDSS
echo installing tomcat...

cd C:\MDSS\malawi\bin
call service.bat install MDSSMalawi 
tomcat6 //US//MDSSMalawi --JvmMs=256 --JvmMx=640 --JavaHome=C:\MDSS\Java\jdk1.5.0_19 --Jvm=C:\MDSS\Java\jdk1.5.0_19\jre\bin\server\jvm.dll --DependsOn postgresql-8.3

cd C:\MDSS\mozambique\bin
call service.bat install MDSSMozambique
tomcat6 //US//MDSSMozambique --JvmMs=256 --JvmMx=640 --JavaHome=C:\MDSS\Java\jdk1.5.0_19 --Jvm=C:\MDSS\Java\jdk1.5.0_19\jre\bin\server\jvm.dll --DependsOn postgresql-8.3 

cd C:\MDSS\zambia\bin
call service.bat install MDSSZambia 
tomcat6 //US//MDSSZambia --JvmMs=256 --JvmMx=640 --JavaHome=C:\MDSS\Java\jdk1.5.0_19 --Jvm=C:\MDSS\Java\jdk1.5.0_19\jre\bin\server\jvm.dll --DependsOn postgresql-8.3

cd C:\MDSS
copy "MDSS Control Panel.lnk" "%HOMEPATH%\Desktop"
copy "BIRT.lnk" "%HOMEPATH%\Desktop"

ECHO MDSS is installed...
pause