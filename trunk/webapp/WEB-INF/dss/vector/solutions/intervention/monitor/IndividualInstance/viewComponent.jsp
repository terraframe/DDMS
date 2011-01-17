<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_IndividualInstance" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.name" id="dss.vector.solutions.intervention.monitor.IndividualInstance.form.id" method="POST">
    <%@include file="../IndividualCase/personHeader.jsp" %>
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="activelyDetected">
        ${item.activelyDetected ? item.activelyDetectedMd.positiveDisplayLabel : item.activelyDetectedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="healthFacility">
        ${item.healthFacility}
      </mjl:dt>
      <mjl:dt attribute="caseIdentifier">
        ${item.caseIdentifier}
      </mjl:dt>      
      <mjl:dt attribute="physician">
        ${physician.firstName} ${physician.lastName}
      </mjl:dt>      
            
      <mjl:dt attribute="detectedBy">
        ${item.detectedBy.displayLabel}
      </mjl:dt>
      
      <div id="Basic_Case_Information">
        <h2><mdss:localize key="Basic_Case_Information" /></h2>
      </div>
      <div id="Basic_Case_Information.content">
        <mjl:dt attribute="diagnosisType">
          <c:forEach items="${item.diagnosisTypeEnumNames}" var="enumName">
            <li>
              ${item.diagnosisTypeMd.enumItems[enumName]}
            </li>
          </c:forEach>        
        </mjl:dt>
        <mjl:dt attribute="diagnosis">
          ${item.diagnosis.displayLabel}
        </mjl:dt>
        <mjl:dt attribute="confirmedDiagnosis">
          ${item.confirmedDiagnosis.displayLabel}
        </mjl:dt>
        <mjl:dt attribute="confirmedDiagnosisDate">
          <span class="formatDate">
            ${item.confirmedDiagnosisDate}
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
        <mjl:dt attribute="dateOfDeath">
          <span class="formatDate">
            ${item.dateOfDeath}
          </span>
        </mjl:dt>
      </div>
      
      
      <div id="Administrative_Information">
        <h2><mdss:localize key="Administrative_Information" /></h2>
      </div>
      <div id="Administrative_Information.content">
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
      </div>
      
      <div id="Laboratory_Testing">
        <h2><mdss:localize key="Laboratory_Testing" /></h2>
      </div>
      <div id="Laboratory_Testing.content">
        <mjl:dt attribute="classification">
          ${item.classification.displayLabel}
        </mjl:dt>      
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
        <mjl:dt attribute="testResult">
          ${item.testResult.displayLabel}
        </mjl:dt>      
        <mjl:dt attribute="malariaType">
          ${item.malariaType.displayLabel}
        </mjl:dt>
        <mjl:dt attribute="primaryInfection">
          ${item.primaryInfection.displayLabel}
        </mjl:dt>
      </div>
      
      <div id="Treatment">
        <h2><mdss:localize key="Treatment" /></h2>
      </div>
      <div id="Treatment.content">
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
      </div>
      
      <div id = "Clinical_Findings">
        <h2><mdss:localize key="Clinical_Findings" /></h2>
      </div>
      <div id="Clinical_Findings.content">      
        <mjl:dt attribute="symptomComments">
          ${item.symptomComments}
        </mjl:dt>
        <mjl:dt attribute="symptom">
          <ul>
            <c:forEach items="${symptoms}" var="current"> 
              <li> ${current.displayLabel} </li> 
            </c:forEach>
          </ul>      
        </mjl:dt>          
      </div>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.edit.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.edit.button" />
  </mjl:form>
</dl>

<mjl:commandLink name="viewCase.link" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.view.mojo">
  <mdss:localize key="View_Case_Instances" />
  <mjl:property name="id" value="${item.individualCase.id}"/>    
</mjl:commandLink>

<br />

<mjl:commandLink name="search.link" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.newInstance.mojo">
  <mdss:localize key="Search_another_Individual_Case" />
</mjl:commandLink>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    // Hide all headers which have no children
    MDSS.Effect.toggleHeader('Clinical_Findings', 'Clinical_Findings.content');
    MDSS.Effect.toggleHeader('Treatment', 'Treatment.content');
    MDSS.Effect.toggleHeader('Laboratory_Testing', 'Laboratory_Testing.content');
    MDSS.Effect.toggleHeader('Administrative_Information', 'Administrative_Information.content');
    MDSS.Effect.toggleHeader('Basic_Case_Information', 'Basic_Case_Information.content');
  })
})();
</script>  
