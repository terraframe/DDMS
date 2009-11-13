<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.irs.SprayLeaderDTO"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%><jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component item="${item}" param="dto">
  <mjl:dt type="text" attribute="startDate" classes="DatePick" />
  <mjl:dt type="text" attribute="completionDate" classes="DatePick" />
  <mjl:dt attribute="geoEntity">
    <mjl:input value="${item.geoEntity != null ? item.geoEntity.geoId : ''}" type="text" param="geoEntityId" classes="geoInput" id="geoEntityGeoId" />
    <mjl:input type="hidden" param="geoEntity" id="geoEntityGeoId_geoEntityId" />
  </mjl:dt>
  <mjl:dt attribute="geoDescription">
    <mjl:textarea param="geoDescription" cols="3" rows="3" />
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

<%=Halp.loadTypes(Arrays.asList(new String[]{SprayLeaderDTO.CLASS, PersonViewDTO.CLASS}))%>

<script type="text/javascript" defer="defer">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    MDSS.leaderSearch({search:'teamLeaderInput', concrete:'teamLeader'});       
  });
})();
</script>    
