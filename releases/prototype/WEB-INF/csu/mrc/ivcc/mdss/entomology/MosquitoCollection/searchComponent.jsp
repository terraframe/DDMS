<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="csu.mrc.ivcc.mdss.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<jsp:include page="/WEB-INF/geoEntityTreeComponent.jsp"></jsp:include>

<script type="text/javascript">

  YAHOO.util.Event.onDOMReady(function(){
    
    var opener = new YAHOO.util.Element("treeOpener");
    opener.on("click", function(){
    
      function selectHandler(geoEntity, node)
      {
        var geoId = document.getElementById('geoIdEl');
        geoId.value = geoEntity.getGeoId();
        
        MDSS.GeoEntityTree.destroyAll();
      }
    
      MDSS.GeoEntityTree.initializeTree("treeView", selectHandler);   
        
    });
  }, null, true);

</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>

<mjl:form name="csu.mrc.ivcc.mdss.entomology.MosquitoCollection.search" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt> Geo Id </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" /> <span id="treeOpener">Search</span></dd>
    <dt> Date </dt>
    <dd> <mjl:input param="collectionDate" type="text" classes="DatePick" id="collectionDate"/></dd>
  </dl>  
  <br>
  <br>
  <mjl:command classes="submitButton" action="csu.mrc.ivcc.mdss.entomology.MosquitoCollectionController.searchByGeoIdAndDate.mojo" name="search.button"
  value="Search"
  />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>

