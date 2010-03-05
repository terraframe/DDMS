<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_IndividualCase" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualCase.form.name" id="dss.vector.solutions.intervention.monitor.IndividualCase.form.id" method="POST">
    <mjl:input type="hidden" param="personId" value="${personId}"/>
    <%@include file="form.jsp" %>
    
    <mjl:component item="${item}" param="instance">
      <mjl:input type="hidden" param="individualCase" value="${item.individualCase.id}"/>
      <mjl:dt attribute="activelyDetected">
        <mjl:boolean param="activelyDetected" id="activelyDetected" />
      </mjl:dt>
      <div class="healthFacility">
        <mjl:dt attribute="healthFacility">
          <mdss:geo param="healthFacility" value="${healthFacility}" filter="${HEALTH_FACILITY}"/>
        </mjl:dt>
      </div>
      <div class="detectedBy">
        <mjl:dt attribute="detectedBy">
          <mdss:mo param="detectedBy" value="${detectedBy}" id="detectedBy"/>
        </mjl:dt>
      </div>
      
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
      <mjl:dt attribute="symptom">
        <mdss:multimo param="symptoms" id="symptoms" browserAttribute="symptom" value="${symptoms}" />
      </mjl:dt>  
    </mjl:component>

    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.create.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.form.create.button" />
  </mjl:form>
</dl>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var healthFacility = new MDSS.HiddenInputElement({element:'healthFacility'});
    var healthFacilityConcrete = new MDSS.HiddenInputElement({element:'healthFacility_geoEntityId'});
    var detectedBy = new MDSS.HiddenInputElement({element:'detectedBy'});
        
    MDSS.ElementHandler.setupBooleanHandler('activelyDetected.negative', 'activelyDetected.positive', [healthFacility, healthFacilityConcrete]);
    MDSS.ElementHandler.setupBooleanHandler('activelyDetected.positive', 'activelyDetected.negative', [detectedBy]);
  })
})();
</script>