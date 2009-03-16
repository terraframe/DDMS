pg_dump -U mdssdeploy mdssdeploy > dump.sql 
svn add * --force
svn ci -m"Update Prototype"
