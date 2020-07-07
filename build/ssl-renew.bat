
REM This script to be used for renewing SSL certificates for DDMS using LetsEncrypt
REM See https://github.com/terraframe/DDMS/wiki/HTTPS-Setup-with-Lets-Encrypt

REM ##### Variables #########
SET DOMAIN=indiaentoddms.co.in
SET EMAIL=Rinki.Deb@lstmed.ac.uk
SET KEYSTORE_PASSWORD=changeit
SET BACKUP_DIR=C:\Users\Administrator\Dropbox\ssl
REM ##### Variables #########




for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "YY=%dt:~2,2%" & set "YYYY=%dt:~0,4%" & set "MM=%dt:~4,2%" & set "DD=%dt:~6,2%"
set "HH=%dt:~8,2%" & set "Min=%dt:~10,2%" & set "Sec=%dt:~12,2%"

rmdir /s /q C:\ssl-working\workdir
rmdir /s /q C:\ssl-working\well-known
rmdir /s /q C:\MDSS\tomcat\webapps\ROOT\.well-known

powershell -Command "powershell -ExecutionPolicy ByPass -NoLogo -NonInteractive -NoProfile -WindowStyle Hidden -File C:\MDSS\manager\manager.ps1 -stopTomcat"
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError  )

echo x=msgbox("Open the manager settings and disable HTTPS. If the server is currently running, stop it.", 0, "DDMS SSL Certificate Renewal") > msgbox.vbs
start /wait msgbox.vbs
del /F /Q msgbox.vbs

set BACKUP_DATE=%YYYY%-%MM%-%DD%
copy certdir\* %BACKUP_DIR%\certdir-%BACKUP_DATE%\*

mkdir C:\ssl-working\workdir
mkdir C:\ssl-working\well-known
mkdir C:\ssl-working\well-known\acme-challenge
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

C:\ssl-working\jdk\bin\java.exe -jar acme_client.jar --command order-certificate -a account.key -w ./workdir -c ./%DOMAIN%.csr --well-known-dir ./well-known/acme-challenge --one-dir-for-well-known
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

mkdir C:\MDSS\tomcat\webapps\ROOT\.well-known
mkdir C:\MDSS\tomcat\webapps\ROOT\.well-known\acme-challenge
copy C:\ssl-working\well-known\acme-challenge\* C:\MDSS\tomcat\webapps\ROOT\.well-known\acme-challenge\*
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

powershell -Command "powershell -ExecutionPolicy ByPass -NoLogo -NonInteractive -NoProfile -WindowStyle Hidden -File C:\MDSS\manager\manager.ps1 -startTomcat"
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

del /F /Q certdir
mkdir certdir
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

C:\ssl-working\jdk\bin\java.exe -jar acme_client.jar --command generate-certificate -a ./account.key -w workdir --csr %DOMAIN%.csr --cert-dir certdir
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

C:\ssl-working\jdk\bin\keytool.exe -importcert -alias %DOMAIN% -keystore cacerts.jks -storepass changeit -file .\certdir\fullchain.pem
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

echo x=msgbox("Open the manager settings and re-enable HTTPS. For the keystore location set it to C:/ssl-working/cacerts.jks. For the keystore alias type in %DOMAIN%. For the keystore pass type in %KEYSTORE_PASSWORD%", 0, "DDMS SSL Certificate Renewal") > msgbox.vbs
start /wait msgbox.vbs
del /F /Q msgbox.vbs

powershell -Command "powershell -ExecutionPolicy ByPass -NoLogo -NonInteractive -NoProfile -WindowStyle Hidden -File C:\MDSS\manager\manager.ps1 -startTomcat"
IF %ERRORLEVEL% NEQ 0 ( CALL :HandleError )

echo x=msgbox("Your certficiate should be renewed successfully. You may view your new certificate by accessing the webserver via firefox and following the instructions here: https://support.mozilla.org/en-US/kb/secure-website-certificate#w_view-a-certificate", 0, "DDMS SSL Certificate Renewal") > msgbox.vbs
start /wait msgbox.vbs
del /F /Q msgbox.vbx

exit 0


REM ##### Functions #########

:HandleError 
echo x=msgbox("An error occurred while renewing your SSL certificate. Check C:\ssl-working\renew.log and C:\var\log\acme\acme.log for more information.", 0, "DDMS SSL Certificate Renewal") > msgbox.vbs
start /wait msgbox.vbs
del /F /Q msgbox.vbs
exit 1
