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
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.general.InsecticideDTO"%>
<%@page import="com.terraframe.mojo.business.BusinessDTO"%>
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
<%@page import="dss.vector.solutions.irs.SprayTeamDTO"%>
<%@page import="dss.vector.solutions.irs.SprayDataDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SprayZoneDTO"%>


<%@page import="dss.vector.solutions.irs.OperatorSprayStatusDTO"%>
<%@page import="dss.vector.solutions.irs.TeamSprayStatusDTO"%>
<%@page import="dss.vector.solutions.irs.TeamSprayViewDTO"%><c:set var="page_title" value="Query_IRS"  scope="request"/>
<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] sprayTypes = new String[]{
    InsecticideBrandViewDTO.CLASS,
    InsecticideBrandDTO.CLASS,
    HouseholdSprayStatusDTO.CLASS,
    OperatorSprayStatusDTO.CLASS,
    TeamSprayStatusDTO.CLASS,
    ZoneSprayDTO.CLASS,
    TeamSprayDTO.CLASS,
    OperatorSprayDTO.CLASS,
    HouseholdSprayStatusViewDTO.CLASS,
    OperatorSprayStatusViewDTO.CLASS,
    TeamSprayStatusViewDTO.CLASS,
    ZoneSprayViewDTO.CLASS,
    TeamSprayViewDTO.CLASS,
    OperatorSprayViewDTO.CLASS
    };
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(sprayTypes));
    loadables.addAll(Arrays.asList(queryTypes));

