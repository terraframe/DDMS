<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = true;
</script>

<c:set var="page_title" value="Create_Survey_Point"  scope="request"/>


<mjl:messages>
  <mjl:message />
</mjl:messages>

<%
  request.setAttribute("SentinelSiteClass", SentinelSiteDTO.CLASS);
  ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
%>

<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.name" id="surveyPointForm" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.SurveyPointController.create.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.create.button" />
  </dl>
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>