@echo off
copy C:\MDSS\tomcat6\webapps\DDMS\WEB-INF\lib\runwaysdk-client.jar /Y C:\MDSS\manager\lib
copy C:\MDSS\tomcat6\webapps\DDMS\WEB-INF\lib\runwaysdk-common.jar /Y C:\MDSS\manager\lib
copy C:\MDSS\tomcat6\webapps\DDMS\WEB-INF\lib\runwaysdk-server.jar /Y C:\MDSS\manager\lib
echo Completed file copy.
pause