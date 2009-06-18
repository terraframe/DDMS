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
<%@page import="dss.vector.solutions.geo.GeoHierarchyViewDTO"%>
<%
  /*****************************************************************
    This file sets up request varibles for geo tree stuff
    It does not and should not print anything out.
  ******************************************************************/

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
  JSONObject obj = new JSONObject(tree);
  JSONArray imports = obj.getJSONArray("imports");

  int length = imports.length()+1;
  String[] types = new String[length];
  for(int i=0; i<types.length-4; i++)
  {
    types[i] = imports.getString(i);
  }
  types[length-4] = GeoEntityViewDTO.CLASS;
  types[length-3] = GeoHierarchyDTO.CLASS;
  types[length-2] = GeoHierarchyViewDTO.CLASS;
  types[length-1] = GeoEntityTreeController.CLASS;

  request.setAttribute("GeoTreeSelectables",tree);
  request.setAttribute("GeoEntityTreeRootId",rootId);
  request.setAttribute("GeoEntityUniversialTypes",Halp.join((List<String>) Arrays.asList(types),"&"));
%>