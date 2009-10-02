<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="individualCase" value="${caseId}"/>
  <mjl:dt type="text" attribute="admissionDate" classes="DatePick" />
  <mjl:dt attribute="anaemiaPatient">
    <mjl:boolean param="anaemiaPatient" />
  </mjl:dt>
  <mjl:dt attribute="clinicalDiagnosis">
    <mjl:boolean param="clinicalDiagnosis" />
  </mjl:dt>
  <mjl:dt attribute="diedInFacility">
    <mjl:boolean param="diedInFacility" />
  </mjl:dt>
  <mjl:dt type="text" attribute="facilityVisit" classes="DatePick" />
  <mjl:dt attribute="labTest">
    <mjl:select var="current" valueAttribute="id" items="${labTest}" param="labTest">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt type="text" attribute="labTestDate" classes="DatePick" />
  <mjl:dt attribute="patientCategory">
    <mjl:select var="current" valueAttribute="id" items="${patientCategory}" param="patientCategory">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="pregnant">
    <mjl:boolean param="pregnant" />
  </mjl:dt>
  <mjl:dt attribute="properlyRelease">
    <mjl:boolean param="properlyRelease" />
  </mjl:dt>
  <mjl:dt attribute="referralReason">
    <mjl:select var="current" valueAttribute="id" items="${referralReason}" param="referralReason">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="referredFrom">
    <mjl:boolean param="referredFrom" />
  </mjl:dt>
  <mjl:dt attribute="referredTo">
    <mjl:boolean param="referredTo" />
  </mjl:dt>
  <mjl:dt type="text" attribute="releaseDate" classes="DatePick" />
  <mjl:dt type="text" attribute="symptomOnset" classes="DatePick" />
  <mjl:dt type="text" attribute="testSampleDate" classes="DatePick" />
  <mjl:dt attribute="treatment">
    <mjl:select var="current" valueAttribute="id" items="${treatment}" param="treatment">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
