<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.MDSSRoleInfo"%>

<c:set var="page_title" value="Select_Class" scope="request" />

<mjl:form name="search.form.name" id="search.form">
  <select  name="actor" id="roleSelectList">
    <c:forEach var="role" items="${actorOptions}">
      <c:choose>
        <c:when test="${actor == role.roleName}">
          <option value="${role.roleName}" selected="selected">${role.displayLabel}</option>
        </c:when>        
        <c:otherwise>
          <option value="${role.roleName}">${role.displayLabel}</option>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </select>
</mjl:form>

<script type='text/javascript'>
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    jumpBoxForm = document.getElementById('search.form');
    selectBox = document.getElementById('roleSelectList'); 

    selectBox.onchange = function(){
      jumpBoxForm.action = 'dss.vector.solutions.util.ReadableAttributeController.getUniversal.mojo';
      jumpBoxForm.submit();
    }
  })
})();    
</script>

<c:set var="action" value="dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo" scope="page"/>
  <!--  ADMINISTRATION -->
  <dl>
    <dt><label><f:message key="Configure_System_Variables"/></label></dt>
    <dd>
      <mjl:commandLink
        name="System_Variables"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.Property">
        <f:message key="System_Variables"/>
      </mjl:commandLink>
    </dd>
    
    <dt><label><f:message key="Configure_Malaria_Season"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Malaria_Season"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.MalariaSeason">
        <f:message key="Malaria_Season"/>
      </mjl:commandLink>
    </dd>    
    
    <dt><label><f:message key="Configure_Epi_Week"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Epi_Week"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.EpiConfiguration">
        <f:message key="Epi_Week"/>
      </mjl:commandLink>
    </dd>    
    
    <dt><label><f:message key="Enter_Population_Data"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Population_Data"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.PopulationDataView">
        <f:message key="Population_Data"/>
      </mjl:commandLink>
    </dd>        

    <dt><label><f:message key="Person_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="PersonView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.PersonView">
        <f:message key="Person"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="MDSSUserView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.MDSSUserView">
        <f:message key="User"/>
      </mjl:commandLink>    
    </dd>

    <dt><label><f:message key="Configure_Insecticide_Products"/></label></dt>
    <dd>
      <mjl:commandLink
        name="InsecticideBrand"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.InsecticideBrand">
        <f:message key="Insecticide_Brand"/>
      </mjl:commandLink>    
    </dd>
    
    <dt><label><f:message key="Ontology_Fields"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Browser_Root"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.ontology.BrowserRoot">
        <f:message key="Browser_Root"/>
      </mjl:commandLink>
    </dd>    

    <dt><label><f:message key="Ontology_Admin"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Term"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.ontology.Term">
        <f:message key="Term"/>
      </mjl:commandLink>
    </dd>      
    
    <dt><label><f:message key="Configure_the_Universal_Tree"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Universal"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.geo.GeoEntityDefinition">
        <f:message key="Universal"/>
      </mjl:commandLink>
    </dd>    

    <dt><label><f:message key="Manage_Geo_Entities"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Geo_Entity"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.geo.generated.GeoEntity">
        <f:message key="Geo_Entity"/>
      </mjl:commandLink>
    </dd>       
  </dl>
  
  <!--  CASE SURVEILLANCE -->
  <dl>
    <dt><label><f:message key="Aggregated_Cases_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="AggregatedCaseView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.AggregatedCaseSearchView">
        <f:message key="Aggregated_Case"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="CaseDiagnosisTypeView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.CaseDiagnosisTypeView">
        <f:message key="Case_Diagnosis_Type"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="CaseDiseaseManifestationView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.CaseDiseaseManifestationView">
        <f:message key="Case_Disease_Manifestation"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="CasePatientTypeView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.CasePatientTypeView">
        <f:message key="Case_Patient_Type"/>
      </mjl:commandLink>    
    </dd>
    
    <dt><label><f:message key="Manage_Individual_Cases"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Individual_Cases"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.IndividualCase">
        <f:message key="Individual_Cases"/>
      </mjl:commandLink>
    </dd>    
    <dd>
      <mjl:commandLink
        name="Individual_Instance"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.IndividualInstance">
        <f:message key="Individual_Instance"/>
      </mjl:commandLink>
    </dd>
    
    <dt><label><f:message key="Manage_Thresholds"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Threshold_Calculation"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.ThresholdCalculationTypeView">
        <f:message key="Configure_Thresholds"/>
      </mjl:commandLink>
    </dd>    
    
    <dt><label><f:message key="Manage_Threshold_Data"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Threshold_Data"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.ThresholdDataView">
        <f:message key="Threshold_Data"/>
      </mjl:commandLink>
    </dd>        
  </dl>  
  
  
  <!-- ENTOMOLOGICAL SURVEILLANCE -->
  <dl>
    <dt><label><f:message key="Mosquito_Collection_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="MosquitoCollectionView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.SearchMosquitoCollectionView">
        <f:message key="Mosquito_Collection"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="SubCollectionView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.SubCollectionView">
        <f:message key="Sub_Collection"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="ImmatureCollectionView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.ImmatureCollectionView">
        <f:message key="Immatures_by_Container_Type"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="PupalCollectionView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.PupalCollectionView">
        <f:message key="Pupal_Collection"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="PupalContainerView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.PupalContainerView">
        <f:message key="Pupal_Container"/>
      </mjl:commandLink>    
    </dd>
  
      <dt><label><f:message key="Assay_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="InfectionAssay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.InfectionAssayView">
        <f:message key="Infection_Assay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="PooledInfectionAssay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.PooledInfectionAssayView">
        <f:message key="Pooled_Infection_Assay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="BiochemicalAssayView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.BiochemicalAssayView">
        <f:message key="Biochemical_Assay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="MolecularAssayView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.MolecularAssayView">
        <f:message key="Molecular_Assay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="DiagnosticAssayView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.DiagnosticAssayView">
        <f:message key="Diagnostic_Dose_Assay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="TimeResponseAssayView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.TimeResponseAssayView">
        <f:message key="Time_Response_Assay"/>
      </mjl:commandLink>    
    </dd>    
    <dd>
      <mjl:commandLink
        name="AdultDiscriminatingDoseAssay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay">
        <f:message key="Adult_Diagnostic_Assay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="LarvaeDiscriminatingDoseAssay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay">
        <f:message key="Larvae_Diagnostic_Assay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="KnockDownAssay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.KnockDownAssay">
        <f:message key="Time_Response_Test"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="EfficacyAssayView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.EfficacyAssayView">
        <f:message key="Efficacy_Bioassay"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="Insecticide"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.Insecticide">
        <f:message key="Insecticide_Active_Ingredient"/>
      </mjl:commandLink>    
    </dd>    
  </dl>
  
  
  <!--  SURVEYS -->
  <dl>
    <dt><label><f:message key="Indicator_Survey_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="SurveyPoint"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.SurveyPointView">
        <f:message key="Survey_Point"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="Household"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.HouseholdView">
        <f:message key="Household"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="SurveyedPersonView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.SurveyedPersonView">
        <f:message key="Surveyed_Person"/>
      </mjl:commandLink>    
    </dd>  
    <dd>
      <mjl:commandLink
        name="ITNInstanceView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.ITNInstanceView">
        <f:message key="ITNs"/>
      </mjl:commandLink>    
    </dd>
  </dl>
  
  
  <!-- INTERVENTION PLANNING -->
  <dl>
    <dt><label><f:message key="Configure_Application_Rate_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="NozzleView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.NozzleView">
        <f:message key="Nozzle"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="InsecticideNozzleView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.InsecticideNozzleView">
        <f:message key="Insecticide_Nozzle"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="AreaStandardsView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.AreaStandardsView">
        <f:message key="Area_Standards"/>
      </mjl:commandLink>    
    </dd>

    <dt><label><f:message key="IRS_Team_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Universal"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.SprayTeamView">
        <f:message key="Spray_Team"/>
      </mjl:commandLink>    
    </dd>

    <dt><label><f:message key="Target_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="ResourceTarget"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.ResourceTargetView">
        <f:message key="Resource_Target"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="GeoTargetView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.GeoTargetView">
        <f:message key="Geo_Target"/>
      </mjl:commandLink>    
    </dd>  
  </dl>
  
  
  <!-- INTERVENTION MONITORING -->
  <dl>
    <dt><label><f:message key="IRS_CRUD_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="OperatorSprayView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.OperatorSprayView">
        <f:message key="Operator_Spray"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="TeamSprayView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.TeamSprayView">
        <f:message key="Team_Spray"/>
      </mjl:commandLink>
    </dd>
    <dd>
      <mjl:commandLink
        name="ZoneSpray"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.ZoneSprayView">
        <f:message key="Area_Spray"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="HouseholdSprayStatusView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.HouseholdSprayStatusView">
        <f:message key="Household_Spray_Status"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="OperatorSprayStatusView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.OperatorSprayStatusView">
        <f:message key="Operator_Spray_Status"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="TeamSprayStatusView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.TeamSprayStatusView">
        <f:message key="Team_Spray_Status"/>
      </mjl:commandLink>    
    </dd>    
  
    <dt><label><f:message key="Manage_Aggregated_IPT"/></label></dt>
    <dd>
      <mjl:commandLink
        name="AggregatedIPT"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.AggregatedIPTView">
        <f:message key="Aggregated_IPT_Information"/>
      </mjl:commandLink>
    </dd>  
    
    <dt><label><f:message key="Manage_Individual_IPT"/></label></dt>
    <dd>
      <mjl:commandLink
        name="IndividualIPTCase"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.IndividualIPTCaseView">
        <f:message key="Individual_IPT_Case"/>
      </mjl:commandLink>
    </dd>
    <dd>
      <mjl:commandLink
        name="IndividualIPT"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.IndividualIPTView">
        <f:message key="Individual_IPT"/>
      </mjl:commandLink>
    </dd>    

    <dt><label><f:message key="ITN_Data_Distribution"/></label></dt>
    <dd>
      <mjl:commandLink
        name="ITNData"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.ITNDataView">
        <f:message key="Aggregated_ITN_Data_Distribution"/>
      </mjl:commandLink>
    </dd>
    <dd>
      <mjl:commandLink
        name="ITNCommunityDistribution"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView">
        <f:message key="ITN_Community_Distribution"/>
      </mjl:commandLink>
    </dd>
    <dd>
      <mjl:commandLink
        name="ITNDistribution"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.ITNDistributionView">
        <f:message key="ITN_Facility_Distribution"/>
      </mjl:commandLink>
    </dd>

    <dt><label><f:message key="Control_of_Immatures"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Larvacide"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.Larvacide">
        <f:message key="Larvacide"/>
      </mjl:commandLink>
    </dd>    
    <dd>
      <mjl:commandLink
        name="Larvacide_Instance"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.LarvacideInstanceView">
        <f:message key="Larvacide_Instance"/>
      </mjl:commandLink>
    </dd>    

    <dt><label><f:message key="Manage_Control_Intervention"/></label></dt>
    <dd>
      <mjl:commandLink
        name="ControlInterventionView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.ControlInterventionView">
        <f:message key="Control_intervention"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="IndividualPremiseVisitView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView">
        <f:message key="Individual_premises_Visit"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="AggregatedPremiseVisitView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView">
        <f:message key="Aggregated_Premises_Visit"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="PersonInterventionView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.PersonInterventionView">
        <f:message key="Person_Days_Used_for_Intervention"/>
      </mjl:commandLink>    
    </dd>
    <dd>
      <mjl:commandLink
        name="InsecticideInterventionView"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.InsecticideInterventionView">
        <f:message key="Insecticide_Use_For_Intervention"/>
      </mjl:commandLink>    
    </dd>
  </dl>


  <!-- STOCK CONTROL -->
  <dl>
    <dt><label><f:message key="Manage_Stock"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Stock_Item"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.stock.StockItemView">
        <f:message key="Stock_Item"/>
      </mjl:commandLink>
    </dd>    
    <dd>
      <mjl:commandLink
        name="Stock_Event"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.stock.StockEventView">
        <f:message key="Stock_Event"/>
      </mjl:commandLink>
    </dd>    
  </dl>  
