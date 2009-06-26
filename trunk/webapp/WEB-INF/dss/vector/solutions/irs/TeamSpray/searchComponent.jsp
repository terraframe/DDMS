<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>


<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%><c:set var="page_title" value="Search_for_a_Team_Spray" scope="request" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = true;
</script>

<mjl:form name="search" method="POST" id ="searchTeamSpray">
  <dl>
    <dt> <fmt:message key="Geo_Id"/> </dt>
    <dd> <mjl:input id="geoIdEl" param="geoId" type="text" value="${geoId}" maxlength="16" classes="geoInput"/></dd>
    <dt> <fmt:message key="Insecticide_Brand"/> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand.componentId" >
       <mjl:option selected="${brand != null && current.id == brand.id ? 'selected' : 'false'}">
          ${current.brandName}
       </mjl:option>
      </mjl:select>
    </dd>
    <dt> <fmt:message key="Spray_Date"/> </dt>
    <dd> <mjl:input param="date" type="text" classes="DatePick NoFuture" id="sprayDate" value="${date}"/></dd>
    <dt> <fmt:message key="Spray_Method"/> </dt>
    <dd>
      <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="method">
        <mjl:radioOption checked="${current.enumName == method ? 'checked' : 'false'}">
            ${current.displayLabel}
        </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <dt> <fmt:message key="Spray_Team"/> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${teams}" id="teamSelect" disabled="true" param="team.componentId" >
       <mjl:option selected="${team != null && current.id == team.id ? 'selected' : 'false'}">
          ${current.teamId}
       </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.irs.TeamSprayController.searchByParameters.mojo" name="search.button" value="Search" />
  </dl>
</mjl:form>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{GeoEntityDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>

<script type="text/javascript" defer="defer">

onValidGeoEntitySelected = function(){
    var geoId = document.getElementById('geoIdEl');
    var teamSelect = document.getElementById('teamSelect');    

    if(geoId.value != '')
    {
      var request = new MDSS.Request({
          onSend: function(){},
          onComplete: function(){},
          onFailure : function(){
          	teamSelect.disabled=true;            
          },
          onProblemExceptionDTO : function(){
            teamSelect.disabled=true;
          },          
          onSuccess : function(teams){
        	// Remove all of the current options in the select list
            Selectbox.removeAllOptions(teamSelect);

            Selectbox.addOption(teamSelect, 'Select Team', '', false);
        	
            // Add the new options retrieved from the AJAX call
        	for(var i=0; i< teams.length; i++) {
              Selectbox.addOption(teamSelect, teams[i].getTeamId(), teams[i].getId(), false);
        	}        	

        	// Enable the select list
        	teamSelect.disabled=false;
        	populatedSprayTeams = true;
        	
          }
  		});

      Mojo.$.dss.vector.solutions.irs.SprayTeam.findByLocation(request, geoId.value);
    }
    else {
      Selectbox.removeAllOptions(teamSelect);        
   	  teamSelect.disabled=true;
    }
  }

onSprayTeamLoaded = function(){
	if(populatedSprayTeams) {		
	  document.getElementById('teamSelect').value = '${team}';	
	}
	else {
		setTimeout('onSprayTeamLoaded();',200);
	}
  }

populatedSprayTeams = false;

//On page load if a valid GeoId is supplied then load the teams for that geo Id
onValidGeoEntitySelected();

onSprayTeamLoaded();
</script>


<div id="cal1Container" class="yui-skin-sam"></div>