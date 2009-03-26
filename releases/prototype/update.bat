cd "C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\MDSS\"

net stop tomcat6

svn up

psql -U postgres -h  66.116.103.234 postgres < drop_mdss.sql

psql -U mdssdeploy -h  66.116.103.234 mdssdeploy < dump.sql


net start tomcat6