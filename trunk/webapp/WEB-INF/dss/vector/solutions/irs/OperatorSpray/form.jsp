<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="dss.vector.solutions.irs.SprayOperatorDTO"%>
<%@page import="dss.vector.solutions.irs.SprayOperatorViewDTO"%>
<%@page import="dss.vector.solutions.irs.AbstractSprayViewDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="sprayId" value="${item.sprayId}" />      

      <mjl:dt attribute="geoEntity">
        <mdss:geo param="geoEntity" value="${item.geoEntity}" political="false" populated="false" spray="true" />
      </mjl:dt>
      <mjl:dt attribute="brand"> 
        <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand" >
         <mjl:option selected="${item.brand != null && current.id == item.brand.id ? 'selected' : 'false'}">
           ${current.brandName}
         </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="sprayDate">
        <mjl:input param="sprayDate" type="text" classes="DatePick NoFuture" id="sprayDate" value="${item.sprayDate}" />
      </mjl:dt>
      <mjl:dt attribute="sprayMethod">
        <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="sprayMethod">
          <mjl:radioOption checked="${mjl:contains(item.sprayMethodEnumNames, current.enumName)? 'checked' : 'false'}">
            ${current.displayLabel}
          </mjl:radioOption>
        </mjl:radioGroup>      
      </mjl:dt>
      <dt>
        <label title="* "><fmt:message key="Team" /></label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${teams}" param="teamId" id="teamSelect" includeBlank="true">
          <mjl:option selected="${teamId != null && current.id == teamId ? 'selected' : 'false'}">
            ${current}
          </mjl:option>
        </mjl:select>
      </dd>         
      <mjl:dt attribute="sprayOperator">
        <mjl:select var="current" valueAttribute="actorId" items="${operators}" id="operatorSelect" param="sprayOperator" >
          <mjl:option selected="${item.sprayOperator != null && actorId == item.sprayOperator.id ? 'selected' : 'false'}">
            ${current.operatorId} - ${current.lastName}, ${current.firstName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>      
      <mjl:dt attribute="teamLeader">       
        <mjl:select var="current" valueAttribute="actorId" items="${operators}" id="leaderSelect" param="teamLeader">
          <mjl:option selected="${item.teamLeader != null && actorId == item.teamLeader.id ? 'selected' : 'false'}">
            ${current.operatorId} - ${current.lastName}, ${current.firstName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>        
      <mjl:dt attribute="surfaceType">
        <mdss:mo param="surfaceType" value="${surfaceType}"/>
      </mjl:dt>                  
      <mjl:dt attribute="teamSprayWeek" type="text"/>
      <mjl:dt attribute="target" type="text"/>
      <mjl:dt attribute="operatorSprayWeek" type="text" />
      <mjl:dt attribute="received" type="text"/>
      <mjl:dt attribute="refills" type="text"/>
      <mjl:dt attribute="returned" type="text"/>
      <mjl:dt attribute="used" type="text"/>
    </mjl:component>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayOperatorDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayOperatorViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var teamSelect = document.getElementById('teamSelect');
    var operatorSelect = document.getElementById('operatorSelect');
    var leaderSelect = document.getElementById('leaderSelect');
    var geoId = document.getElementById('geoIdEl');

    var search = new MDSS.TeamSearch(geoId, teamSelect, operatorSelect, leaderSelect);

    onValidGeoEntitySelected = function(){
        search.populateSprayTeams();
    }
  })
})();
</script>