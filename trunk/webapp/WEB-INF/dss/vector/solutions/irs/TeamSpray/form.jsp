<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="dss.vector.solutions.irs.TeamMemberViewDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />      
      <mjl:dt attribute="geoEntity">
        <mdss:geo param="geoEntity" value="${item.geoEntity}" political="false" populated="false" spray="true" />
      </mjl:dt>
      <mjl:dt attribute="brand"> 
        <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand" >
         <mjl:option selected="${item.brand != null && current.id == item.brand.id ? 'selected' : 'false'}">
           ${current.productName.termDisplayLabel.value}
         </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="sprayDate">
        <mjl:input param="sprayDate" type="text" classes="DatePick NoFuture" id="sprayDate" value="${item.sprayDate}" />
      </mjl:dt>
      <mjl:dt attribute="sprayMethod">
        <mjl:group type="radio" var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="sprayMethod">
          <mjl:groupOption checked="${mjl:contains(item.sprayMethodEnumNames, current.enumName)? 'checked' : 'false'}">
            ${current.displayLabel}
          </mjl:groupOption>
        </mjl:group>      
      </mjl:dt>
      <mjl:dt attribute="sprayTeam">
        <mjl:select var="current" valueAttribute="id" items="${teams}" id="teamSelect" param="sprayTeam" >
          <mjl:option selected="${team != null && current.id == team.id ? 'selected' : 'false'}">
            ${current.teamId}
          </mjl:option>
        </mjl:select>
      </mjl:dt>      
      <mjl:dt attribute="teamLeader">       
        <mjl:select var="current" valueAttribute="actorId" items="${members}" id="leaderSelect" param="teamLeader" includeBlank="true">
          <mjl:option selected="${(leaderId != null && current.actorId == leaderId) ? 'selected' : 'false'}">
            ${current.memberId} - ${current.lastName}, ${current.firstName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>        
      <mjl:dt attribute="surfaceType">
        <mdss:mo param="surfaceType" value="${surfaceType}"/>
      </mjl:dt>     
      <mjl:dt attribute="supervisor">
        <mjl:select var="current" valueAttribute="supervisorId" items="${supervisors}" param="supervisor" includeBlank="true" >
         <mjl:option selected="${supervisor != null && current.id == supervisor.id ? 'selected' : 'false'}">
           ${current.firstName} ${current.lastName}
         </mjl:option>
        </mjl:select>        
      </mjl:dt>                   
      <mjl:dt attribute="target" type="text"/>
    </mjl:component>

<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS, TeamMemberViewDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var search = new MDSS.TeamSearch('geoIdEl', 'teamSelect', null, 'leaderSelect');

    Mojo.GLOBAL.onValidGeoEntitySelected = function(){
      search.populateSprayTeams();
    }
  })
})();
</script>