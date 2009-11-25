<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>

<%@page import="dss.vector.solutions.query.DefaultSavedMapDTO"%>


<%@page import="dss.vector.solutions.query.SavedMapController"%><jsp:include page="../templates/header.jsp"></jsp:include>

<script type="text/javascript">
<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

String[] types = new String[]{DefaultSavedMapDTO.CLASS, SavedMapController.CLASS, LayerViewDTO.CLASS,
    ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS,
    NonRangeCategoryController.CLASS, MappingController.CLASS};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>


YAHOO.util.Event.onDOMReady(function(){

  var queryList = <%= (String) request.getAttribute("queryList") %>;
  var mapList = <%= (String) request.getAttribute("mapList") %>
	
  new MDSS.MapPanel('mapPanel', queryList, mapList).render();
	  
});
</script>

<div class="yui-skin-sam">
  <div id="mapPanel"></div>
</div>

<jsp:include page="../templates/footer.jsp"></jsp:include>