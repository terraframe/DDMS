<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>

<c:set var="page_title" value="Search_Spray_Team_Targets" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="now" value="<%=new java.util.Date()%>" scope="request" />


<mjl:form name="dss.vector.solutions.irs.GeoTargetController.view.mojo" method="POST" id="searchResourceTargets">
  <dl>
    <dt>
      <label><fmt:message key="Geo_Id"/></label>
    </dt>
    <dd>
      <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/>
    </dd>  
    <dt>
      <label> <fmt:message key="Season" /></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${seasons}" param="season.componentId" >
        <mjl:option>
          ${current.seasonName}, <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.startDate}" /> - <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.endDate}" />
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label><fmt:message key="Team" /></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${sprayTeams}" param="id" id="teamSelect" allLabel="All" disabled="true" includeBlank="true">
        <mjl:option>
          ${current}
         </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.irs.ResourceTargetController.view.mojo" name="search.button" value="Search" />
  </dl>
</mjl:form>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{GeoEntityDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>


<script type="text/javascript">
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

        	// Add the 'ALL' option
            Selectbox.addOption(teamSelect, MDSS.localize('All'), 'ALL', true);

            // Add the new options retrieved from the AJAX call
        	for(var i=0; i< teams.length; i++) {
              Selectbox.addOption(teamSelect, teams[i].getTeamId(), teams[i].getId(), false);
        	}        	

        	// Enable the select list
        	teamSelect.disabled=false;
          }
  		});

      Mojo.$.dss.vector.solutions.irs.SprayTeam.findByLocation(request, geoId.value);
    }
    else {
   	  teamSelect.disabled=true;
    }
  }
</script>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = true;
</script>
