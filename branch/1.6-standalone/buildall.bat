
SET "ANT_HOME=C:\Program Files (x86)\apache_ant"
SET ANT_OPTS=-Xmx1024m -XX:MaxPermSize=128M
SET "JAVA_HOME=C:\svn\ddms\installer-stage\Java\jdk1.8.0_66"
SET "JDK_HOME=C:\svn\ddms\installer-stage\Java\jdk1.8.0_66"
SET "JRE_HOME=C:\svn\ddms\installer-stage\Java\jdk1.8.0_66"

REM Copying over DDMS's runway jars to the backup manager
copy ..\trunk\lib\client\runwaysdk-client.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\runwaysdk-client.jar
copy ..\trunk\lib\client\runwaysdk-gis-client.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\runwaysdk-gis-client.jar
copy ..\trunk\lib\common\runwaysdk-common.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\runwaysdk-common.jar
copy ..\trunk\lib\common\runwaysdk-gis-common.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\runwaysdk-gis-common.jar
copy ..\trunk\lib\server\runwaysdk-server.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\runwaysdk-server.jar
copy ..\trunk\lib\server\runwaysdk-gis-server.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\runwaysdk-gis-server.jar

call mvn -U -f ..\tomcat-remote-listener\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\tomcat-remote-listener\target\tomcat-remote-listener-1.0.1.jar C:\svn\ddms\standalone\manager-1.0.0\lib\tomcat-remote-listener-1.0.1.jar
if %errorlevel% neq 0 exit /b %errorlevel%

call mvn -U -f ..\manager\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\manager\target\manager-1.0.0.jar C:\svn\ddms\standalone\manager-1.0.0\lib\manager-1.0.0.jar
if %errorlevel% neq 0 exit /b %errorlevel%

cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\ddms-initializer -buildfile ..\ddms-initializer\scripts\deploy.xml -Dmdss.admin=C:\svn\ddms\standalone -Dmdss.root=C:\svn\ddms\trunk -Dswt.jar=C:\Users\Administrator\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%

call mvn -U -f ..\backup-manager\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\backup-manager\target\backup-manager-1.0.0.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\backup-manager-1.0.0.jar
if %errorlevel% neq 0 exit /b %errorlevel%

cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\MDSSGISI -buildfile ..\MDSSGISI\scripts\deploy.xml -Dmdss.admin=C:\svn\ddms\standalone -Dmdss.root=C:\svn\ddms\trunk -Dswt.jar=C:\Users\Administrator\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%

cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\synch-manager -buildfile ..\synch-manager\scripts\deploy.xml -Dmdss.admin=C:\svn\ddms\standalone -Dmdss.root=C:\svn\ddms\trunk -Dswt.jar=C:\Users\Administrator\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%