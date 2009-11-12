<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_IndividualInstance" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.name" id="dss.vector.solutions.intervention.monitor.IndividualInstance.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="activelyDetected">
        ${item.activelyDetected ? item.activelyDetectedMd.positiveDisplayLabel : item.activelyDetectedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="healthFacility">
        ${item.healthFacility}
      </mjl:dt>
      <mjl:dt attribute="detectedBy">
        ${item.detectedBy.displayLabel}
      </mjl:dt>
      
      <h2><fmt:message key="Basic_Case_Information" /></h2>
      <mjl:dt attribute="clinicalDiagnosis">
        ${item.clinicalDiagnosis ? item.clinicalDiagnosisMd.positiveDisplayLabel : item.clinicalDiagnosisMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="symptomOnset">
        <span class="formatDate">
          ${item.symptomOnset}
        </span>
      </mjl:dt>
      <mjl:dt attribute="facilityVisit">
        <span class="formatDate">
          ${item.facilityVisit}
        </span>
      </mjl:dt>
      <mjl:dt attribute="patientCategory">
        ${item.patientCategory.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="admissionDate">
        <span class="formatDate">
          ${item.admissionDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="releaseDate">
        <span class="formatDate">
          ${item.releaseDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="anaemiaPatient">
        ${item.anaemiaPatient ? item.anaemiaPatientMd.positiveDisplayLabel : item.anaemiaPatientMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="pregnant">
        ${item.pregnant ? item.pregnantMd.positiveDisplayLabel : item.pregnantMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="diedInFacility">
        ${item.diedInFacility ? item.diedInFacilityMd.positiveDisplayLabel : item.diedInFacilityMd.negativeDisplayLabel}
      </mjl:dt>
      
      <h2><fmt:message key="Administrative_Information" /></h2>
      <mjl:dt attribute="properlyRelease">
        ${item.properlyRelease ? item.properlyReleaseMd.positiveDisplayLabel : item.properlyReleaseMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="referredTo">
        ${item.referredTo ? item.referredToMd.positiveDisplayLabel : item.referredToMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="referredFrom">
        ${item.referredFrom ? item.referredFromMd.positiveDisplayLabel : item.referredFromMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="referralReason">
        ${item.referralReason.displayLabel}
      </mjl:dt>
      
      <h2><fmt:message key="Laboratory_Testing" /></h2>
      <mjl:dt attribute="sampleType">
        ${item.sampleType.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="labTest">
        ${item.labTest.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="testSampleDate">
        <span class="formatDate">
          ${item.testSampleDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="labTestDate">
        <span class="formatDate">
          ${item.labTestDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="malariaType">
        ${item.malariaType.displayLabel}
      </mjl:dt>
      
      <h2><fmt:message key="Treatment" /></h2>
      <mjl:dt attribute="treatmentMethod">
        ${item.treatmentMethod.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="treatment">
        ${item.treatment.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="treatmentStartDate">
        <span class="formatDate">
          ${item.treatmentStartDate}
        </span>
      </mjl:dt>
      
      <h2><fmt:message key="Clinical_Findings" /></h2>
      <mjl:dt attribute="symptomComments">
        ${item.symptomComments}
      </mjl:dt>
    </mjl:component>
    
	<%--
	<c:if test="${item.isCaseStocksReadable}">
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
	          ${current.hasSymptom ? current.hasSymptomMd.positiveDisplayLabel :current.hasSymptomMd.negativeDisplayLabel}
	          <mjl:messages attribute="symptoms">
	            <mjl:message />
	          </mjl:messages>
	        </td>
	      </tr>
	    </mjl:components>
	  </table>
	</dd>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.edit.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.edit.button" />
  </mjl:form>
</dl>
