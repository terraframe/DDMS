<%@ taglib uri="tlds/mojoLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>
<%@page import="dss.vector.solutions.geo.AllPathsDTO"%>

<%
  ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  if (request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID) == null)
  {
    //The Earth is the root.
    //FIXME: use country's default root
    EarthDTO earth = EarthDTO.getEarthInstance(requestIF);
    request.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, earth.getId());

  }
  String rootId = (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID);
  String tree = GeoHierarchyDTO.defineAllowedTree(requestIF, rootId);
%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{GeoEntityViewDTO.CLASS, AllPathsDTO.CLASS}))%>

<input type="button" value="Regenerate Paths" id="regeneratePaths" />

<script type="text/javascript">
  MDSS.GeoTreeSelectables = <%= tree %>;
  MDSS.GeoEntityTreeRootId = '<%= rootId %>';
  
  YAHOO.util.Event.on('regeneratePaths', 'click', function(){
    
    var request = new MDSS.Request({
      onSuccess : function(){ /*success*/} 
    });
    
    Mojo.$.dss.vector.solutions.geo.AllPaths.regeneratePaths(request);
  });
</script>
