<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

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


<%@page import="dss.vector.solutions.query.SavedMapController"%>
<%@page import="dss.vector.solutions.query.LayerDTO"%>
<%@page import="dss.vector.solutions.query.LayerController"%>
<%@page import="dss.vector.solutions.query.AbstractCategoryController"%>


<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.AttributeGeoHierarchyDTO"%><c:set var="page_title" value="Generate_Maps"  scope="request"/>

<jsp:include page="../templates/header.jsp"></jsp:include>

<jwr:script src="/bundles/mapBundle.js"/>

<script type="text/javascript">


YAHOO.util.Event.onDOMReady(function(){

    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('exportIframe', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);
});

<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

String[] types = new String[]{DefaultSavedMapDTO.CLASS, SavedMapController.CLASS, LayerViewDTO.CLASS, LayerDTO.CLASS,
    ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS,
    NonRangeCategoryController.CLASS, MappingController.CLASS, LayerController.CLASS, AbstractCategoryController.CLASS, SavedSearchDTO.CLASS,
    AttributeGeoHierarchyDTO.CLASS};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>


YAHOO.util.Event.onDOMReady(function(){

  var mapList = <%= (String) request.getAttribute("mapList") %>

	
  new MDSS.MapPanel('mapPanel', mapList).render();
	  
});
</script>

<div class="yui-skin-sam">
  <div id="mapPanel"></div>
</div>

<iframe id="exportIframe" name="exportIframe" style="display: none; width: 1px; height: 1px;"></iframe>

<div style="display: none">
  <form id="exportShapefile" target="exportIframe" method="POST" action="dss.vector.solutions.query.MappingController.exportShapefile.mojo">
    <input type="hidden" id="export_mapId" name="mapId" />
  </form>
</div>

<jsp:include page="../templates/footer.jsp"></jsp:include>