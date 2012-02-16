<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyViewDTO"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>

<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<jsp:include page="geoEntityTreeComponent.jsp"/>
<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

// The root will have been set by the code in geoEntityTreeComponent.jsp
String rootId = (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID);

String[] types = new String[] { GeoHierarchyDTO.CLASS, GeoHierarchyViewDTO.CLASS, GeoEntityTreeController.CLASS };
%>

<%=Halp.loadTypes((List<String>) Arrays.asList(types))%>

<script type="text/javascript">
MDSS.SelectSearchRootId = '<%=rootId%>';

</script>
