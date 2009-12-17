<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.intervention.monitor.IndividualInstanceDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />
<%@include file="../IndividualCase/personHeader.jsp" %>

<%
  List<String> entityUniversals = Arrays.asList(new String[]{HealthFacilityDTO.CLASS + "*"}); 
  request.setAttribute("entityUniversals", entityUniversals);
%>

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="individualCase" value="${item.individualCase.id}"/>
  <mjl:dt attribute="activelyDetected">
    <mjl:boolean param="activelyDetected" />
  </mjl:dt>
  <mjl:dt attribute="healthFacility">
    <mdss:geo param="healthFacility" value="${healthFacility}" universals="${entityUniversals}" />
  </mjl:dt>
  <mjl:dt attribute="detectedBy">
    <mdss:mo param="detectedBy" value="${detectedBy}"/>
  </mjl:dt>
  
  <h2><fmt:message key="Basic_Case_Information" /></h2>
  <mjl:dt attribute="clinicalDiagnosis">
    <mjl:boolean param="clinicalDiagnosis" />
  </mjl:dt>
  <mjl:dt type="text" attribute="symptomOnset" classes="DatePick  NoFuture" />
  <mjl:dt type="text" attribute="facilityVisit" classes="DatePick  NoFuture" />
  <mjl:dt attribute="patientCategory">
    <mdss:mo param="patientCategory" value="${patientCategory}"/>
  </mjl:dt>
  <mjl:dt type="text" attribute="admissionDate" classes="DatePick  NoFuture" />
  <mjl:dt type="text" attribute="releaseDate" classes="DatePick  NoFuture" />
  <mjl:dt attribute="anaemiaPatient">
    <mjl:boolean param="anaemiaPatient" />
  </mjl:dt>
  <mjl:dt attribute="pregnant">
    <mjl:boolean param="pregnant" />
  </mjl:dt>
  <mjl:dt attribute="diedInFacility">
    <mjl:boolean param="diedInFacility" />
  </mjl:dt>
  
  <h2><fmt:message key="Administrative_Information" /></h2>
  <mjl:dt attribute="properlyRelease">
    <mjl:boolean param="properlyRelease" />
  </mjl:dt>
  <mjl:dt attribute="referredTo">
    <mjl:boolean param="referredTo" />
  </mjl:dt>
  <mjl:dt attribute="referredFrom">
    <mjl:boolean param="referredFrom" />
  </mjl:dt>
  <mjl:dt attribute="referralReason">
    <mdss:mo param="referralReason" value="${referralReason}"/>
  </mjl:dt>

  <h2><fmt:message key="Laboratory_Testing" /></h2>
  <mjl:dt attribute="sampleType">
    <mdss:mo param="sampleType" value="${sampleType}"/>
  </mjl:dt>
  <mjl:dt attribute="labTest">
    <mdss:mo param="labTest" value="${labTest}"/>
  </mjl:dt>
  <mjl:dt type="text" attribute="testSampleDate" classes="DatePick  NoFuture" />
  <mjl:dt type="text" attribute="labTestDate" classes="DatePick  NoFuture" />
  <mjl:dt attribute="malariaType">
    <mdss:mo param="malariaType" value="${malariaType}"/>
  </mjl:dt>

  <h2><fmt:message key="Treatment" /></h2>
  <mjl:dt attribute="treatmentMethod">
    <mdss:mo param="treatmentMethod" value="${treatmentMethod}"/>
  </mjl:dt>
  <mjl:dt attribute="treatment">
    <mdss:mo param="treatment" value="${treatment}"/>
  </mjl:dt>
  <mjl:dt type="text" attribute="treatmentStartDate" classes="DatePick  NoFuture" />
  
  <h2><fmt:message key="Clinical_Findings" /></h2>
  <mjl:dt attribute="symptomComments">
    <mjl:textarea param="symptomComments" cols="3" rows="3"/>
  </mjl:dt>
</mjl:component>

<%--
<c:if test="${item.isSymptomReadable}">
</c:if>
  --%>
  <dt>
    
  </dt>
  <dd>
    <table class="displayTable">
      <tr>
        <th><fmt:message key="Malaria_Symptom"/></th>
        <th><fmt:message key="Patient_Has_Symptom"/></th>
      </tr>
      <mjl:components items="${symptoms}" param="symptoms" var="current" varStatus="status">
        <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
          <td>
            ${current.child.displayLabel}
          </td>
          <td>
            <mjl:boolean param="hasSymptom"/>
            <mjl:messages attribute="hasSymptom">
              <mjl:message />
            </mjl:messages>
          </td>
        </tr>
      </mjl:components>
    </table>
  </dd>