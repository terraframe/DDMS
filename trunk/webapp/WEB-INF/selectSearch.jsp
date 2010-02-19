<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyViewDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="org.json.JSONArray"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>


<jsp:include page="geoEntityTreeComponent.jsp"/>
<%ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
//String rootId = (String) request.getAttribute(GeoEntityTreeController.ROOT_GEO_ENTITY_ID);
String rootId = EarthDTO.getEarthInstance(requestIF).getId();
String[] types = new String[] { GeoHierarchyDTO.CLASS, GeoHierarchyViewDTO.CLASS, GeoEntityTreeController.CLASS, GeoEntityViewDTO.CLASS };
//String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
// out.print(js);%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types))%>
<script type="text/javascript">
MDSS.SelectSearchRootId = '<%=rootId%>';
</script>