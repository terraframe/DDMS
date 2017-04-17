<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<c:set var="page_title" value="Search_for_a_Team_Spray" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:form name="search" method="POST" id ="searchTeamSpray">
  <dl>
    <dt> <mdss:localize key="Geo_Entity"/> </dt>
    <dd> 
      <mdss:geo param="geoId" concrete="false" value="${geoId}" political="false" spray="true" />
    </dd>
    <dt> <mdss:localize key="Insecticide"/> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand.componentId" >
       <mjl:option selected="${brand != null && current.id == brand.id ? 'selected' : 'false'}">
          ${current.productName.termDisplayLabel.value}
       </mjl:option>
      </mjl:select>
    </dd>
    <dt> <mdss:localize key="Spray_Date"/> </dt>
    <dd> <mjl:input param="date" type="text" classes="DatePick NoFuture" id="sprayDate" value="${date}"/></dd>
    <dt> <mdss:localize key="Spray_Method"/> </dt>
    <dd>
      <mjl:group type="radio" var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="method">
        <mjl:groupOption checked="${current.enumName == method ? 'checked' : 'false'}">
            ${current.displayLabel}
        </mjl:groupOption>
      </mjl:group>
    </dd>
    <dt> <mdss:localize key="Spray_Team"/> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${teams}" id="teamSelect" param="team.componentId" >
       <mjl:option selected="${team != null && current.id == team.id ? 'selected' : 'false'}">
          ${current.teamId}
       </mjl:option>
      </mjl:select>
    </dd>
    <mdss:localize key="Search" var="Localized_Search" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.irs.TeamSprayController.searchByParameters.mojo" name="search.button" value="${Localized_Search}" />
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.TeamSprayExcelView" name="excelType"/>
</jsp:include>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>

<script type="text/javascript" defer="defer">

var teamSelect = document.getElementById('teamSelect');
var geoId = document.getElementById('geoId');

var search = new MDSS.TeamSearch(geoId, teamSelect, null, null);

onValidGeoEntitySelected = function(){
  search.populateSprayTeams();
}

</script>

<div id="cal1Container" class="yui-skin-sam"></div>