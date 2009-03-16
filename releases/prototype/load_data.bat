net stop tomcat6

svn up

psql -U postgres postgres < drop_mdss.sql

psql -U mdssdeploy mdssdeploy < dump.sql


net start tomcat6