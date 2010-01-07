<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.query.QueryController"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>

<c:set var="page_title" value="Query_Aggregated_Cases"  scope="request" />

<jsp:include page="../templates/header.jsp"></jsp:include>


<jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
<jwr:script src="/bundles/queryBundle.js"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

    String[] types = new String[]{EpiDateDTO.CLASS, AggregatedCaseDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);

    out.print(js);
  %>

(function(){

  YAHOO.util.Event.onDOMReady(function(){

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;
    var ageGroups = <%= (String) request.getAttribute("ageGroups") %>;
    var visibleAttributes = <%= (String) request.getAttribute("visibleAttributes") %>;
    visibleAttributes = visibleAttributes.concat(
        [{"attributeName":"cfr","displayLabel":"CFR(GB)","type":"sqldouble"},
         {"attributeName":"incidence_100","displayLabel":"Incidence 100(GB)","type":"sqldouble"},
         {"attributeName":"incidence_1000","displayLabel":"Incidence 1,000(GB)","type":"sqldouble"},
         {"attributeName":"incidence_10000","displayLabel":"Incidence 10,000(GB)","type":"sqldouble"},
         {"attributeName":"incidence_100000","displayLabel":"Incidence 100,000(GB)","type":"sqldouble"},
        ]


         );

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

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>