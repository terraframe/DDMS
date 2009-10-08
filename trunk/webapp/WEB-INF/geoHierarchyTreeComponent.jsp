<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="tlds/mojoLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTypeController"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyViewDTO"%>

<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.MODTO"%>
<%@page import="dss.vector.solutions.ontology.TermController"%>
<%@page import="dss.vector.solutions.ontology.MOController"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%><script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String rootId = (String) request.getAttribute(GeoEntityTypeController.ROOT_GEO_HIERARCHY_ID);
  
    String[] types = {GeoHierarchyDTO.CLASS, GeoHierarchyViewDTO.CLASS, GeoEntityTypeController.CLASS,
        BrowserRootDTO.CLASS, BrowserRootViewDTO.CLASS, BrowserRootController.CLASS, TermViewDTO.CLASS, TermDTO.CLASS};
    
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>
  
  MDSS.GeoHierarchyTreeRootId = '<%= rootId %>';
  
</script>