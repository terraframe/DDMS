@echo off
rem ant -buildfile cc-build.xml 

SET ANT_HOME=C:\Program Files\Java\apache-ant-1.9.4
SET ANT_OPTS=-Xmx1024m -XX:MaxPermSize=128M
SET JAVA_HOME=C:\Program Files\Java\jdk1.6.0_45
SET JDK_HOME=C:\Program Files\Java\jdk1.6.0_45
SET JRE_HOME=C:\Program Files\Java\jre6


rem "%ANT_HOME%\bin\ant" -buildfile cc-build.xml -Dpostgres.bin="C:/Program Files/PostgreSQL/8.4/bin"  build_installer_exe
cls
cmd /c "%ANT_HOME%\bin\ant" -buildfile build.xml email-only