<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>

<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.PersonController"%>
<%@page import="dss.vector.solutions.PersonDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%><c:set var="page_title" value="Search_ITNDistribution" scope="request" />
<c:set var="healthFacility" value="<%= HealthFacilityDTO.CLASS %>" scope="page"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<jsp:include page="/WEB-INF/selectSearch.jsp" />
<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
MDSS.AbstractSelectSearch.ExtraUniversals.push('${healthFacility}*');
</script>

<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.name" id="dss.vector.solutions.intervention.monitor.ITNDistribution.form.id" method="POST">
    <input type="hidden" id="typeSearchFilter" value="${healthFacility}" />
    <mjl:component item="${item}" param="view">
      <mjl:dt attribute="facility">
        <mjl:input value="${item.facility}" type="text" param="facility" classes="geoInput" id="geoIdEl" />
      </mjl:dt>
      <mjl:dt attribute="distributionDate" >
        <mjl:input type="text" param="distributionDate" id="distributionDate" classes="DatePick NoFuture"/>
      </mjl:dt>
      <mjl:dt attribute="batchNumber">
        <mjl:input type="text" param="batchNumber" />
      </mjl:dt>
      <mjl:dt attribute="person">
        <mjl:input type="text" param="personDisplay" id="personDisplay" />
        <mjl:input type="hidden" param="person" id="person"/>
        <span class="clickable" id="person.span">
          <span id="createperson">
            <fmt:message key="Create_new_recipient"/>        
          </span>
          <span id="editperson" >
            <fmt:message key="Edit_recipient"/>        
          </span>
        </span>
      </mjl:dt>
    </mjl:component>

    <mjl:command value="Search" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.viewHistory.mojo" name="search.person" id="button.id" />
  </mjl:form>
</dl>

<%=Halp.loadTypes(Arrays.asList(new String[]{PersonDTO.CLASS, PersonController.CLASS, PersonViewDTO.CLASS}))%>

<script type="text/javascript" >
(function(){
    YAHOO.util.Event.onDOMReady(function(){
      
    MDSS.PersonModal.setUpPersonModal(
          { searchEl : 'personDisplay', idEl : 'person'},
          { createLink : 'createperson', editLink : 'editperson', modalEl : 'person.span', calendarEl : 'dateOfBirth'},
          { button : 'button.id'} );
  });
})();
</script>
