<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>

<c:set var="action" value="dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo" scope="page"/>

  <dl>
    <dt><label><f:message key="Person_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Person"
        display="Person"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.Person"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="IRS_Team_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="SprayTeam"
        display="Spray Team"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.SprayTeam"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="SprayLeader"
        display="Spray Leader"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.SprayLeader"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="SprayOperator"
        display="Spray Operator"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.SprayOperator"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Mosquito_Collection_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="MosquitoCollection"
        display="Mosquito Collection"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.MosquitoCollection"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="MorphologicalSpecieGroup"
        display="Morphological Specie Group"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.MorphologicalSpecieGroupView"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Mosquito_Collection_Point_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="MosquitoCollection"
        display="Mosquito Collection Point"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.MosquitoCollectionPointView"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Species_Identification_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="MosquitoView"
        display="Mosquito"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.MosquitoView"/>
    </dd>
    <dd>        
      <mjl:commandLink
        name="UninterestingSpecieGroupView"
        display="Uninteresting Specie Group"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.UninterestingSpecieGroupView"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Adult_Diagnostic_Assay_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="AdultDiagnosticAssay"
        display="Adult Diagnostic Assay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Insecticide"
        display="Insecticide"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.Insecticide"/>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Larvae_Diagnostic_Assay_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="LarvaeDiagnosticAssay"
        display="Larvae Diagnostic Assay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Insecticide"
        display="Insecticide"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.Insecticide"/>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Time_Response_Test_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="TimeResponseTest"
        display="Time Response Test"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.KnockDownAssay"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Insecticide"
        display="Insecticide"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.Insecticide"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Efficacy_Bioassay_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="EfficacyAssay"
        display="Efficacy Bioassay"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.entomology.assay.EfficacyAssayView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Insecticide"
        display="Insecticide"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.Insecticide"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Aggregated_Cases_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="InfiantCaseView"
        display="Aggregated Cases 1"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.InfiantCaseView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="ChildCaseView"
        display="Aggregated Cases 2"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.ChildCaseView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="TeenCaseView"
        display="Aggregated Cases 3"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.TeenCaseView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="AdultCaseView"
        display="Aggregated Cases 4"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.AdultCaseView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="ElderCaseView"
        display="Aggregated Cases 5"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.ElderCaseView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="AncientCaseView"
        display="Aggregated Cases 6"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.surveillance.AncientCaseView"/>
    </dd>    
    <dd>
      <mjl:commandLink
        name="AncientCaseView"
        display="Aggregated Cases 6"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.EpiDate"/>
    </dd>    
    
  </dl>
  <dl>
    <dt><label><f:message key="Indicator_Survey_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="SurveyPoint"
        display="Survey Point"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.SurveyPointView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Household"
        display="House Hold"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.Household"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Person"
        display="Surveyed Person"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.PersonView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Wall"
        display="Wall"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.WallView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Roof"
        display="Surveyed Person"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.RoofView"/>
    </dd>

  </dl>
  <dl>
    <dt><label><f:message key="Configure_Application_Rate_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="InsecticideBrand"
        display="Insecticide Brand"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.InsecticideBrandView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Nozzle"
        display="Nozzle"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.NozzleView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="InsecticideNozzle"
        display="InsecticideNozzle"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.InsecticideNozzleView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="Area Standards"
        display="Area Standards"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.AreaStandardsView"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Target_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="ResourceTarget"
        display="Resource Target"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.ResourceTargetView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="GeoTarget"
        display="Geo Target"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.GeoTargetView"/>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="IRS_CRUD_Management"/></label></dt>
    <dd>
      <mjl:commandLink
        name="OperatorSpray"
        display="Operator Spray"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.OperatorSprayView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="TeamSpray"
        display="Team Spray"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.TeamSprayView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="ZoneSpray"
        display="Area Spray"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.ZoneSprayView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="HouseholdSprayStatus"
        display="Household Spray Status"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.HouseholdSprayStatusView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="OperatorSprayStatus"
        display="Operator Spray Status"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.OperatorSprayStatusView"/>
    </dd>
    <dd>
      <mjl:commandLink
        name="TeamSprayStatus"
        display="Team Spray Status"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.irs.TeamSprayStatusView"/>
    </dd>
    
  </dl>
  
  <dl>
    <dt><label><f:message key="Aggregated_IPT_Information"/></label></dt>
    <dd>
      <mjl:commandLink
        name="AggregatedIPT"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.AggregatedIPTView">
        <f:message key="Aggregated_IPT_Information"/>
      </mjl:commandLink>
    </dd>
    
  </dl>

  <dl>
    <dt><label><f:message key="ITN_Data_Distribution"/></label></dt>
    <dd>
      <mjl:commandLink
        name="ITNData"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.ITNDataView">
        <f:message key="ITN_Data_Distribution"/>
      </mjl:commandLink>
    </dd>
    
  </dl>
  