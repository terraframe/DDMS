<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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


<%@page import="dss.vector.solutions.surveillance.AggregatedCaseViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>
<%@page import="dss.vector.solutions.geo.generated.CollectionSite"%>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= CollectionSite.CLASS %>*');
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= HealthFacilityDTO.CLASS %>*');
</script>

<c:set var="page_title" value="Search_ITN_Data"  scope="request"/>

<mjl:form name="search" method="POST" id ="searchAggregatedCase">
  <dl>
    <dt> <fmt:message key="Geo_Id"/> </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" value="${geoId}" maxlength="16" classes="geoInput"/></dd>
    <dt> <fmt:message key="Period_Type"/> </dt>
    <dd>
      <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${periodType}" param="periodType">
        <mjl:radioOption checked="${current.enumName == checkedType ? 'checked' : 'false'}" >
            ${current.displayLabel}
        </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <dt> <fmt:message key="Period"/> </dt>
    <dd>
      <mjl:input param="period" type="text" size="2" maxlength="2" value="${period}" id="period" classes="NumbersOnly"/>
      <mjl:messages attribute="period">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <dt> <fmt:message key="Year"/> </dt>
    <dd>
      <mjl:input param="year" type="text" size="4" maxlength="4" value="${year}" classes="NoFutureYear" id='year'/>
      <mjl:messages attribute="year">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.intervention.monitor.ITNDataController.searchByGeoIdAndPeriod.mojo" name="search.button" id="button.id" value="Search" />
  </dl>
</mjl:form>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{AggregatedCaseViewDTO.CLASS}))%>

<script type="text/javascript" defer="defer">
  var form = document.getElementById('searchAggregatedCase');

  var periodType = form.periodType;
  var button = document.getElementById('button.id');

  var geoId = document.getElementById('geoIdEl');	  
  var year = document.getElementById('year');
  var period = document.getElementById('period');

  MDSS.validateEpiDate(button, geoId, year, period, periodType);
</script>
