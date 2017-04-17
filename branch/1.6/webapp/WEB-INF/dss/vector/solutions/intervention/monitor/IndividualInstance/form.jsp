<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.intervention.monitor.IndividualInstanceDTO"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.PhysicianDTO"%>
<%@page import="dss.vector.solutions.PersonWithDelegatesViewDTO"%>
<%@page import="dss.vector.solutions.PersonController"%>


<jsp:include page="/WEB-INF/selectSearch.jsp" />
<%@include file="../IndividualCase/personHeader.jsp" %>

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="individualCase" value="${item.individualCase.id}"/>
  <mjl:dt attribute="activelyDetected">
    <mjl:boolean param="activelyDetected" id="activelyDetected" />
  </mjl:dt>
  <div class="healthFacility">
    <mjl:dt attribute="healthFacility">
      <mdss:geo param="healthFacility" value="${healthFacility}" filter="${HEALTH_FACILITY}" />
    </mjl:dt>
  </div>
  <div class="caseIdentifier">
    <mjl:dt attribute="caseIdentifier">
     <mjl:input type="text" param="caseIdentifier" id="caseIdentifier"/>
    </mjl:dt>      
  </div>
  <div class="physician">
    <mjl:dt attribute="physician">
      <mjl:input id="physicianInput" param="physicianInput" type="text" value="${physicianLabel}"/>
      <mjl:input id="physician" param="physician" type="hidden" value="${physicianId}"/>
          
      <span class="clickable" id="physician.span">
        <span id="createPhysician">
          <mdss:localize key="Create_New_Physician"/>        
        </span>
        <span id="editPhysician" >
          <mdss:localize key="Edit_Physician"/>        
        </span>
      </span>
    </mjl:dt>      
  </div>      
  <div class="detectedBy">
    <mjl:dt attribute="detectedBy">
      <mdss:mo param="detectedBy" value="${detectedBy}" id="detectedBy"/>
    </mjl:dt>
  </div>
      
  <div id="Basic_Case_Information">
  <h2><mdss:localize key="Basic_Case_Information" /></h2>
  </div>
  <div id="Basic_Case_Information.content">      
    <mjl:dt attribute="diagnosisType">
      <mjl:select param="diagnosisType" items="${diagnosisType}" var="current" valueAttribute="enumName" id="diagnosisType">
        <mjl:option selected="${mjl:contains(item.diagnosisTypeEnumNames, current.enumName) ? 'selected' : 'false'}">
          ${item.diagnosisTypeMd.enumItems[current.enumName]}
        </mjl:option>
      </mjl:select>        
    </mjl:dt>
    <mjl:dt attribute="diagnosis">
      <mdss:mo param="diagnosis" value="${diagnosis}" id="diagnosis"/>
    </mjl:dt>
    <mjl:dt attribute="confirmedDiagnosis">
      <mdss:mo param="confirmedDiagnosis" value="${confirmedDiagnosis}" id="confirmedDiagnosis"/>
    </mjl:dt>
    <mjl:dt type="text" attribute="confirmedDiagnosisDate" classes="DatePick  NoFuture" /> 
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
    <mjl:dt type="text" attribute="dateOfDeath" classes="DatePick  NoFuture" />
  </div>
      
  <div id="Administrative_Information">
    <h2><mdss:localize key="Administrative_Information" /></h2>
  </div>
  <div id="Administrative_Information.content">
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
  </div>

  <div id="Laboratory_Testing">
    <h2><mdss:localize key="Laboratory_Testing" /></h2>
  </div>          
  <div id="Laboratory_Testing.content">
    <mjl:dt attribute="classification">
      <mdss:mo param="classification" value="${classification}"/>
    </mjl:dt>
    <mjl:dt attribute="sampleType">
      <mdss:mo param="sampleType" value="${sampleType}"/>
    </mjl:dt>
    <mjl:dt attribute="labTest">
      <mdss:mo param="labTest" value="${labTest}"/>
    </mjl:dt>
    <mjl:dt type="text" attribute="testSampleDate" classes="DatePick  NoFuture" />
    <mjl:dt type="text" attribute="labTestDate" classes="DatePick  NoFuture" />
    <mjl:dt attribute="testResult">
      <mdss:mo param="testResult" value="${testResult}"/>
    </mjl:dt>
    <mjl:dt attribute="malariaType">
      <mdss:mo param="malariaType" value="${malariaType}"/>
    </mjl:dt>
    <mjl:dt attribute="primaryInfection">
      <mdss:mo param="primaryInfection" value="${primaryInfection}"/>
    </mjl:dt>
  </div>    
    
  <div id="Treatment">
    <h2><mdss:localize key="Treatment" /></h2>
  </div>
  <div id="Treatment.content">
    <mjl:dt attribute="treatmentMethod">
      <mdss:mo param="treatmentMethod" value="${treatmentMethod}"/>
    </mjl:dt>
    <mjl:dt attribute="treatment">
      <mdss:mo param="treatment" value="${treatment}"/>
    </mjl:dt>
    <mjl:dt type="text" attribute="treatmentStartDate" classes="DatePick  NoFuture" />
  </div> 
      
  <div id="Clinical_Findings">
    <h2><mdss:localize key="Clinical_Findings" /></h2>
  </div>
  <div id="Clinical_Findings.content">
    <mjl:dt attribute="symptomComments">
      <mjl:textarea param="symptomComments" cols="3" rows="3"/>
    </mjl:dt>
    <mjl:dt attribute="symptom">
      <mdss:multimo param="symptoms" id="symptoms" browserAttribute="symptom" value="${symptoms}" />
    </mjl:dt>  
  </div>
</mjl:component>

<%=Halp.loadTypes(new String[]{PhysicianDTO.CLASS, PersonViewDTO.CLASS, PersonWithDelegatesViewDTO.CLASS, PersonController.CLASS}) %>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    if(document.getElementById('physician') != null) {
      var prop = {
        input : 'physicianInput',
        concrete : 'physician',
        createLink : 'createPhysician',
        editLink : 'editPhysician', 
        clickable : 'physician.span', 
        calendar : 'dateOfBirth'
      };
        
      new MDSS.PhysicianModal(prop);
    }

    // Hide all headers which have no children
    MDSS.Effect.toggleHeader('Clinical_Findings', 'Clinical_Findings.content');
    MDSS.Effect.toggleHeader('Treatment', 'Treatment.content');
    MDSS.Effect.toggleHeader('Laboratory_Testing', 'Laboratory_Testing.content');
    MDSS.Effect.toggleHeader('Administrative_Information', 'Administrative_Information.content');
    MDSS.Effect.toggleHeader('Basic_Case_Information', 'Basic_Case_Information.content');
    
    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var healthFacility = new MDSS.HiddenInputElement({element:'healthFacility'});
    var healthFacilityConcrete = new MDSS.HiddenInputElement({element:'healthFacility_geoEntityId'});
    var caseIdentifier = new MDSS.HiddenInputElement({element:'caseIdentifier'});
    var physician = new MDSS.HiddenInputElement({element:'physician'});
    var physicianInput = new MDSS.HiddenInputElement({element:'physicianInput'});
    var detectedBy = new MDSS.HiddenInputElement({element:'detectedBy'});
        
    MDSS.ElementHandler.setupBooleanHandler('activelyDetected.negative', 'activelyDetected.positive', [healthFacility, healthFacilityConcrete, caseIdentifier, physician, physicianInput]);
    MDSS.ElementHandler.setupBooleanHandler('activelyDetected.positive', 'activelyDetected.negative', [detectedBy]);
  })
})();
</script>  