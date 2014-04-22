:::::::::::::::::::::::::::::::::::::::::
:: Automatically check & get admin rights
:::::::::::::::::::::::::::::::::::::::::
@echo off
CLS 
ECHO.
ECHO =============================
ECHO Running Admin shell
ECHO =============================

:checkPrivileges 
NET FILE 1>NUL 2>NUL
if '%errorlevel%' == '0' ( goto gotPrivileges ) else ( goto getPrivileges ) 

:getPrivileges 
if '%1'=='ELEV' (shift & goto gotPrivileges)  
ECHO. 
ECHO **************************************
ECHO Invoking UAC for Privilege Escalation 
ECHO **************************************

setlocal DisableDelayedExpansion
set "batchPath=%~0"
setlocal EnableDelayedExpansion
ECHO Set UAC = CreateObject^("Shell.Application"^) > "%temp%\OEgetPrivileges.vbs" 
ECHO UAC.ShellExecute "!batchPath!", "ELEV", "", "runas", 1 >> "%temp%\OEgetPrivileges.vbs" 
"%temp%\OEgetPrivileges.vbs" 
exit /B 

:gotPrivileges 
::::::::::::::::::::::::::::
:START
::::::::::::::::::::::::::::
setlocal & pushd .

REM Run shell as admin (example) - put here code as you like
start JAVA_HOME_VALUE\bin\javaw.exe -Djavax.rmi.ssl.client.enabledProtocols=TLSv1 -Djavax.rmi.ssl.client.enabledCipherSuites=SSL_RSA_WITH_RC4_128_MD5 -Djavax.net.ssl.trustStorePassword=1206b6579Acb3 -Djavax.net.ssl.trustStore=C:\MDSS\manager\keystore\ddms.ts -Djavax.net.ssl.keyStorePassword=4b657920666fZ -Djavax.net.ssl.keyStore=C:\MDSS\manager\keystore\ddms.ks -Djavax.rmi.ssl.enabled=true -cp "C:\MDSS\manager\manager-1.0.0\classes\;C:\MDSS\manager\manager-1.0.0\lib\*" dss.vector.solutions.manager.ServerManagerWindow