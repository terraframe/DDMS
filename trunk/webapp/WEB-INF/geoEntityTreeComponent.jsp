<%@ taglib uri="tlds/runwayLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>
<%@page import="dss.vector.solutions.geo.AllPathsDTO"%>
<%@page import="dss.vector.solutions.DefaultGeoEntityDTO"%>
<%@page import="dss.vector.solutions.geo.ChildEntityOverflowInformationDTO"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityEntityLabelDTO"%>

<%
  ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

  String rootId;
  String tree;
  if(request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID) != null)
  {
    rootId = (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID);
    tree = GeoHierarchyDTO.defineAllowedTree(requestIF, rootId);
  }
  else
  {
    rootId = DefaultGeoEntityDTO.getDefaultGeoEntity(requestIF).getValue(DefaultGeoEntityDTO.GEOENTITY);
    tree = GeoHierarchyDTO.defineAllowedTree(requestIF, EarthDTO.getEarthInstance(requestIF).getId());
  }
  
  // Force the root, which will be used by calling code such as 101 and 061
  request.setAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID, rootId);
  
%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{GeoEntityViewDTO.CLASS, AllPathsDTO.CLASS, ChildEntityOverflowInformationDTO.CLASS, GeoEntityEntityLabelDTO.CLASS}))%>


<script type="text/javascript">
  MDSS.GeoTreeSelectables = <%= tree %>;
</script>

<iframe id="exportIframe" name="exportIframe" style="display: none; width: 1px; height: 1px;"></iframe>