%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('messageFrame', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var insectcide = new Mojo.$.dss.vector.solutions.irs.InsecticideBrand();

    var insectcideAttribs = ["brandName","activeIngredient","amount","weight","sachetsPerRefill"];

    var Insecticide_Details = insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.InsecticideBrand'});
    Insecticide_Details = Insecticide_Details.concat([

                                                      {
                                                         displayLabel:"Nozzle",
                                                         key:"nozzle_defaultLocale",
                                                         type:"sqlcharacter",
                                                         attributeName:"nozzle_defaultLocale",
                                                       },
                                                       {
                                                         displayLabel:"Nozzle Ratio",
                                                         key:"nozzle_ratio",
                                                         type:"sqldouble",
                                                         attributeName:"nozzle_ratio",
                                                       },
                                                       {
                                                         displayLabel:"Active Ingredient per Can (g)",
                                                         key:"active_ingredient_per_can",
                                                         type:"sqldouble",
                                                         attributeName:"active_ingredient_per_can",
                                                       },
                                                       {
                                                         displayLabel:"Recommended Application Rate (g/m²)",
                                                         key:"standard_application_rate",
                                                         type:"sqldouble",
                                                         attributeName:"standard_application_rate",
                                                       },
                                                       {
                                                         displayLabel:"Recommended Application Rate (mg/m²)",
                                                         key:"standard_application_rate_mg",
                                                         type:"sqldouble",
                                                         attributeName:"standard_application_rate_mg",
                                                       },
                                                       {
                                                         displayLabel:"Sprayable Units per Can",
                                                         key:"units_per_can",
                                                         type:"sqldouble",
                                                         attributeName:"units_per_can",
                                                       },
                                                    ]);

    var abstractSpray = new Mojo.$.dss.vector.solutions.irs.OperatorSpray();
    var abstractSprayAtribs = ["geoEntity","sprayMethod","surfaceType", "sprayDate"];
    var operatorSprayMap = {<%=(String) request.getAttribute("operatorSprayMap")%>};

   
    var Spray_Details = ([
                          {
                            displayLabel:"Aggregation Level",
                            key:"aggregation_level",
                            type:"sqlcharacter",
                            attributeName:"aggregation_level",
                            dropDownMap:{'1':'1','2':'2','3':'3'}
                          },
                         ]);


    var sprayStatus = new Mojo.$.dss.vector.solutions.irs.HouseholdSprayStatus();
    var sprayStatusAttribs = ["households","structures","rooms","sprayedHouseholds","sprayedStructures","sprayedRooms","locked","refused","other"];

    Spray_Details = Spray_Details.concat(abstractSprayAtribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:abstractSpray, suffix:'_spray', dropDownMaps:operatorSprayMap, type:'dss.vector.solutions.irs.AbstractSpray'}));
    Spray_Details = Spray_Details.concat(sprayStatusAttribs.map(MDSS.QueryBaseNew.mapInts, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.AbstractSpray'}));


    var netAttribs = ["people","roomsWithBedNets","bedNets","prevSprayedHouseholds","prevSprayedStructures"];
    var HouseHold_Structure_Detail = netAttribs.map(MDSS.QueryBaseNew.mapInts, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.AbstractSpray'});

   var insectcideUsageAttribs = ["received","used","refills","returned"];
   Insecticide_Details = Insecticide_Details.concat(insectcideUsageAttribs.map(MDSS.QueryBaseNew.mapInts, {obj:abstractSpray, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.AbstractSpray'}));

     var Planed_Targets = [

                                  {
                                    displayLabel:"Oprator Target (P)",
                                    key:"planed_operator_target",
                                    type:"sqlinteger",
                                    attributeName:"planed_operator_target",
                                  },
                                  {
                                    displayLabel:"Team Target (P)",
                                    key:"planed_team_target",
                                    type:"sqlinteger",
                                    attributeName:"planed_team_target",
                                  },
                                  {
                                    displayLabel:"Spray Area Target (P)",
                                    key:"planed_area_target",
                                    type:"sqlinteger",
                                    attributeName:"planed_area_target",
                                  },
                                  /* {
                                  displayLabel:"Planned Coverage",
                                  key:"planned_coverage",
                                  type:"sqldouble",
                                  attributeName:"planned_coverage",
                                },*/
                              ];

     var Actual_Targets = [
                                  {
                                    displayLabel:"Operator Target (A)",
                                    key:"operator_target",
                                    type:"sqlinteger",
                                    attributeName:"operator_target",
                                  },
                                  {
                                    displayLabel:"Team Target (A)",
                                    key:"team_target",
                                    type:"sqlinteger",
                                    attributeName:"team_target",
                                  },
                                  {
                                    displayLabel:"Zone Target (A)",
                                    key:"zone_target",
                                    type:"sqlinteger",
                                    attributeName:"zone_target",
                                  },
                                  {
                                    displayLabel:"Target Unit",
                                    key:"targetUnit_displayLabel",
                                    type:"sqlcharacter",
                                    attributeName:"targetUnit_displayLabel",
                                  }
                           ];

   Spray_Details = Spray_Details.concat([
                                  {
                                    displayLabel:"Units Sprayed #",
                                    key:"sprayedunits",
                                    type:"sqlinteger",
                                    attributeName:"sprayedunits",
                                  },   
                                  {
                                     displayLabel:"Unsprayed Strucures",
                                     key:"structure_unsprayed",
                                     type:"sqlinteger",
                                     attributeName:"structure_unsprayed",
                                   },
                                   {
                                     displayLabel:"Unsprayed Households",
                                     key:"household_unsprayed",
                                     type:"sqlinteger",
                                     attributeName:"household_unsprayed",
                                   },
                                   {
                                     displayLabel:"Unsprayed Rooms",
                                     key:"room_unsprayed",
                                     type:"sqlinteger",
                                     attributeName:"room_unsprayed",
                                   },
                                   {
                                      displayLabel:"Unsprayed Units",
                                      key:"unit_unsprayed",
                                      type:"sqlinteger",
                                      attributeName:"unit_unsprayed",
                                   }
                                ]);
  





   HouseHold_Structure_Detail = HouseHold_Structure_Detail.concat([
                                         {
                                           displayLabel:"Household ID",
                                           key:"household_id",
                                           type:"sqlcharacter",
                                           attributeName:"household_id",
                                         },
                                         {
                                           displayLabel:"Structure ID",
                                           key:"structure_id",
                                           type:"sqlcharacter",
                                           attributeName:"structure_id",
                                         },

                                       ]);

    var Coverage = [

                                   {
                                     displayLabel:"Unit Application Rate (g/m²)",
                                     key:"unit_application_rate",
                                     type:"sqldouble",
                                     attributeName:"unit_application_rate",
                                     isAggregate:true
                                   },
                                   {
                                     displayLabel:"Unit Application Rate (mg/m²)",
                                     key:"unit_application_rate_mg",
                                     type:"sqldouble",
                                     attributeName:"unit_application_rate_mg",
                                     isAggregate:true
                                   },
                                   {
                                     displayLabel:"Unit Application Ratio",
                                     key:"unit_application_ratio",
                                     type:"sqldouble",
                                     attributeName:"unit_application_ratio",
                                     isAggregate:true
                                   },
                                   {
                                     displayLabel:"Unit Operational Coverage",
                                     key:"unit_operational_coverage",
                                     type:"sqldouble",
                                     attributeName:"unit_operational_coverage",
                                     isAggregate:true
                                   },                      
                                   {
                                     displayLabel:"Calculated # Rooms Sprayed",
                                     key:"calculated_rooms_sprayed",
                                     type:"sqldouble",
                                     attributeName:"calculated_rooms_sprayed",
                                     isAggregate:true
                                   },
                                   {
                                     displayLabel:"Calculated # Structures Sprayed",
                                     key:"calculated_structures_sprayed",
                                     type:"sqldouble",
                                     attributeName:"calculated_structures_sprayed",
                                     isAggregate:true
                                   },
                                   {
                                     displayLabel:"Calculated # Households Sprayed",
                                     key:"calculated_households_sprayed",
                                     type:"sqldouble",
                                     attributeName:"calculated_households_sprayed",
                                     isAggregate:true
                                   },
                                   
                                  
                                ];

    var Spray_Team_Detail = [
                                {
                                  displayLabel:"Spray Operator",
                                  key:"sprayoperator_defaultLocale",
                                  type:"sqlcharacter",
                                  attributeName:"sprayoperator_defaultLocale",
                                },
                                {
                                  displayLabel:"Operator Week",
                                  key:"operator_week",
                                  type:"sqlinteger",
                                  attributeName:"operator_week",
                                },
                                {
                                  displayLabel:"Spray Team Leader",
                                  key:"sprayleader_defaultLocale",
                                  type:"sqlcharacter",
                                  attributeName:"sprayleader_defaultLocale",
                                },
                                {
                                  displayLabel:"Spray Team",
                                  key:"sprayteam_defaultLocale",
                                  type:"sqlcharacter",
                                  attributeName:"sprayteam_defaultLocale",
                                },
                                {
                                  displayLabel:"Team Week",
                                  key:"team_week",
                                  type:"sqlinteger",
                                  attributeName:"team_week",
                                },
                                {
                                  displayLabel:"Zone Supervisor",
                                  key:"zone_supervisor",
                                  type:"sqlcharacter",
                                  attributeName:"zone_supervisor",
                                },
                                {
                                  displayLabel:"Zone Week",
                                  key:"zone_week",
                                  type:"sqlinteger",
                                  attributeName:"zone_week",
                                },



                             ];


    var selectableGroups = [
 	                         {title:"Planned_Targets", values:Planed_Targets, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"Actual_Targets", values:Actual_Targets, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
                             {title:"Insecticide", values:Insecticide_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"Spray_Details", values:Spray_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"Calculations", values:Coverage, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"HouseHold_Structure_Detail", values:HouseHold_Structure_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"Spray_Team_Detail", values:Spray_Team_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                     ];

    var query = new MDSS.QueryIRS(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>