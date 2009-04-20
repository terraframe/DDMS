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
<jsp:include page="../templates/header.jsp"></jsp:include>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript" src="js/QueryEntomology.js"></script>
<script type="text/javascript">

// mosquito definition
  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

    String[] types = new String[]{MosquitoDTO.CLASS, SpecieDTO.CLASS};
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>

(function(){

  YAHOO.util.Event.onDOMReady(function(){

    var assayTree = <%= (String) request.getAttribute("assayTree") %>;

    MDSS.QueryEntomology.initialize(assayTree);

    MDSS.QueryEntomology.render();

  });

})();
</script>

<div class="yui-skin-sam">
  <div id="queryPanel" class="queryPanel"></div>
</div>
<div id="report"></div>
<div id="cal1Container" class="yui-skin-sam"></div>
<jsp:include page="../templates/footer.jsp"></jsp:include>