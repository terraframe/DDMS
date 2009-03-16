<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="tlds/mojoLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String rootId = (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID);
  
    String tree = GeoHierarchyDTO.defineAllowedTree(requestIF, rootId);
    JSONObject obj = new JSONObject(tree);
    JSONArray imports = obj.getJSONArray("imports");
    
    String[] types = new String[imports.length()];
    for(int i=0; i<types.length; i++)
    {
      types[i] = imports.getString(i);
    }
    
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>
  
  MDSS.GeoTreeSelectables = <%= tree %>;
  MDSS.GeoEntityTreeRootId = '<%= rootId %>';
  
</script>