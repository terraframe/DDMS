<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@page import="com.terraframe.mojo.business.ClassQueryDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.terraframe.mojo.transport.attributes.AttributeDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.query.QueryController"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.ThematicLayerDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoDTO"%>
<%@page import="dss.vector.solutions.mo.SpecieDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoViewDTO"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.UninterestingSpecieGroupDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF"%>
<%@page import="com.terraframe.mojo.dataaccess.metadata.MdAttributeConcreteDAO"%>
<%@page import="com.terraframe.mojo.dataaccess.MdBusinessDAOIF"%>
<%@page import="com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoView"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.general.InsecticideDTO"%>
<%@page import="com.terraframe.mojo.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.irs.ActorSprayStatusDTO"%>
<%@page import="dss.vector.solutions.irs.ZoneSprayDTO"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayDTO"%>
<%@page import="dss.vector.solutions.irs.TeamSprayDTO"%>
<%@page import="dss.vector.solutions.irs.HouseholdSprayStatusDTO"%>
<%@page import="dss.vector.solutions.irs.TeamSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandDTO"%>
<%@page import="dss.vector.solutions.irs.TeamSprayView"%>
<%@page import="dss.vector.solutions.irs.HouseholdSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.ZoneSprayViewDTO"%>
<%@page import="dss.vector.solutions.irs.OperatorSprayViewDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandViewDTO"%>
<%@page import="dss.vector.solutions.irs.SprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="dss.vector.solutions.irs.ActorSprayStatusViewDTO"%>
<%@page import="dss.vector.solutions.irs.SprayStatusDTO"%>
<%@page import="dss.vector.solutions.irs.SprayDataDTO"%>

<%@page import="dss.vector.solutions.irs.ActorSprayDTO"%><c:set var="page_title" value="Query_IRS"  scope="request"/>
<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] sprayTypes = new String[]{InsecticideBrandViewDTO.CLASS,
    InsecticideBrandDTO.CLASS,
    SprayDataDTO.CLASS,
    SprayStatusDTO.CLASS,
    ZoneSprayDTO.CLASS,
    ActorSprayDTO.CLASS,
    OperatorSprayDTO.CLASS
    //TeamSprayStatusViewDTO.CLASS,
    //TeamSprayView.CLASS,ZoneSprayViewDTO.CLASS,
    //OperatorSprayStatusViewDTO.CLASS,OperatorSprayViewDTO.CLASS,
    //HouseholdSprayStatusViewDTO.CLASS,
    };
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, LayerViewDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};


    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(sprayTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayTeamDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{SprayStatusViewDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{ActorSprayStatusViewDTO.CLASS}))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(new String[]{TeamSprayStatusViewDTO.CLASS}))%>

<script type="text/javascript">
// Setting both values to false will select *all* univerals
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;


