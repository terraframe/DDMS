
SET ANT_HOME=C:\Program Files\Java\apache-ant-1.9.4
SET ANT_OPTS=-Xmx1024m -XX:MaxPermSize=128M
SET JAVA_HOME=C:\Program Files\Java\jdk1.6.0_45
SET JDK_HOME=C:\Program Files\Java\jdk1.6.0_45
SET JRE_HOME=C:\Program Files\Java\jre6

call mvn -U -f ..\tomcat-remote-listener\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\tomcat-remote-listener\target\tomcat-remote-listener-1.0.1.jar C:\svn\ddms\standalone\manager-1.0.0\lib\tomcat-remote-listener-1.0.1.jar
if %errorlevel% neq 0 exit /b %errorlevel%

call mvn -U -f ..\manager\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\manager\target\manager-1.0.0.jar C:\svn\ddms\standalone\manager-1.0.0\lib\manager-1.0.0.jar
if %errorlevel% neq 0 exit /b %errorlevel%

cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\ddms-initializer -buildfile ..\ddms-initializer\scripts\deploy.xml -Dmdss.admin=C:\svn\ddms\standalone -Dmdss.root=C:\svn\ddms\trunk -Dswt.jar=C:\Users\TerraFrame\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%

call mvn -U -f ..\backup-manager\pom.xml clean install
if %errorlevel% neq 0 exit /b %errorlevel%
xcopy /y ..\backup-manager\target\backup-manager-1.0.0.jar C:\svn\ddms\standalone\backup-manager-1.0.0\lib\backup-manager-1.0.0.jar
if %errorlevel% neq 0 exit /b %errorlevel%

cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\MDSSGISI -buildfile ..\MDSSGISI\scripts\deploy.xml -Dmdss.admin=C:\svn\ddms\standalone -Dmdss.root=C:\svn\ddms\trunk -Dswt.jar=C:\Users\TerraFrame\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%

cmd /c "%ANT_HOME%\bin\ant" -Dbasedir=..\synch-manager -buildfile ..\synch-manager\scripts\deploy.xml -Dmdss.admin=C:\svn\ddms\standalone -Dmdss.root=C:\svn\ddms\trunk -Dswt.jar=C:\Users\TerraFrame\.m2\repository\org\eclipse\swt\org\eclipse\swt\win32\win32\x86_64\4\3\swt\org.eclipse.swt.win32.win32.x86_64\4.3\org.eclipse.swt.win32.win32.x86_64-4.3.jar deploy
if %errorlevel% neq 0 exit /b %errorlevel%