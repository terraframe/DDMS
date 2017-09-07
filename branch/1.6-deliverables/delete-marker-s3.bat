@echo off
rem ant -buildfile cc-build.xml 

SET "ANT_HOME=C:\Program Files (x86)\apache_ant"
SET ANT_OPTS=-Xmx1024m -XX:MaxPermSize=128M
SET JAVA_HOME=C:\svn\ddms\installer-stage\Java\jdk1.6.0_16
SET JDK_HOME=C:\svn\ddms\installer-stage\Java\jdk1.6.0_16
SET JRE_HOME=C:\svn\ddms\installer-stage\Java\jdk1.6.0_16\jre


rem "%ANT_HOME%\bin\ant" -buildfile cc-build.xml -Dpostgres.bin="C:/Program Files/PostgreSQL/8.4/bin"  build_installer_exe
cls
cmd /c "%ANT_HOME%\bin\ant" -buildfile build.xml delete-marker-s3