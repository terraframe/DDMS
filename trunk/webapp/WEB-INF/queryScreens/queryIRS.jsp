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

<%@page import="dss.vector.solutions.irs.ActorSprayDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SprayZoneDTO"%><c:set var="page_title" value="Query_IRS"  scope="request"/>
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
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    //var sprayMaps = {<%//=(String) request.getAttribute("sprayStatusMap")%>};

    var mapAttribs = function(attribName,index){
      var attrib = this.obj.attributeMap[attribName];
      var row = {};
      if(attrib){
        row.attributeName = attrib.attributeName;
        if(attrib.dtoType.contains('AttributeReferenceDTO'))
        {
          //row.attributeName += '.name';
        }
        if(attrib.dtoType.contains('AttributeEnumerationDTO'))
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
    var insectcide = new Mojo.$.dss.vector.solutions.irs.InsecticideBrandView();


    var insectcideAttribs = ["brandName","activeIngredient","amount","weight","sachetsPerRefill"];
    var actorSprayAtribs = ["refills","received","used","returned"];

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
                                                       }
                                                    ]);

     Insecticide_Details = Insecticide_Details.concat(actorSprayAtribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.ActorSpray'}));

    var Spray_Details = ([
                          {
                            displayLabel:"Aggregation Level",
                            key:"aggregation_level",
                            type:"sqlcharacter",
                            attributeName:"aggregation_level",
                            dropDownMap:{'1':'1','2':'2','3':'3'}
                          },
                         ]);


    //var sprayStatus = new Mojo.$.dss.vector.solutions.irs.SprayStatusView();
    var sprayDataAttribs = ["sprayDate", "sprayMethod", "surfaceType"];
    var sprayStatusAttribs = ["households","structures","rooms","sprayedHouseholds","sprayedStructures","sprayedRooms","locked","refused","other"];

    Spray_Details = Spray_Details.concat(sprayDataAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:sprayData, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.SprayData'}));
    Spray_Details = Spray_Details.concat(sprayStatusAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.SprayStatus'}));


    var netAttribs = ["people","roomsWithBedNets","bedNets","prevSprayedHouseholds","prevSprayedStructures"];
    var HouseHold_Structure_Detail = netAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.SprayStatus'});


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
                                  }
                           ];

   Spray_Details = Spray_Details.concat([

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
                                     key:"room_application_rate",
                                     type:"sqldouble",
                                     attributeName:"room_application_rate",
                                   },
                                   {
                                     displayLabel:"Unit Application Rate (mg/m²)",
                                     key:"room_application_rate_mg",
                                     type:"sqldouble",
                                     attributeName:"room_application_rate_mg",
                                   },
                                   {
                                     displayLabel:"Unit Operational Coverage",
                                     key:"room_operational_coverage",
                                     type:"sqldouble",
                                     attributeName:"room_operational_coverage",
                                   },
                                   {
                                     displayLabel:"Unit Application Ratio",
                                     key:"room_application_ratio",
                                     type:"sqldouble",
                                     attributeName:"room_application_ratio",
                                   },
                                   /*
                                   {
                                     displayLabel:"Unit Application Rate (g/m²)",
                                     key:"structure_application_rate",
                                     type:"sqldouble",
                                     attributeName:"structure_application_rate",
                                   },
                                   {
                                     displayLabel:"Unit Application Rate (mg/m²)",
                                     key:"structure_application_rate",
                                     type:"sqldouble",
                                     attributeName:"structure_application_rate_mg",
                                   },
                                   {
                                     displayLabel:"Unit Application Ratio",
                                     key:"structure_application_ratio",
                                     type:"sqldouble",
                                     attributeName:"structure_application_ratio",
                                   },
                                   {
                                     displayLabel:"Unit Operational Coverage",
                                     key:"structure_operational_coverage",
                                     type:"sqldouble",
                                     attributeName:"structure_operational_coverage",
                                   },
                                   {
                                     displayLabel:"Unit Application Rate (g/m²)",
                                     key:"household_application_rate",
                                     type:"sqldouble",
                                     attributeName:"household_application_rate",
                                   },
                                   {
                                     displayLabel:"Unit Application Rate (mg/m²)",
                                     key:"household_application_rate",
                                     type:"sqldouble",
                                     attributeName:"household_application_rate_mg",
                                   },
                                   {
                                     displayLabel:"Unit Operational Coverage",
                                     key:"household_operational_coverage",
                                     type:"sqldouble",
                                     attributeName:"household_operational_coverage",
                                   },
                                   {
                                     displayLabel:"Unit Application Ratio",
                                     key:"household_application_ratio",
                                     type:"sqldouble",
                                     attributeName:"household_application_ratio",
                                   },*/

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
 	                         {title:"Planned_Targets", values:Planed_Targets, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         {title:"Actual_Targets", values:Actual_Targets, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
                             {title:"Insecticide", values:Insecticide_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         {title:"Spray_Details", values:Spray_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         {title:"Calculations", values:Coverage, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         {title:"HouseHold_Structure_Detail", values:HouseHold_Structure_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                         {title:"Spray_Team_Detail", values:Spray_Team_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.SprayStatus.CLASS},
 	                     ];

    var query = new MDSS.QueryIRS(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>