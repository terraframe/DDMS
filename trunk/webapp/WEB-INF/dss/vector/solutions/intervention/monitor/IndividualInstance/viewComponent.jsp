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
      <mjl:dt attribute="admissionDate">
        <span class="formatDate">
          ${item.admissionDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="anaemiaPatient">
        ${item.anaemiaPatient ? item.anaemiaPatientMd.positiveDisplayLabel : item.anaemiaPatientMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="clinicalDiagnosis">
        ${item.clinicalDiagnosis ? item.clinicalDiagnosisMd.positiveDisplayLabel : item.clinicalDiagnosisMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="diedInFacility">
        ${item.diedInFacility ? item.diedInFacilityMd.positiveDisplayLabel : item.diedInFacilityMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="facilityVisit">
        <span class="formatDate">
          ${item.facilityVisit}
        </span>
      </mjl:dt>
      <mjl:dt attribute="labTest">
        ${item.labTest.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="labTestDate">
        <span class="formatDate">
          ${item.labTestDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="patientCategory">
        ${item.patientCategory.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="pregnant">
        ${item.pregnant ? item.pregnantMd.positiveDisplayLabel : item.pregnantMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="properlyRelease">
        ${item.properlyRelease ? item.properlyReleaseMd.positiveDisplayLabel : item.properlyReleaseMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="referralReason">
        ${item.referralReason.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="referredFrom">
        ${item.referredFrom ? item.referredFromMd.positiveDisplayLabel : item.referredFromMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="referredTo">
        ${item.referredTo ? item.referredToMd.positiveDisplayLabel : item.referredToMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="releaseDate">
        <span class="formatDate">
          ${item.releaseDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="symptomOnset">
        <span class="formatDate">
          ${item.symptomOnset}
        </span>
      </mjl:dt>
      <mjl:dt attribute="testSampleDate">
        <span class="formatDate">
          ${item.testSampleDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="treatment">
        ${item.treatment.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.edit.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>
