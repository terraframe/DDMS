<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@page import="dss.vector.solutions.MDSSRoleInfo"%>

<c:set var="page_title" value="Select_Class" scope="request" />

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){

    YAHOO.util.Event.on(document.body, 'click', function(e){
      var target = e.target;
      if(target.nodeName === 'SPAN' && YAHOO.util.Dom.hasClass(target, 'roleLink'))
      {
        var select = document.getElementById('roleSelectList');
        var actor = select.options[select.selectedIndex].value;

        var newLocation = 'dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo?'
        newLocation += 'actor='+actor+'&universal='+target.id;

        window.location = newLocation;
      }
    });
  });
})();
</script>

<c:set var="action" value="dss.vector.solutions.util.ReadableAttributeController.getAttributes.mojo" scope="page"/>

<select id="roleSelectList">
  <c:forEach var="role" items="${actorOptions}">
  <option value="${role.roleName}">${role.displayLabel}</option>
  </c:forEach>
</select>

  <dl>
    <dt><label><f:message key="Person_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.PersonView" class="roleLink"><f:message key="Person"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="IRS_Team_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.irs.SprayTeam" class="roleLink"><f:message key="Spray_Team"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.SprayLeader" class="roleLink"><f:message key="Spray_Leader"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.SprayOperator" class="roleLink"><f:message key="Spray_Operator"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Mosquito_Collection_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.SearchMosquitoCollectionView" class="roleLink"><f:message key="Mosquito_Collection"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.entomology.SubCollectionView" class="roleLink"><f:message key="Sub_Collection"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.entomology.InfectionAssayView" class="roleLink"><f:message key="Infection_Assay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.entomology.PooledInfectionAssayView" class="roleLink"><f:message key="Pooled_Infection_Assay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.entomology.BiochemicalAssayView" class="roleLink"><f:message key="Biochemical_Assay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.entomology.MolecularAssayView" class="roleLink"><f:message key="Molecular_Assay"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Adult_Diagnostic_Assay_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay" class="roleLink"><f:message key="Adult_Diagnostic_Assay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Larvae_Diagnostic_Assay_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay" class="roleLink"><f:message key="Larvae_Diagnostic_Assay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Time_Response_Test_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.KnockDownAssay" class="roleLink"><f:message key="Time_Response_Test"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Efficacy_Bioassay_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.EfficacyAssayView" class="roleLink"><f:message key="Efficacy_Bioassay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Aggregated_Cases_Management"/></label></dt>
    <c:forEach items="${ageGroups}" varStatus="status" var="ageGroup">
      <dd>
        <span id="dss.vector.solutions.surveillance.${ageGroup.mdView.typeName}" class="roleLink"><f:message key="Age_Group"/> <c:out value="${ageGroup.displayLabel}"/> </span>
      </dd>    
    </c:forEach>
    <dd>
      <span id="dss.vector.solutions.general.EpiDate" class="roleLink"><f:message key="Epi_Date"/></span>
    </dd>    
    
  </dl>
  <dl>
    <dt><label><f:message key="Indicator_Survey_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.SurveyPointView" class="roleLink"><f:message key="Survey_Point"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.HouseholdView" class="roleLink"><f:message key="Household"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.ITNInstanceView" class="roleLink"><f:message key="ITNs"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.SurveyedPersonView" class="roleLink"><f:message key="Surveyed_Person"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Configure_Application_Rate_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.irs.InsecticideBrandView" class="roleLink"><f:message key="Insecticide_Brand"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.NozzleView" class="roleLink"><f:message key="Nozzle"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.InsecticideNozzleView" class="roleLink"><f:message key="Insecticide_Nozzle"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.AreaStandardsView" class="roleLink"><f:message key="Area_Standards"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Target_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.irs.ResourceTargetView" class="roleLink"><f:message key="Resource_Target"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.GeoTargetView" class="roleLink"><f:message key="Geo_Target"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="IRS_CRUD_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.irs.OperatorSprayView" class="roleLink"><f:message key="Operator_Spray"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.TeamSprayView" class="roleLink"><f:message key="Team_Spray"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.ZoneSprayView" class="roleLink"><f:message key="Area_Spray"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.HouseholdSprayStatusView" class="roleLink"><f:message key="Household_Spray_Status"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.OperatorSprayStatusView" class="roleLink"><f:message key="Operator_Spray_Status"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.TeamSprayStatusView" class="roleLink"><f:message key="Team_Spray_Status"/></span>
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
  <dl>
    <dt><label><f:message key="ITN_Community_Distribution"/></label></dt>
    <dd>
      <mjl:commandLink
        name="ITNCommunityDistribution"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.ITNCommunityDistributionView">
        <f:message key="ITN_Community_Distribution"/>
      </mjl:commandLink>
    </dd>
    
  </dl>
  <dl>
    <dt><label><f:message key="Individual_IPT"/></label></dt>
    <dd>
      <mjl:commandLink
        name="IndividualIPTCase"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.IndividualIPTCaseView">
        <f:message key="Individual_IPT_Case"/>
      </mjl:commandLink>
      <mjl:commandLink
        name="IndividualIPT"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.intervention.monitor.IndividualIPTView">
        <f:message key="Individual_IPT"/>
      </mjl:commandLink>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Population_Data"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Population_Data"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.PopulationDataView">
        <f:message key="Population_Data"/>
      </mjl:commandLink>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Threshold_Data"/></label></dt>
    <dd>
      <mjl:commandLink
        name="Threshold_Data"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.ThresholdDataView">
        <f:message key="Threshold_Data"/>
      </mjl:commandLink>
    </dd>    
    <dd>
      <mjl:commandLink
        name="Threshold_Calculation"
        action="${action}?actor=${actor}&universal=dss.vector.solutions.general.ThresholdCalculationTypeView">
        <f:message key="Threshold_Calculation"/>
      </mjl:commandLink>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Stock_Item"/></label></dt>
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
  <dl>
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
  </dl>
  <dl>
    <dt><label><f:message key="Individual_Cases"/></label></dt>
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
  </dl>

