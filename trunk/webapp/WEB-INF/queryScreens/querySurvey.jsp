<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.query.QueryController"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.SurveyPointDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.HouseholdDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>

<c:set var="page_title" value="Query_Indicator_Surveys"  scope="request" />

<jsp:include page="../templates/header.jsp"></jsp:include>


<jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
<jwr:script src="/bundles/queryBundle.js"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

    String[] types = new String[]{SurveyPointDTO.CLASS, HouseholdDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);

    out.print(js);
  %>

MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = true;
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= SentinelSiteDTO.CLASS %>');

(function(){

  YAHOO.util.Event.onDOMReady(function(){

    var queryList = <%= (String) request.getAttribute("queryList") %>;
    var nets = <%= (String) request.getAttribute("nets") %>;
    var rdtResults = <%= (String) request.getAttribute("rdtResults") %>

    var query = new MDSS.QuerySurvey(queryList, nets, rdtResults);
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