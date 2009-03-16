<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="tlds/mojoLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTypeController"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyViewDTO"%>
<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String rootId = (String) request.getAttribute(GeoEntityTypeController.ROOT_GEO_HIERARCHY_ID);
  
    String[] types = {GeoHierarchyDTO.CLASS, GeoHierarchyViewDTO.CLASS, GeoEntityTypeController.CLASS};
    
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>
  
  MDSS.GeoHierarchyTreeRootId = '<%= rootId %>';
  
</script>