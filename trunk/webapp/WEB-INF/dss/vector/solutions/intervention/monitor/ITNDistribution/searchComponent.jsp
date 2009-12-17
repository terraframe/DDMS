<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.PersonController"%>
<%@page import="dss.vector.solutions.PersonDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<c:set var="healthFacility" scope="page"><%= HealthFacilityDTO.CLASS %></c:set>
<c:set var="page_title" value="Search_ITNDistribution" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<%
  List<String> entityUniversals = Arrays.asList(new String[]{HealthFacilityDTO.CLASS + "*"}); 
  request.setAttribute("entityUniversals", entityUniversals);
%>

<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.name" id="dss.vector.solutions.intervention.monitor.ITNDistribution.form.id" method="POST">
    <mjl:component item="${item}" param="view">
      <mjl:dt attribute="facility">
        <mdss:geo param="facility" concrete="false" value="${item.facility}" universals="${entityUniversals}" filter="${healthFacility}" />
      </mjl:dt>
      <mjl:dt attribute="distributionDate" >
        <mjl:input type="text" param="distributionDate" id="distributionDate" classes="DatePick NoFuture"/>
      </mjl:dt>
      <mjl:dt attribute="batchNumber">
        <mjl:input type="text" param="batchNumber" />
      </mjl:dt>
      <mjl:dt attribute="person">
        <mjl:input type="text" param="firstName.search" id="firstName.search" value="${person != null ? person.firstName : ''}" />
        <mjl:input type="text" param="lastName.search" id="lastName.search" value="${person != null ? person.lastName : ''}" />

        <mjl:input type="hidden" param="person" id="person"/>

        <span class="clickable" id="person.span">
          <span id="createPerson">
            <fmt:message key="Create_new_recipient"/>        
          </span>
          <span id="editPerson" >
            <fmt:message key="Edit_recipient"/>        
          </span>
        </span>
      </mjl:dt>
    </mjl:component>

    <mjl:command value="Search" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.viewHistory.mojo" name="search.person" id="button.id" />
  </mjl:form>
</dl>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.ITNDistributionExcelView" name="excelType"/>
</jsp:include>

<%=Halp.loadTypes(Arrays.asList(new String[]{PersonDTO.CLASS, PersonController.CLASS, PersonViewDTO.CLASS}))%>

<script type="text/javascript" >
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var prop = {
      elements : ['firstName.search', 'lastName.search'],
      concrete : 'person',
      createLink : 'createPerson',
      editLink : 'editPerson', 
      clickable : 'person.span', 
      calendar : 'dateOfBirth', 
      firstName: 'firstName', 
      lastName: 'lastName',
      button : 'button.id'
    };
  
    MDSS.PersonModal.setUpPersonModal(prop);
  });
})();
</script>
