<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.PersonController"%>
<%@page import="dss.vector.solutions.PersonDTO"%>

<c:set scope="request" var="page_title" value="Search_IndividualCase" />

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:messages>
<mjl:message />
</mjl:messages>
<dl>
<mjl:form id="form.id" name="IndividualCase.form.name" method="GET">
  <dt>
    <label> ${item.caseReportDateMd.displayLabel} </label>
  </dt>
  <dd>
    <mjl:input type="text" param="caseReportDate" classes="DatePick NoFuture" id="caseReportDate" value="${caseReportDate != null ? caseReportDate : ''}"/>
    <mjl:messages attribute="caseReportDate">
      <mjl:message/>
    </mjl:messages>
  </dd>
  <dt>
    <label> ${item.diagnosisDateMd.displayLabel} </label>
  </dt>
  <dd>
    <mjl:input type="text" param="diagnosisDate" classes="DatePick NoFuture" id="diagnosisDate" value="${diagnosisDate != null ? diagnosisDate : ''}"/>
    <mjl:messages attribute="diagnosisDate">
      <mjl:message/>
    </mjl:messages>
  </dd>

  <c:if test="${hasPermission}">
  <dt>
    <label> ${item.patientMd.displayLabel} </label>
  </dt>
  <dd>
	<table>
		<tr>
			<td> <label class="sublabel"> ${person.identifierMd.displayLabel}  </label></td>
			<td> <label class="sublabel"> ${person.firstNameMd.displayLabel}  </label></td>
			<td> <label class="sublabel"> ${person.lastNameMd.displayLabel}  </label></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
      			<mjl:input type="text" param="identifier.search" id="identifier.search" value="${person != null ? person.identifier : ''}" />
			</td>
			<td>
      			<mjl:input type="text" param="firstName.search" id="firstName.search" value="${person != null ? person.firstName : ''}" />
			</td>
			<td>      
      			<mjl:input type="text" param="lastName.search" id="lastName.search" value="${person != null ? person.lastName : ''}" />
			</td>
			<td>
      			<mjl:input type="hidden" param="personId" id="patient" value="${person != null ? person.personId : ''}"/>
      			<span class="clickable" id="recipient.span">
        			<span id="createPatient">
          			<mdss:localize key="Create_new_Patient"/>        
        			</span>
        			<span id="editPatient" >
          			<mdss:localize key="Edit_Patient"/>        
        			</span>
      			</span>
     			<mjl:messages attribute="personId">
    			<mjl:message/>
  			</mjl:messages>
			</td>
		</tr>
	</table>      
  </dd>
  </c:if>      

<mdss:localize key="Search" var="Localized_Search" />

<mjl:command name="searchPatient.button" value="${Localized_Search}" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.search.mojo" id="button.id" />
</mjl:form>
</dl>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.IndividualCaseExcelView" name="excelType"/>
</jsp:include>

<%=Halp.loadTypes(Arrays.asList(new String[]{PersonDTO.CLASS, PersonController.CLASS, PersonViewDTO.CLASS}))%>

<script type="text/javascript" >
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var prop = {
      elements : ['identifier.search', 'firstName.search', 'lastName.search'],
      concrete : 'patient',
      createLink : 'createPatient',
      editLink : 'editPatient', 
      clickable : 'recipient.span', 
      calendar : 'dateOfBirth', 
      firstName: 'firstName', 
      lastName: 'lastName',
      identifier: 'identifier',
      button : 'button.id'
    };
  
    new MDSS.PersonModal(prop);
  });
})();
</script>

