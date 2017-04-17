<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<c:set var="page_title" value="Create_Survey_Point"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.name" id="surveyPointForm" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mdss:localize key="Create" var="Localized_Create" />

    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.intervention.monitor.SurveyPointController.create.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.create.button" />
  </dl>
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>