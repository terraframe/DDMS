<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="dss.vector.solutions.irs.SprayOperatorViewDTO"%>


<%@page import="dss.vector.solutions.irs.SprayOperatorDTO"%>

<c:set var="page_title" value="Search_for_an_Operator_Spray" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:form name="search" method="POST" id ="searchOperatorSpray">
  <dl>
    <dt> <fmt:message key="Geo_Id"/> </dt>
    <dd>
      <mdss:geo param="geoId" concrete="false" value="${geoId}" political="false" populated="false" spray="true" />
    </dd>
    <dt> <fmt:message key="Insecticide_Brand"/> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand.componentId" >
       <mjl:option selected="${brand != null && current.id == brand.id ? 'selected' : 'false'}">
          ${current.brandName}
       </mjl:option>
      </mjl:select>
    </dd>
    <dt> <fmt:message key="Spray_Date"/> </dt>
    <dd> <mjl:input param="date" type="text" classes="DatePick NoFuture" id="sprayDate" /></dd>
    <dt> <fmt:message key="Spray_Method"/> </dt>
    <dd>
      <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="method">
        <mjl:radioOption checked="${current.enumName == method ? 'checked' : 'false'}">
            ${current.displayLabel}
        </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <dt>
      <fmt:message key="Team" />
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${teams}" param="teamId" id="teamSelect" includeBlank="true">
        <mjl:option selected="${teamId != null && teamId == current.id ? 'selected' : 'false'}">
          ${current}
         </mjl:option>
      </mjl:select>
    </dd>   
    <dt> <fmt:message key="Spray_Operator"/> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="actorId" items="${operators}" id="operatorSelect" param="operator.componentId" >
        <mjl:option selected="${operator != null && current.actorId == operator.id ? 'selected' : 'false'}">
          ${current.operatorId} - ${current.lastName}, ${current.firstName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.irs.OperatorSprayController.searchByParameters.mojo" name="search.button" value="Search" />
  </dl>
</mjl:form>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.OperatorSprayExcelView" name="excelType"/>
</jsp:include>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayOperatorDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayOperatorViewDTO.CLASS}))%>


<script type="text/javascript" defer="defer">

var teamSelect = document.getElementById('teamSelect');
var operatorSelect = document.getElementById('operatorSelect');
var geoId = document.getElementById('geoId');

var search = new MDSS.TeamSearch(geoId, teamSelect, operatorSelect, null);

onValidGeoEntitySelected = function(){
  search.populateSprayTeams();
}

</script>


<div id="cal1Container" class="yui-skin-sam"></div>