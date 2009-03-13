pg_dump -U mdssdeploy mdssdeploy > dump.sql 
svn info|grep "Last Changed Rev" > revision.txt
svn add * --force
svn ci -m"Update Prototype"
