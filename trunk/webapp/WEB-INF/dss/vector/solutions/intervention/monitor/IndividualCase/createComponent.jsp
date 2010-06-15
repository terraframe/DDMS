<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.PersonDTO"%>
<%@page import="dss.vector.solutions.PersonController"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.PersonWithDelegatesViewDTO"%>
<%@page import="dss.vector.solutions.PhysicianDTO"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<c:set var="page_title" value="Create_IndividualCase" scope="request" />

<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualCase.form.name" id="dss.vector.solutions.intervention.monitor.IndividualCase.form.id" method="POST">
    <mjl:input type="hidden" param="personId" value="${personId}"/>

    <!--
      CASE SPECIFIC FIELDS
    -->

    <%@include file="personHeader.jsp" %>
    <mjl:component item="${individualCase}" param="dto">
      <mjl:dt type="text" attribute="caseReportDate" classes="DatePick NoFuture" />
      <mjl:dt type="text" attribute="diagnosisDate" classes="DatePick NoFuture" />
      <mjl:dt attribute="residence">
        <mdss:geo param="residence" value="${individualCase.residence}" />  
      </mjl:dt>
      <mjl:dt attribute="residenceText">
        <mjl:textarea param="residenceText" cols="3" rows="3"/>
      </mjl:dt>
      <mjl:dt attribute="probableSource">
        <mdss:geo param="probableSource" value="${individualCase.probableSource}" />  
      </mjl:dt>
      <mjl:dt attribute="probableSourceText">
        <mjl:textarea param="probableSourceText" cols="3" rows="3"/>
      </mjl:dt>
      <mjl:dt attribute="workplace">
        <mdss:geo param="workplace" value="${individualCase.workplace}" />  
      </mjl:dt>
      <mjl:dt attribute="workplaceText">
        <mjl:textarea param="workplaceText" cols="3" rows="3"/>
      </mjl:dt>
      <mjl:dt attribute="otherSettlements">
        <mjl:textarea param="otherSettlements" cols="3" rows="3"/>
      </mjl:dt>
    </mjl:component>
    
    <!-- 
      INSTANCE SPECIFIC FIELDS    
     -->
    
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
              <fmt:message key="Create_new_Physician"/>        
            </span>
            <span id="editPhysician" >
              <fmt:message key="Edit_Physician"/>        
            </span>
          </span>
        </mjl:dt>      
      </div>      
      <div class="detectedBy">
        <mjl:dt attribute="detectedBy">
          <mdss:mo param="detectedBy" value="${detectedBy}" id="detectedBy"/>
        </mjl:dt>
      </div>
      
      <h2><fmt:message key="Basic_Case_Information" /></h2>
      
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
 
      <mjl:dt attribute="detectedBy">
        <mdss:mo param="detectedBy" value="${detectedBy}" id="detectedBy"/>
      </mjl:dt>
      
      <mjl:dt type="text" attribute="symptomOnset" classes="DatePick  NoFuture" />
      <mjl:dt type="text" attribute="facilityVisit" classes="DatePick  NoFuture" />
    </mjl:component>
    
    
    <!--
      IMPORTANT: THESE ARE CASE SPECIFIC ENTERIES
     -->
    <mjl:component item="${individualCase}" param="dto">
      <mjl:dt attribute="origin">
        <mdss:mo param="origin" value="${origin}" />
      </mjl:dt>
      <mjl:dt attribute="plasmaLeakageOnset">
        <mjl:input type="text" param="plasmaLeakageOnset" id="plasmaLeakageOnset" classes="DatePick NoFuture" />
      </mjl:dt>
      <mjl:dt attribute="hemorrhagicOnset">
        <mjl:input type="text" param="hemorrhagicOnset" id="hemorrhagicOnset" classes="DatePick NoFuture" />
      </mjl:dt>    
    </mjl:component>

    <!-- 
      INSTANCE SPECIFIC FIELDS    
     -->
    <mjl:component item="${item}" param="instance">
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

<%=Halp.loadTypes(new String[]{PhysicianDTO.CLASS, PersonViewDTO.CLASS, PersonWithDelegatesViewDTO.CLASS, PersonController.CLASS}) %>

<script type="text/javascript">
Mojo.Meta.newClass('MDSS.PhysicianModal', {
  Extends : MDSS.AbstractPersonModal,
  Instance: {
    initialize : function(prop) {
      this.$initialize(prop);

      this._input = (Mojo.Util.isString(prop.input) ? document.getElementById(prop.input) : prop.input);

      var dF = Mojo.Util.bind(this, this.listFunction);
      var iF = Mojo.Util.bind(this, this.idFunction);
      var lF = Mojo.Util.bind(this, this.listFunction);
      var sF = Mojo.Util.bind(this, this.searchFunction);
      var sEH = Mojo.Util.bind(this, this.selectEventHandler);
  
      this.search = new MDSS.GenericSearch(this._input, this._concrete, lF, dF, iF, sF, sEH);
      this.search.addListener(Mojo.Util.bind(this, this.eventHandler));
      
      if(this._concrete.value != '') {
        this.selectEventHandler();
      }
      else {
        this.showCreatePatient();  
      }
    },
    
    apply : function() {
      var request = new MDSS.Request({
        that : this,
        onSuccess: function(view) {
          var id = view.getValue(Mojo.$.dss.vector.solutions.PersonWithDelegatesView.PHYSICIANDELEGATE);
          this.that._concrete.value = id;
          this.that._input.value = view.getFirstName() + ' ' + view.getLastName();

          this.that.destroyModal();

          this.that.selectEventHandler();
        }
      });
      
      var component = this.populateComponent();

      component.applyAsPhysician(request);
    },
    
    populateModal : function() {
    },

    getAction : function() {
      return Mojo.$.dss.vector.solutions.PersonController.editPhysician;
    },
      
    searchFunction : function(request, value) {
      Mojo.$.dss.vector.solutions.Physician.searchForPhysician(request, value);
    },
   
    listFunction : function(valueObject) {
      var firstName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.FIRSTNAME);
      var lastName = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.LASTNAME);

      return firstName + ' ' + lastName;
    },

    idFunction : function(valueObject) {
      var id = valueObject.getValue(Mojo.$.dss.vector.solutions.PersonView.ID);

      return id;
    }
  }
});

(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var prop = {
      input : 'physicianInput',
      concrete : 'physician',
      createLink : 'createPhysician',
      editLink : 'editPhysician', 
      clickable : 'physician.span', 
      calendar : 'dateOfBirth'
    };
      
    new MDSS.PhysicianModal(prop);

    
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