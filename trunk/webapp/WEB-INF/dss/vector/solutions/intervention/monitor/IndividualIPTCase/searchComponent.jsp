<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>
<%@page import="dss.vector.solutions.PersonController"%>
<%@page import="dss.vector.solutions.PersonDTO"%><c:set scope="request" var="page_title" value="Search_IndividualIPTCase" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = true;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;
MDSS.AbstractSelectSearch.ExtraUniversals.push('<%=HealthFacilityDTO.CLASS%>*');
</script>

<mjl:messages>
	<mjl:message />
</mjl:messages>
<dl>
	<mjl:form id="form.id" name="IndividualIPT.form.name" method="GET">
	  <dt>
	    <label> ${item.serviceDateMd.displayLabel} </label>
	  </dt>
	  <dd>
	    <mjl:input type="text" param="serviceDate" classes="DatePick" id="serviceDate"/>
	  </dd>
	  <dt>
	    <label> ${item.patientMd.displayLabel} </label>
	  </dt>
	  <dd>
        <mjl:input type="text" param="patientDisplay" id="patientDisplay" />
        <mjl:input type="hidden" param="patientId" id="patient"/>
        <span class="clickable" id="recipient.span">
          <span id="createPatient">
            <fmt:message key="Create_new_Patient"/>        
          </span>
          <span id="editPatient" >
            <fmt:message key="Edit_Patient"/>        
          </span>
        </span>
	  </dd>

		<mjl:command name="searchPatient.button" value="Search"	action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.viewCasePage.mojo" id="button.id" />
	</mjl:form>
</dl>

<%=Halp.loadTypes(Arrays.asList(new String[]{PersonDTO.CLASS, PersonController.CLASS, PersonViewDTO.CLASS}))%>

<script type="text/javascript" >
(function(){
	  YAHOO.util.Event.onDOMReady(function(){
		  
    MDSS.PersonModal.setUpPersonModal(
    	    { searchEl : 'patientDisplay', idEl : 'patient'},
    	    { createLink : 'createPatient', editLink : 'editPatient', modalEl : 'recipient.span', calendarEl : 'dateOfBirth'},
    	    { button : 'button.id'} );
  });
})();
</script>

