<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.MDSSRoleInfo"%>
<%@page import="java.util.List"%>
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
    <dt><label><mdss:localize key="Configure_System_Variables"/></label></dt>
    <dd>
      <mdss:localize key="System_Variables" var="component"/>
      <mjl:commandLink
        name="System_Variables"
        action="${action}">
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>
        <mjl:property name="universal" value="dss.vector.solutions.Property"/>
        ${component}
      </mjl:commandLink>
    </dd>
    
    <dt><label><mdss:localize key="Configure_Malaria_Season"/></label></dt>
    <dd>
      <mdss:localize key="Malaria_Season" var="component"/>
      <mjl:commandLink
        name="Malaria_Season"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.general.MalariaSeason" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>
        ${component}        
      </mjl:commandLink>
    </dd>    
    
    <dt><label><mdss:localize key="Configure_Epi_Week"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Epi_Week"/>
      <mjl:commandLink
        name="Epi_Week"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.general.EpiConfiguration" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>
        ${component}        
      </mjl:commandLink>
    </dd>    
    
    <dt><label><mdss:localize key="Enter_Population_Data"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Population_Data"/>
      <mjl:commandLink
        name="Population_Data"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.general.PopulationDataView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>        

    <dt><label><mdss:localize key="Person_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Person"/>
      <mjl:commandLink
        name="PersonView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.PersonView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="User"/>
      <mjl:commandLink
        name="MDSSUserView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.MDSSUserView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>

    <dt><label><mdss:localize key="Configure_Insecticide_Products"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Insecticide_Brand"/>
      <mjl:commandLink
        name="InsecticideBrand"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.InsecticideBrand" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component} 
      </mjl:commandLink>    
    </dd>
    
    <dt><label><mdss:localize key="Ontology_Fields"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Browser_Root"/>
      <mjl:commandLink
        name="Browser_Root"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.ontology.BrowserRoot" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    

    <dt><label><mdss:localize key="Ontology_Admin"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Term"/>
      <mjl:commandLink
        name="Term"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.ontology.Term" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>      
    
    <dt><label><mdss:localize key="Configure_the_Universal_Tree"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Universal"/>
      <mjl:commandLink
        name="Universal"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.geo.GeoEntityDefinition" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    

    <dt><label><mdss:localize key="Manage_Geo_Entities"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Geo_Entity"/>
      <mjl:commandLink
        name="Geo_Entity"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.geo.generated.GeoEntity" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>       
  </dl>
  
  <!--  CASE SURVEILLANCE -->
  <dl>
    <dt><label><mdss:localize key="Aggregated_Cases_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Aggregated_Case"/>
      <mjl:commandLink
        name="AggregatedCaseView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.surveillance.AggregatedCaseSearchView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Case_Diagnosis_Type"/>
      <mjl:commandLink
        name="CaseDiagnosisTypeView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.surveillance.CaseDiagnosisTypeView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Case_Disease_Manifestation"/>
      <mjl:commandLink
        name="CaseDiseaseManifestationView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.surveillance.CaseDiseaseManifestationView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Case_Patient_Type"/>
      <mjl:commandLink
        name="CasePatientTypeView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.surveillance.CasePatientTypeView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    
    <dt><label><mdss:localize key="Manage_Individual_Cases"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Individual_Cases"/>
      <mjl:commandLink
        name="Individual_Cases"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.IndividualCase" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    
    <dd>
      <mdss:localize var="component" key="Individual_Instance"/>
      <mjl:commandLink
        name="Individual_Instance"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.IndividualInstance" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>
    
    <dt><label><mdss:localize key="Manage_Thresholds"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Configure_Thresholds"/>
      <mjl:commandLink
        name="Threshold_Calculation"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.general.ThresholdCalculationTypeView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    
    
    <dt><label><mdss:localize key="Manage_Threshold_Data"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Threshold_Data"/>
      <mjl:commandLink
        name="Threshold_Data"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.general.ThresholdDataView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}        
      </mjl:commandLink>
    </dd>        
  </dl>  
  
  
  <!-- ENTOMOLOGICAL SURVEILLANCE -->
  <dl>
    <dt><label><mdss:localize key="Mosquito_Collection_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Mosquito_Collection"/>
      <mjl:commandLink
        name="MosquitoCollectionView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.SearchMosquitoCollectionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Sub_Collection"/>
      <mjl:commandLink
        name="SubCollectionView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.SubCollectionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Immatures_by_Container_Type"/>
      <mjl:commandLink
        name="ImmatureCollectionView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.ImmatureCollectionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component} 
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Pupal_Collection"/>
      <mjl:commandLink
        name="PupalCollectionView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.PupalCollectionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component} 
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Pupal_Container"/>
      <mjl:commandLink
        name="PupalContainerView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.PupalContainerView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component} 
      </mjl:commandLink>    
    </dd>
  
      <dt><label><mdss:localize key="Assay_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Infection_Assay"/>
      <mjl:commandLink
        name="InfectionAssay"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.InfectionAssayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component} 
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Pooled_Infection_Assay"/>
      <mjl:commandLink
        name="PooledInfectionAssay"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.PooledInfectionAssayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Biochemical_Assay"/>
      <mjl:commandLink
        name="BiochemicalAssayView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.BiochemicalAssayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component} 
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Molecular_Assay"/>
      <mjl:commandLink
        name="MolecularAssayView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.MolecularAssayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Diagnostic_Dose_Assay"/>
      <mjl:commandLink
        name="DiagnosticAssayView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.DiagnosticAssayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component} 
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Time_Response_Assay"/>
      <mjl:commandLink
        name="TimeResponseAssayView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.TimeResponseAssayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>    
    <dd>
      <mdss:localize var="component" key="Adult_Diagnostic_Assay"/>
      <mjl:commandLink
        name="AdultDiscriminatingDoseAssay"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Adult_Diagnostic_Assay_Interval"/>
      <mjl:commandLink
        name="AdultDiscriminatingDoseAssay"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseIntervalView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Larvae_Diagnostic_Assay"/>
      <mjl:commandLink
        name="LarvaeDiscriminatingDoseAssay"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Time_Response_Test"/>
      <mjl:commandLink
        name="KnockDownAssay"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.assay.KnockDownAssay" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Time_Response_Test_Interval"/>
      <mjl:commandLink
        name="KnockDownAssay"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.assay.KnockDownIntervalView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Efficacy_Bioassay"/>
      <mjl:commandLink
        name="EfficacyAssayView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.entomology.assay.EfficacyAssayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Insecticide_Active_Ingredient"/>
      <mjl:commandLink
        name="Insecticide"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.general.Insecticide" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>    
  </dl>
  
  
  <!--  SURVEYS -->
  <dl>
    <dt><label><mdss:localize key="Indicator_Survey_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Survey_Point"/>
      <mjl:commandLink
        name="SurveyPoint"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.SurveyPointView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Household"/>
      <mjl:commandLink
        name="Household"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.HouseholdView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Surveyed_Person"/>
      <mjl:commandLink
        name="SurveyedPersonView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.SurveyedPersonView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>  
    <dd>
      <mdss:localize var="component" key="ITNs"/>
      <mjl:commandLink
        name="ITNInstanceView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.ITNInstanceView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
  </dl>
  
  
  <!-- INTERVENTION PLANNING -->
  <dl>
    <dt><label><mdss:localize key="Configure_Application_Rate_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Nozzle"/>
      <mjl:commandLink
        name="NozzleView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.NozzleView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Insecticide_Nozzle"/>
      <mjl:commandLink
        name="InsecticideNozzleView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.InsecticideNozzleView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Area_Standards"/>
      <mjl:commandLink
        name="AreaStandardsView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.AreaStandardsView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>

    <dt><label><mdss:localize key="IRS_Team_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Spray_Team"/>
      <mjl:commandLink
        name="Universal"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.SprayTeamView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>

    <dt><label><mdss:localize key="Target_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Resource_Target"/>
      <mjl:commandLink
        name="ResourceTarget"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.ResourceTargetView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Geo_Target"/>
      <mjl:commandLink
        name="GeoTargetView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.GeoTargetView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>  
  </dl>
  
  
  <!-- INTERVENTION MONITORING -->
  <dl>
    <dt><label><mdss:localize key="IRS_CRUD_Management"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Operator_Spray"/>
      <mjl:commandLink
        name="OperatorSprayView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.OperatorSprayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Team_Spray"/>
      <mjl:commandLink
        name="TeamSprayView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.TeamSprayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>
    <dd>
      <mdss:localize var="component" key="Area_Spray"/>
      <mjl:commandLink
        name="ZoneSpray"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.ZoneSprayView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Household_Spray_Status"/>
      <mjl:commandLink
        name="HouseholdSprayStatusView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.HouseholdSprayStatusView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Operator_Spray_Status"/>
      <mjl:commandLink
        name="OperatorSprayStatusView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.OperatorSprayStatusView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Team_Spray_Status"/>
      <mjl:commandLink
        name="TeamSprayStatusView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.irs.TeamSprayStatusView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>    
  
    <dt><label><mdss:localize key="Manage_Aggregated_IPT"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Aggregated_IPT_Information"/>
      <mjl:commandLink
        name="AggregatedIPT"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.AggregatedIPTView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>  
    
    <dt><label><mdss:localize key="Manage_Individual_IPT"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Individual_IPT_Case"/>
      <mjl:commandLink
        name="IndividualIPTCase"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.IndividualIPTCaseView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>
    <dd>
      <mdss:localize var="component" key="Individual_IPT"/>
      <mjl:commandLink
        name="IndividualIPT"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.IndividualIPTView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    

    <dt><label><mdss:localize key="ITN_Data_Distribution"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Aggregated_ITN_Data_Distribution"/>
      <mjl:commandLink
        name="ITNData"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.ITNDataView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>
    <dd>
      <mdss:localize var="component" key="ITN_Community_Distribution"/>
      <mjl:commandLink
        name="ITNCommunityDistribution"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>
    <dd>
      <mdss:localize var="component" key="ITN_Facility_Distribution"/>
      <mjl:commandLink
        name="ITNDistribution"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.ITNDistributionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>

    <dt><label><mdss:localize key="Control_of_Immatures"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Larvacide"/>
      <mjl:commandLink
        name="Larvacide"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.Larvacide" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    
    <dd>
      <mdss:localize var="component" key="Larvacide_Instance"/>
      <mjl:commandLink
        name="Larvacide_Instance"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.LarvacideInstanceView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    

    <dt><label><mdss:localize key="Manage_Control_Intervention"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Control_intervention"/>
      <mjl:commandLink
        name="ControlInterventionView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.ControlInterventionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Individual_premises_Visit"/>
      <mjl:commandLink
        name="IndividualPremiseVisitView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.IndividualPremiseVisitView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Aggregated_Premises_Visit"/>
      <mjl:commandLink
        name="AggregatedPremiseVisitView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Person_Days_Used_for_Intervention"/>
      <mjl:commandLink
        name="PersonInterventionView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.PersonInterventionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
    <dd>
      <mdss:localize var="component" key="Insecticide_Use_For_Intervention"/>
      <mjl:commandLink
        name="InsecticideInterventionView"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.intervention.monitor.InsecticideInterventionView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>    
    </dd>
  </dl>


  <!-- STOCK CONTROL -->
  <dl>
    <dt><label><mdss:localize key="Manage_Stock"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Stock_Item"/>
      <mjl:commandLink
        name="Stock_Item"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.stock.StockItemView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    
    <dd>
      <mdss:localize var="component" key="Stock_Event"/>
      <mjl:commandLink
        name="Stock_Event"
        action="${action}">
        <mjl:property name="universal" value="dss.vector.solutions.stock.StockEventView" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    
  </dl>  
  
  <!-- Form admin -->
  <dl>
    <dt><label><mdss:localize key="Manage_Form_Admin"/></label></dt>
    <dd>
      <mdss:localize var="component" key="Form"/>
      <mjl:commandLink
        name="Form"
        action="${action}">
        <mjl:property name="universal" value="com.runwaysdk.system.metadata.MdWebForm" />
        <mjl:property name="component" value="${component}"/>
        <mjl:property name="actor" value="${actor}"/>        
        ${component}
      </mjl:commandLink>
    </dd>    
  </dl>  
  
  <dl>
    <dt><label><mdss:localize key="Manage_Forms"/></label></dt>
      <%
        List<String> forms = (List<String>) request.getAttribute("forms");
      
        for(String form : forms)
        {    
          out.write("<dd>" + form + "</dd>\n");
        }
      %>
  </dl>