YAHOO.util.Event.onDOMReady(function(){

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var sprayMaps = {<%//=(String) request.getAttribute("sprayStatusMap")%>};

    var mapAttribs = function(attribName,index){
      var attrib = this.obj.attributeMap[attribName];
      var row = {};
      if(attrib){
        row.attributeName = attrib.attributeName;
        if(attrib.dtoType === 'AttributeReferenceDTO')
        {
          row.attributeName += '.displayLabel.currentValue';
        }
        if(attrib.dtoType === 'AttributeEnumerationDTO')
        {
          row.attributeName += '.displayLabel.currentValue';
        }
        row.key = attrib.attributeName + this.suffix;
        row.type = this.type;
        row.dtoType = attrib.dtoType;
        row.displayLabel = attrib.attributeMdDTO.displayLabel;
        if(this.dropDownMaps[attrib.attributeName]){
          row.dropDownMap = this.dropDownMaps[attrib.attributeName];
        }
      }else{
        row.attributeName = attribName;
        row.type = 'sqlcharacter';
        row.displayLabel = attribName;
        row.key = attribName;

      }
      return row;
    }


    var sprayStatus = new Mojo.$.dss.vector.solutions.irs.TeamSprayStatusView();
    var sprayData = new Mojo.$.dss.vector.solutions.irs.SprayData();
    var actorSpray = new Mojo.$.dss.vector.solutions.irs.ActorSpray();
    var insectcide = new Mojo.$.dss.vector.solutions.irs.InsecticideBrandView();


    var insectcideAttribs = ["brandName","activeIngredient","amount","weight","sachetsPerRefill"];
    var actorSprayAtribs = ["recived","used","refills","returned"];

    var Insecticide_Details = insectcideAttribs.map(mapAttribs, {obj:insectcide, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.InsecticideBrand'});
    Insecticide_Details = Insecticide_Details.concat(actorSprayAtribs.map(mapAttribs, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.ActorSpray'}));

    var abstractCalculations = {
        key:"resistance_result",
    	attributeName:"resistance_result",
      	type:"sqlcharacter",
      	displayLabel:"Resistance"
     };




    //var sprayStatus = new Mojo.$.dss.vector.solutions.irs.SprayStatusView();
    var sprayDataAttribs = ["sprayDate", "sprayMethod", "surfaceType"];
    var sprayStatusAttribs = ["households","structures","rooms","sprayedHouseholds","sprayedStructures","sprayedRooms","locked","refused","other"];


    var Spray_Details = [];
    Spray_Details = Spray_Details.concat(sprayDataAttribs.map(mapAttribs, {obj:sprayData, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.SprayData'}));
    Spray_Details = Spray_Details.concat(sprayStatusAttribs.map(mapAttribs, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.SprayStatus'}));


    var netAttribs = ["people","roomsWithBedNets","bedNets","prevSprayedHouseholds","prevSprayedStructures"];
    var HouseHold_Structure_Detail = netAttribs.map(mapAttribs, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.SprayStatus'});


     var Planed_Targets = [
                                  {
                                    displayLabel:"Spray Area Target",
                                    key:"spray_area_target_planed",
                                    type:"sqlcharacter",
                                    attributeName:"spray_area_target_planed",
                                  },
                                  {
                                    displayLabel:"Oprator Target",
                                    key:"oprator_target_planed",
                                    type:"sqlcharacter",
                                    attributeName:"oprator_target_planed",
                                  },
                                  {
                                    displayLabel:"Team Target",
                                    key:"team_target",
                                    type:"sqlcharacter",
                                    attributeName:"team_target_planed",
                                  }
                              ];

     var Actual_Targets = [
                                  {
                                    displayLabel:"Oprator Target",
                                    key:"oprator_target_actual",
                                    type:"sqlcharacter",
                                    attributeName:"test",
                                  },
                                  {
                                    displayLabel:"Team Target",
                                    key:"oprator_target_actual",
                                    type:"sqlcharacter",
                                    attributeName:"oprator_target_actual",
                                  }
                           ];

   /* Spray_Details = Spray_Details.concat([

                                   {
                                     displayLabel:"Unsprayed Strucures",
                                     key:"unsprayed_structures",
                                     type:"sqlinteger",
                                     attributeName:"unsprayed_structures",
                                   },
                                   {
                                     displayLabel:"Unsprayed Households",
                                     key:"unsprayed_households",
                                     type:"sqlinteger",
                                     attributeName:"unsprayed_households",
                                   },
                                   {
                                     displayLabel:"Unsprayed Rooms",
                                     key:"unsprayed_rooms",
                                     type:"sqlinteger",
                                     attributeName:"unsprayed_rooms",
                                   }
                                ]);*/

     var Coverege = [

                   /*  select += "actorspray."+ActorSpray.RECEIVED+",\n";
                     select += "actorspray."+ActorSpray.RETURNED+",\n";
                     select += "actorspray."+ActorSpray.USED+",\n";
                     select += "actorspray."+ActorSpray.REFILLS+",\n";*/

                                   {
                                     displayLabel:"Operational Coverage",
                                     key:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Planned Coverage",
                                     key:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Application Rate",
                                     key:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Application Ratio",
                                     key:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   }
                                ];

    var Spray_Team_Detail = [
                                     {
                                       displayLabel:"Spray Team",
                                       key:"sprayoperator_displaylabel",
                                        type:"sqlcharacter",
                                       attributeName:"sprayoperator_displaylabel",
                                     },
                                     {
                                       displayLabel:"Spray Team Week",
                                       key:"spray_team_week",
                                       type:"sqlcharacter",
                                       attributeName:"spray_team_week",
                                     },
                                     {
                                       displayLabel:"Operator",
                                       key:"sprayoperator_displaylabel",
                                       type:"sqlcharacter",
                                       attributeName:"sprayoperator_displaylabel",
                                     },
                                     {
                                       displayLabel:"Operator Spray Week",
                                       key:"operator_week",
                                       type:"sqlcharacter",
                                       attributeName:"operator_week",
                                     }
                             ];



    var selectableGroups = [
 	                         {title:"Planed_Targets", values:Planed_Targets, group:"spray", klass:Mojo.$.dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS},
 	                         //{title:"Actual_Targets", values:Actual_Targets, group:"spray", klass:Mojo.$.dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS},
                             {title:"Insecticide", values:Insecticide_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         {title:"Spray_Details", values:Spray_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         //{title:"Coverege", values:Coverege, group:"spray", klass:Mojo.$.dss.vector.solutions.entomology.assay.CollectionAssay.CLASS},
 	                         {title:"HouseHold_Structure_Detail", values:HouseHold_Structure_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         //{title:"Spray_Team_Detail", values:Spray_Team_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.entomology.assay.CollectionAssay.CLASS},
 	                     ];

    var query = new MDSS.QueryIRS(selectableGroups, queryList);
    query.render();

});

</script>

<div class="yui-skin-sam">

<div id="tabSet" class="yui-navset">
    <ul class="yui-nav">
        <li class="selected"><a href="#tab1"><em><fmt:message key="Query_Tab" /></em></a></li>
        <li><a href="#tab2"><em><fmt:message key="Map_Tab" /></em></a></li>
    </ul>
    <div class="yui-content">
        <div><div id="queryPanel"></div></div>
        <div><div id="mapPanel"></div></div>
    </div>
</div>

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>

<textarea id="debug_xml" cols="40" rows="40" style="width:1280px"> </textarea>


</div>

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>
<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>


<jsp:include page="../templates/footer.jsp"></jsp:include>