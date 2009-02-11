<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<div id="navMenu">
<mjl:commandLink action="mdss.test.GeoEntityController.viewAll.mojo"
	name="geoEntites.link" display="View GeoEntites" />
	
<mjl:commandLink 
	action="mdss.entomology.MosquitoCollectionController.viewAll.mojo" 
	name="mosquitoCollection.link" display="View Mosquioto Collections (MDSS024)" />

<mjl:commandLink
	action="mdss.entomology.MorphologicalSpecieGroupController.viewAll.mojo"
	name="morphologicalSpecieGroup.link" display="View Morphological Speicie Groups" />
	
<mjl:commandLink
	action="mdss.entomology.MosquitoController.viewAll.mojo"
	name="mosquito.link" display="View Mosquiotos" />
	
<mjl:commandLink name="LoginController.logout"
	action="com.terraframe.mojo.defaults.LoginController.logout.mojo"
	display="Logout" />
</div>