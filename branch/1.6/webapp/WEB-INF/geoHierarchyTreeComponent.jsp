<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="tlds/runwayLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTypeController"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyViewDTO"%>

<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermController"%>
<%@page import="dss.vector.solutions.ontology.BrowserFieldDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootViewDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootController"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>

<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String rootId = (String) request.getAttribute(GeoEntityTypeController.ROOT_GEO_HIERARCHY_ID);
  
    String[] types = {GeoHierarchyDTO.CLASS, GeoHierarchyViewDTO.CLASS, GeoEntityTypeController.CLASS, BrowserRootController.CLASS};
    
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>
  
  MDSS.DefaultRoot = '<%= rootId %>';
  
</script>

<iframe id="exportIframe" name="exportIframe" style="display: none; width: 1px; height: 1px;"></iframe>