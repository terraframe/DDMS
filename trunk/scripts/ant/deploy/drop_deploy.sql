drop database mdssdeploy;

CREATE DATABASE mdssdeploy
WITH ENCODING='UTF8'
TEMPLATE=template_postgis
OWNER=mdssdeploy;