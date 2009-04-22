<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="dss.vector.solutions.mo.SpecieDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SentinalSiteDTO"%>
<%@page import="dss.vector.solutions.entomology.QueryController"%>
<jsp:include page="../templates/header.jsp"></jsp:include>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script src="http://127.0.0.1:8080/geoserver/openlayers/OpenLayers.js" type="text/javascript"></script>
<script type="text/javascript" src="js/QueryEntomology.js"></script>
<script type="text/javascript">

// mosquito definition
  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

    String[] types = new String[]{MosquitoDTO.CLASS, SpecieDTO.CLASS, QueryController.CLASS};
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>

(function(){

  YAHOO.util.Event.onDOMReady(function(){

	var tabs = new YAHOO.widget.TabView("tabSet");

    var assayTree = <%= (String) request.getAttribute("assayTree") %>;

    MDSS.QueryEntomology.initialize(assayTree);

    MDSS.QueryEntomology.render();

  });

})();
</script>

<div class="yui-skin-sam">

<div id="tabSet" class="yui-navset">
    <ul class="yui-nav">
        <li class="selected"><a href="#tab1"><em>Query</em></a></li>
        <li><a href="#tab2"><em>Map</em></a></li>
    </ul>
    <div class="yui-content">
        <div><div id="queryPanel"></div></div>
        <div><div id="mapPanel"></div></div>
    </div>
</div>


</div>
<div id="report"></div>
<div id="cal1Container" class="yui-skin-sam"></div>
<jsp:include page="../templates/footer.jsp"></jsp:include>