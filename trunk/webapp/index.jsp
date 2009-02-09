<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>MDSS</title>
</head>
<body>
<h1>Welcome to MDSS</h1>
<mjl:commandLink action="mdss.test.GeoEntityController.viewAll.mojo"
	name="geoEntites.link" display="View GeoEntites" />
<mjl:commandLink
	action="mdss.entomology.MosquitoCollectionController.viewAll.mojo"
	name="mosquitoCollection.link" display="View Mosquioto Collections" />
<mjl:commandLink
	action="mdss.entomology.MorphologicalSpecieGroupController.viewAll.mojo"
	name="morphologicalSpecieGroup.link" display="View Morphological Speicie Groups" />
<mjl:commandLink
	action="mdss.entomology.MosquitoController.viewAll.mojo"
	name="mosquito.link" display="View Mosquiotos" />
<mjl:commandLink name="LoginController.logout"
	action="com.terraframe.mojo.defaults.LoginController.logout.mojo"
	display="Logout" />
</body>
</html>
