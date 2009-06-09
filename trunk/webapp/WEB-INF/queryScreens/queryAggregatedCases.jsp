<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
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
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.query.AggregatedCasesSearchDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.geo.generated.FacilityDTO"%>
<%@page import="dss.vector.solutions.geo.generated.AbstractSite"%>
<jsp:include page="../templates/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
<jwr:script src="/bundles/queryBundle.js"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

    String[] types = new String[]{ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, AggregatedCasesSearchDTO.CLASS, AggregatedCaseDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);

    out.print(js);
  %>

MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= AbstractSite.CLASS %>');
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= FacilityDTO.CLASS %>*');

(function(){

  YAHOO.util.Event.onDOMReady(function(){

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;
    var ageGroups = <%= (String) request.getAttribute("ageGroups") %>;
    var visibleAttributes = <%= (String) request.getAttribute("visibleAttributes") %>;

    var orderedGrids = <%= (String) request.getAttribute("orderedGrids") %>;

    var query = new MDSS.QueryAggregatedCases(ageGroups, visibleAttributes, orderedGrids, queryList);
    query.render();

    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('messageFrame', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);

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

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>
<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>

</div>
<jsp:include page="../templates/footer.jsp"></jsp:include>