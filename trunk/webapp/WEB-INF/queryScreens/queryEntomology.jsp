<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="dss.vector.solutions.mo.SpecieDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.query.QueryController"%>
<%@page import="dss.vector.solutions.query.EntomologySearch"%>
<%@page import="dss.vector.solutions.query.EntomologySearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.ThematicLayerDTO"%>
<jsp:include page="../templates/header.jsp"></jsp:include>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script src="/geoserver/openlayers/OpenLayers.js" type="text/javascript"></script>
<script type="text/javascript" src="js/QueryEntomology.js"></script>
<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

    String[] types = new String[]{RangeCategoryDTO.CLASS, NonRangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchViewDTO.CLASS, MosquitoDTO.CLASS, SpecieDTO.CLASS, QueryController.CLASS, EntomologySearchDTO.CLASS};
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>

(function(){

  YAHOO.util.Event.onDOMReady(function(){

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var assayTree = <%= (String) request.getAttribute("assayTree") %>;
    var queryList = <%= (String) request.getAttribute("queryList") %>;

    MDSS.QueryEntomology.initialize(assayTree, queryList);

    MDSS.QueryEntomology.render();

  });

})();
</script>

<div class="yui-skin-sam">

<div id="tabSet" class="yui-navset">
    <ul class="yui-nav">
        <li class="selected"><a href="#tab1"><em><fmt:message key="Query_Tab" /></em></a></li>
        <li><a href="#tab2"><em><fmt:message key="Map_Tab" /></em></a></li>
    </ul>
    <div class="yui-content">
        <div><div id="queryPanel"></div></div>
        <div><div id="mapPanel"></div></div>
    </div>
</div>


</div>
<div id="cal1Container" class="yui-skin-sam"></div>
<jsp:include page="../templates/footer.jsp"></jsp:include>