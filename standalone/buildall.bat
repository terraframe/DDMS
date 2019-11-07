
SET "ANT_HOME=C:\Program Files (x86)\apache_ant"
SET ANT_OPTS=-Xmx1024m -XX:MaxPermSize=128M
SET "JAVA_HOME=C:\git\DDMS\installer-stage\Java\jdk1.8.0_66"
SET "JDK_HOME=C:\git\DDMS\installer-stage\Java\jdk1.8.0_66"
SET "JRE_HOME=C:\git\DDMS\installer-stage\Java\jdk1.8.0_66"

REM Copying over DDMS's runway jars to the backup manager
copy ..\DDMS\lib\client\runwaysdk-client.jar C:\git\DDMS\standalone\backup-manager-1.0.0\lib\runwaysdk-client.jar
copy ..\DDMS\lib\client\runwaysdk-gis-client.jar C:\git\DDMS\standalone\backup-manager-1.0.0\lib\runwaysdk-gis-client.jar
copy ..\DDMS\lib\common\runwaysdk-common.jar C:\git\DDMS\standalone\backup-manager-1.0.0\lib\runwaysdk-common.jar
copy ..\DDMS\lib\common\runwaysdk-gis-common.jar C:\git\DDMS\standalone\backup-manager-1.0.0\lib\runwaysdk-gis-common.jar
copy ..\DDMS\lib\server\runwaysdk-server.jar C:\git\DDMS\standalone\backup-manager-1.0.0\lib\runwaysdk-server.jar
copy ..\DDMS\lib\server\runwaysdk-gis-server.jar C:\git\DDMS\standalone\backup-manager-1.0.0\lib\runwaysdk-gis-server.jar

REM Tomcat Remote Listener
call mvn -U -f ..\dss.vector.org.json\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
call mvn -U -f ..\tomcat-remote-listener\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
call mvn -U -f ..\manager\pom.xml dependency:copy-dependencies -DoutputDirectory=C:\git\DDMS\standalone\manager-1.0.0\lib
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\tomcat-remote-listener\target\tomcat-remote-listener.jar C:\git\DDMS\standalone\manager-1.0.0\lib
if %errorlevel% neq 0 exit /b %errorlevel%

REM Manager
call mvn -U -f ..\manager\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
call mvn -U -f ..\manager\pom.xml dependency:copy-dependencies -DoutputDirectory=C:\git\DDMS\standalone\manager-1.0.0\lib
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\manager\target\manager-1.0.0.jar C:\git\DDMS\standalone\manager-1.0.0\lib
if %errorlevel% neq 0 exit /b %errorlevel%

REM DDMS Initializer
cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\ddms-initializer -buildfile ..\ddms-initializer\scripts\deploy.xml -Dmdss.admin=C:\git\DDMS\standalone -Dmdss.root=C:\git\DDMS\DDMS -Dswt.jar=C:\Users\Administrator\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%

REM Backup Manager
call mvn -U -f ..\backup-manager\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
call mvn -U -f ..\manager\pom.xml dependency:copy-dependencies -DoutputDirectory=C:\git\DDMS\standalone\backup-manager-1.0.0\lib
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\backup-manager\target\backup-manager-1.0.0.jar C:\git\DDMS\standalone\backup-manager-1.0.0\lib
if %errorlevel% neq 0 exit /b %errorlevel%

REM MDSSGISI
cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\MDSSGISI -buildfile ..\MDSSGISI\scripts\deploy.xml -Dmdss.admin=C:\git\DDMS\standalone -Dmdss.root=C:\git\DDMS\DDMS -Dswt.jar=C:\Users\Administrator\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%

REM Sync Manager
cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\synch-manager -buildfile ..\synch-manager\scripts\deploy.xml -Dmdss.admin=C:\git\DDMS\standalone -Dmdss.root=C:\git\DDMS\DDMS -Dswt.jar=C:\Users\Administrator\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%

REM Runway Patcher
cmd /c "%ANT_HOME%\bin\ant" -buildfile ..\runway-patcher\scripts\build.xml -Dbasedir=C:\git\DDMS\runway-patcher
if %errorlevel% neq 0 exit /b %errorlevel%
call mvn -U -f ..\runway-patcher\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\runway-patcher\target\runway-patcher-1.0.0.jar C:\git\DDMS\ddms-runway-patcher\lib
if %errorlevel% neq 0 exit /b %errorlevel%
