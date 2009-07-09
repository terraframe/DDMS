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
<%@page import="dss.vector.solutions.geo.generated.PermanentWaterBodyDTO"%>
<%@page import="dss.vector.solutions.geo.generated.TrapDTO"%>

<c:set var="page_title" value="Mosquito_Collection_Points"  scope="request"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<%
  request.setAttribute("TrapClass", TrapDTO.CLASS);
  request.setAttribute("PermanentWaterBodyClass", PermanentWaterBodyDTO.CLASS);
%>

<script type="text/javascript">
  MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= TrapDTO.CLASS %>');
  MDSS.AbstractSelectSearch.ExtraUniversals.push('<%= PermanentWaterBodyDTO.CLASS %>');
</script>

<mjl:form name="dss.vector.solutions.entomology.MosquitoCollectionPoint.search" method="GET" id ="searchMosquitoCollections">
  <dl>
    <dt> <fmt:message key="Filter" /> </dt>
    <dd>
        <input type="radio" name="filterType" class="filterType" value="" checked="checked" /><fmt:message key="All" /> &nbsp;&nbsp;&nbsp;
        <input type="radio"  class="filterType" name="filterType" value="${TrapClass}" /><fmt:message key="Trap" /> &nbsp;&nbsp;&nbsp;
        <input type="radio"  class="filterType" name="filterType" value="${PermanentWaterBodyClass}" /><fmt:message key="Permanent_Water_Body" />
    </dd>
    <dt> <fmt:message key="Geo_Entity" /> </dt>
    <dd>
      <mjl:input id="geoIdEl" param="geoId" type="text"  maxlength="16" value="${geoId}" classes="geoInput"/>
      <mjl:messages attribute="geoId">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <dt> <fmt:message key="Start_Date" /> </dt>
    <dd>
      <mjl:input param="startDate" type="text" classes="DatePick NoFuture" id="startDate" value="${startDate}"/>
      <mjl:messages attribute="startDate">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <dt> <fmt:message key="End_Date" /> </dt>
    <dd>
      <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate" value="${endDate}"/>
      <mjl:messages attribute="endDate">
        <mjl:message/>
      </mjl:messages>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.entomology.MosquitoCollectionPointController.searchByGeoIdAndDate.mojo" name="search.button"  value="Search"/>
  </dl>
</mjl:form>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.MorphologicalSpecieGroupExcelView" name="excelType"/>
</jsp:include>