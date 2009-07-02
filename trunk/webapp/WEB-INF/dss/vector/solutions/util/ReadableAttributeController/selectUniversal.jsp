<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>

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
      <span id="dss.vector.solutions.Person" class="roleLink"><f:message key="Person"/></span>
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
      <span id="dss.vector.solutions.entomology.MosquitoCollection" class="roleLink"><f:message key="Mosquito Collection"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.entomology.MorphologicalSpecieGroupView" class="roleLink"><f:message key="Morphological Specie Group"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Mosquito_Collection_Point_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.MosquitoCollectionPoint" class="roleLink"><f:message key="Mosquito Collection Point"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.entomology.MorphologicalSpecieGroupView" class="roleLink"><f:message key="Morphological Specie Group"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Species_Identification_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.MosquitoView" class="roleLink"><f:message key="Mosquito"/></span>
    </dd>
    <dd>        
      <span id="dss.vector.solutions.entomology.UninterestingSpecieGroupView" class="roleLink"><f:message key="Uninteresting Specie Group"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Adult_Diagnostic_Assay_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay" class="roleLink"><f:message key="Adult Diagnostic Assay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Larvae_Diagnostic_Assay_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay" class="roleLink"><f:message key="Larvae Diagnostic Assay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>    
  </dl>
  <dl>
    <dt><label><f:message key="Time_Response_Test_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.KnockDownAssay" class="roleLink"><f:message key="Time Response Test"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Efficacy_Bioassay_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.entomology.assay.EfficacyAssayView" class="roleLink"><f:message key="Efficacy Bioassay"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.general.Insecticide" class="roleLink"><f:message key="Insecticide"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Aggregated_Cases_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.surveillance.InfiantCaseView" class="roleLink"><f:message key="Aggregated Cases 1"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.surveillance.ChildCaseView" class="roleLink"><f:message key="Aggregated Cases 2"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.surveillance.TeenCaseView" class="roleLink"><f:message key="Aggregated Cases 3"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.surveillance.AdultCaseView" class="roleLink"><f:message key="Aggregated Cases 4"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.surveillance.ElderCaseView" class="roleLink"><f:message key="Aggregated Cases 5"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.surveillance.AncientCaseView" class="roleLink"><f:message key="Aggregated Cases 6"/></span>
    </dd>    
    <dd>
      <span id="dss.vector.solutions.general.EpiDate" class="roleLink"><f:message key="Aggregated Cases 6"/></span>
    </dd>    
    
  </dl>
  <dl>
    <dt><label><f:message key="Indicator_Survey_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.SurveyPointView" class="roleLink"><f:message key="Survey Point"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.Household" class="roleLink"><f:message key="House Hold"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.PersonView" class="roleLink"><f:message key="Surveyed Person"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.WallView" class="roleLink"><f:message key="Wall"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.intervention.monitor.RoofView" class="roleLink"><f:message key="Surveyed Person"/></span>
    </dd>

  </dl>
  <dl>
    <dt><label><f:message key="Configure_Application_Rate_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.irs.InsecticideBrandView" class="roleLink"><f:message key="Insecticide Brand"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.NozzleView" class="roleLink"><f:message key="Nozzle"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.InsecticideNozzleView" class="roleLink"><f:message key="InsecticideNozzle"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.AreaStandardsView" class="roleLink"><f:message key="Area Standards"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="Target_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.irs.ResourceTargetView" class="roleLink"><f:message key="Resource Target"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.GeoTargetView" class="roleLink"><f:message key="Geo Target"/></span>
    </dd>
  </dl>
  <dl>
    <dt><label><f:message key="IRS_CRUD_Management"/></label></dt>
    <dd>
      <span id="dss.vector.solutions.irs.OperatorSprayView" class="roleLink"><f:message key="Operator Spray"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.TeamSprayView" class="roleLink"><f:message key="Team Spray"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.ZoneSprayView" class="roleLink"><f:message key="Area Spray"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.HouseholdSprayStatusView" class="roleLink"><f:message key="Household Spray Status"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.OperatorSprayStatusView" class="roleLink"><f:message key="Operator Spray Status"/></span>
    </dd>
    <dd>
      <span id="dss.vector.solutions.irs.TeamSprayStatusView" class="roleLink"><f:message key="Team Spray Status"/></span>
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
