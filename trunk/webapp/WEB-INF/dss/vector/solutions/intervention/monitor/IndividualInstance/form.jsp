<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="dss.vector.solutions.intervention.monitor.IndividualInstanceDTO"%>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="individualCase" value="${item.individualCase}"/>
  <mjl:dt attribute="activelyDetected">
    <mjl:boolean param="activelyDetected" />
  </mjl:dt>
  <mjl:dt attribute="healthFacility">
    <mjl:input value="${individualCase.healthFacility != null ? individualCase.healthFacility.geoId : ''}" type="text" param="healthFacilityId" classes="geoInput" id="healthFacilityGeoId" />
    <mjl:input type="hidden" param="healthFacility" id="healthFacilityGeoId_geoEntityId" />
  </mjl:dt>
  <mjl:dt attribute="detectedBy">
    <span class="clickable browserLauncher" id="detectedByBtn"> <fmt:message key="Browser"/></span>
    <div id="detectedByDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${detectedBy != null}">
          ${detectedBy.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="detectedBy" id="detectedBy" value="${detectedBy != null ? detectedBy.id : ''}" />
  </mjl:dt>
  
  <h2><fmt:message key="Basic_Case_Information" /></h2>
  <mjl:dt attribute="clinicalDiagnosis">
    <mjl:boolean param="clinicalDiagnosis" />
  </mjl:dt>
  <mjl:dt type="text" attribute="symptomOnset" classes="DatePick" />
  <mjl:dt type="text" attribute="facilityVisit" classes="DatePick" />
  <mjl:dt attribute="patientCategory">
    <span class="clickable browserLauncher" id="patientCategoryBtn"> <fmt:message key="Browser"/></span>
    <div id="patientCategoryDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${patientCategory != null}">
          ${patientCategory.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="patientCategory" id="patientCategory" value="${patientCategory != null ? patientCategory.id : ''}" />
  </mjl:dt>
  <mjl:dt type="text" attribute="admissionDate" classes="DatePick" />
  <mjl:dt type="text" attribute="releaseDate" classes="DatePick" />
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
    <span class="clickable browserLauncher" id="referralReasonBtn"> <fmt:message key="Browser"/></span>
    <div id="referralReasonDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${referralReason != null}">
          ${referralReason.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="referralReason" id="referralReason" value="${referralReason != null ? referralReason.id : ''}" />
  </mjl:dt>

  <h2><fmt:message key="Laboratory_Testing" /></h2>
  <mjl:dt attribute="sampleType">
    <span class="clickable browserLauncher" id="sampleTypeBtn"> <fmt:message key="Browser"/></span>
    <div id="sampleTypeDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${sampleType != null}">
          ${sampleType.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="sampleType" id="sampleType" value="${sampleType != null ? sampleType.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="labTest">
    <span class="clickable browserLauncher" id="labTestBtn"> <fmt:message key="Browser"/></span>
    <div id="labTestDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${labTest != null}">
          ${labTest.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="labTest" id="labTest" value="${labTest != null ? labTest.id : ''}" />
  </mjl:dt>
  <mjl:dt type="text" attribute="testSampleDate" classes="DatePick" />
  <mjl:dt type="text" attribute="labTestDate" classes="DatePick" />
  <mjl:dt attribute="malariaType">
    <span class="clickable browserLauncher" id="malariaTypeBtn"> <fmt:message key="Browser"/></span>
    <div id="malariaTypeDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${malariaType != null}">
          ${malariaType.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="malariaType" id="malariaType" value="${malariaType != null ? malariaType.id : ''}" />
  </mjl:dt>

  <h2><fmt:message key="Treatment" /></h2>
  <mjl:dt attribute="treatmentMethod">
    <span class="clickable browserLauncher" id="treatmentMethodBtn"> <fmt:message key="Browser"/></span>
    <div id="treatmentMethodDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${treatmentMethod != null}">
          ${treatmentMethod.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="treatmentMethod" id="treatmentMethod" value="${treatmentMethod != null ? treatmentMethod.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="treatment">
    <span class="clickable browserLauncher" id="treatmentBtn"> <fmt:message key="Browser"/></span>
    <div id="treatmentDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${treatment != null}">
          ${treatment.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="treatment" id="treatment" value="${treatment != null ? treatment.id : ''}" />
  </mjl:dt>
  <mjl:dt type="text" attribute="treatmentStartDate" classes="DatePick" />
  
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
  
<script type="text/javascript">  
  YAHOO.util.Event.onDOMReady(function(){   
    var attributes = [
         {attributeName:'sampleType'},
         {attributeName:'labTest'},
         {attributeName:'malariaType'},
         {attributeName:'patientCategory'},
         {attributeName:'referralReason'},
         {attributeName:'treatment'},
         {attributeName:'treatmentMethod'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=IndividualInstanceDTO.CLASS%>", attributes);
    });
</script>