<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.irs.TeamMemberViewDTO"%>
<%@page import="dss.vector.solutions.irs.TeamMemberDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<mjl:component item="${item}" param="dto">
  <mjl:dt type="text" attribute="startDate" classes="DatePick" />
  <mjl:dt type="text" attribute="completionDate" classes="DatePick" />
  <mjl:dt attribute="geoEntity">
     <mdss:geo param="geoEntity" value="${item.geoEntity}" />
  </mjl:dt>
  <mjl:dt attribute="geoDescription">
    <mjl:textarea param="geoDescription" cols="3" rows="3" />
  </mjl:dt>
  <mjl:dt attribute="natureOfControl">
    <mjl:boolean param="natureOfControl"/>
  </mjl:dt>
  <mjl:dt attribute="personCount">
    <mjl:input type="text" param="personCount" />
  </mjl:dt>
  <mjl:dt attribute="teamLeader">
    <mjl:input id="teamLeaderInput" param="teamLeaderInput" type="text" value="${item.teamLeader.person}"/>
    <mjl:input id="teamLeader" param="teamLeader" type="hidden" value="${item.teamLeader.id}"/>        
    <mjl:messages attribute="teamLeader">
      <mjl:message />
    </mjl:messages>
  </mjl:dt>
  
</mjl:component>

<%=Halp.loadTypes(Arrays.asList(new String[]{TeamMemberDTO.CLASS, TeamMemberViewDTO.CLASS}))%>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    new MDSS.SprayLeaderSearch({search:'teamLeaderInput', concrete:'teamLeader'});       
  });
})();
</script>    
