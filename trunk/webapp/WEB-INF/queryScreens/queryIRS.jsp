<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@page import="com.runwaysdk.business.ClassQueryDTO"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.runwaysdk.transport.attributes.AttributeDTO"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
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
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.general.InsecticideDTO"%>
<%@page import="com.runwaysdk.business.BusinessDTO"%>
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

<script type="text/javascript"><!--

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

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var targetManagementColumns = [

                          {
                            key:"operator_actual_target",
                            //key:"operator_target",
                            type:"sqlinteger",
                            attributeName:"operator_actual_target",
                            isAggregate:true
                          },
                          {
                            key:"team_actual_target",
                            //key:"team_target",
                            type:"sqlinteger",
                            attributeName:"team_actual_target",
                            isAggregate:true
                          },
                          {
                            key:"operator_planned_target",
                            //key:"planed_operator_target",
                            type:"sqlinteger",
                            attributeName:"operator_planned_target",
                            isAggregate:true
                          },
                          {
                            key:"team_planned_target",
                            //key:"planed_team_target",
                            type:"sqlinteger",
                            attributeName:"team_planned_target",
                            isAggregate:true
                          },
                          {
                            key:"area_planned_target",
                            //key:"planed_area_target",
                            type:"sqlinteger",
                            attributeName:"area_planned_target",
                            isAggregate:true
                          },
                          /* OLD
                          {
                            key:"planned_coverage",
                            type:"sqldouble",
                            attributeName:"planned_coverage",
                            isAggregate:true
                          },
                          */
                          { // NEW
                            key:"operator_planned_coverage",
                            type:"sqldouble",
                            attributeName:"operator_planned_coverage",
                            isAggregate:true
                          },
                          { // NEW
                            key:"team_planned_coverage",
                            type:"sqldouble",
                            attributeName:"team_planned_coverage",
                            isAggregate:true
                          },
                          { // NEW
                            key:"area_planned_coverage",
                            type:"sqldouble",
                            attributeName:"area_planned_coverage",
                            isAggregate:true
                          },
                          { // NEW
                            key:"operator_target_divergence",
                            type:"sqldouble",
                            attributeName:"operator_target_divergence",
                            isAggregate:true
                          },
                          { // NEW
                            key:"team_target_divergence",
                            type:"sqldouble",
                            attributeName:"team_target_divergence",
                            isAggregate:true
                          },
                          { // NEW
                            key:"operator_targeted_coverage",
                            type:"sqldouble",
                            attributeName:"operator_targeted_coverage",
                            isAggregate:true
                          },
                          {
                            // NEW
                            key:"team_targeted_coverage",
                            type:"sqldouble",
                            attributeName:"team_targeted_coverage",
                            isAggregate:true
                          }
                          
                          /* OLD
                          {
                            
                            key:"zone_target",
                            type:"sqlinteger",
                            attributeName:"zone_target",
                          },
                          */
                          /* OLD
                          {
                            
                            key:"targetUnit_displayLabel",
                            type:"sqlcharacter",
                            attributeName:"targetUnit_displayLabel",
                          },
                          */
                   ];
    

    var insectcide = new Mojo.$.dss.vector.solutions.irs.InsecticideBrand();

    var insectcideAttribs = [
                             "productName",
                             "activeIngredient",
                             "concentrationQuantifier",
                             "concentrationQualifier",
                             "insecticideUse",
                             "useDetail",
                             "unitsPerApplication",
                             "unitQuantifier",
                             "unitQualifier",
                             ];
    <%
    Halp.setReadableAttributes(request, "insectcideAttribs", InsecticideBrandViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("insectcideAttribs") %>);
    insectcideAttribs = Mojo.Iter.filter(insectcideAttribs, function(attrib){
      return this.contains(attrib);
    }, available);    

    var insecticideBrandMap = {<%=(String) request.getAttribute("insecticideBrandMap")%>};
    var Insecticide_Details = insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_spray', dropDownMaps:insecticideBrandMap, type:'dss.vector.solutions.irs.InsecticideBrand'});
    Insecticide_Details = Insecticide_Details.concat([

                                                      {
                                                         
                                                         key:"nozzle_defaultLocale",
                                                         type:"sqlcharacter",
                                                         attributeName:"nozzle_defaultLocale",
                                                       },
                                                       {
                                                         
                                                         key:"nozzle_ratio",
                                                         type:"sqldouble",
                                                         attributeName:"nozzle_ratio",
                                                       },
                                                       {
                                                         
                                                         key:"active_ingredient_per_can",
                                                         type:"sqldouble",
                                                         attributeName:"active_ingredient_per_can",
                                                       },
                                                       {
                                                         
                                                         key:"standard_application_rate",
                                                         type:"sqldouble",
                                                         attributeName:"standard_application_rate",
                                                       },
                                                       {
                                                         
                                                         key:"standard_application_rate_mg",
                                                         type:"sqldouble",
                                                         attributeName:"standard_application_rate_mg",
                                                       },
                                                       {
                                                         
                                                         key:"units_per_can",
                                                         type:"sqldouble",
                                                         attributeName:"units_per_can",
                                                       },
                                                       
                                                    ]);
    var abstractSpray = new Mojo.$.dss.vector.solutions.irs.OperatorSpray();

    // OperatorSpray, OperatorSprayStatus, TeamSprayStatus
    var insectcideUsageAttribs = ["received","used","refills","returned"];
    <%
      Halp.setReadableAttributes(request, "insectcideUsageAttribs_os", OperatorSprayViewDTO.CLASS, requestIF);
      Halp.setReadableAttributes(request, "insectcideUsageAttribs_oss", OperatorSprayStatusViewDTO.CLASS, requestIF);
      Halp.setReadableAttributes(request, "insectcideUsageAttribs_ts", TeamSprayStatusDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("insectcideUsageAttribs_os") %>);
    available.addAll(<%= request.getAttribute("insectcideUsageAttribs_oss") %>);
    available.addAll(<%= request.getAttribute("insectcideUsageAttribs_ts") %>);
    insectcideUsageAttribs = Mojo.Iter.filter(insectcideUsageAttribs, function(attrib){
      return this.contains(attrib);
    }, available);  

    Insecticide_Details = Insecticide_Details.concat(insectcideUsageAttribs.map(MDSS.QueryBaseNew.mapInts, {obj:abstractSpray, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.AbstractSpray'}));
    
    
    // OperatorSpray, TeamSpray, ZoneSpray
    var abstractSprayAtribs = ["geoEntity","sprayDate","sprayMethod","surfaceType"];
    <%
      Halp.setReadableAttributes(request, "abstractSprayAtribs_os", OperatorSprayViewDTO.CLASS, requestIF);
      Halp.setReadableAttributes(request, "abstractSprayAtribs_ts", TeamSprayViewDTO.CLASS, requestIF);
      Halp.setReadableAttributes(request, "abstractSprayAtribs_zs", ZoneSprayViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("abstractSprayAtribs_os") %>);
    available.addAll(<%= request.getAttribute("abstractSprayAtribs_ts") %>);
    available.addAll(<%= request.getAttribute("abstractSprayAtribs_zs") %>);
    abstractSprayAtribs = Mojo.Iter.filter(abstractSprayAtribs, function(attrib){
      return this.contains(attrib);
    }, available);  
    
    var operatorSprayMap = {<%=(String) request.getAttribute("operatorSprayMap")%>};

   
    var Spray_Details = ([
                          {
                            
                            key:"aggregation_level",
                            type:"sqlcharacter",
                            attributeName:"aggregation_level",
                            dropDownMap:{'1':'1','2':'2','3':'3'}
                          },
                         ]);


    // HouseholdSprayStatus, OperatorSprayStatus, TeamSprayStatus (Used for Spray_Details and Household_Structure_Details)
    <%
      Halp.setReadableAttributes(request, "hss", HouseholdSprayStatusViewDTO.CLASS, requestIF);
      Halp.setReadableAttributes(request, "oss", OperatorSprayStatusViewDTO.CLASS, requestIF);
      Halp.setReadableAttributes(request, "tss", TeamSprayStatusViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("hss") %>);
    available.addAll(<%= request.getAttribute("oss") %>);
    available.addAll(<%= request.getAttribute("tss") %>);

    var sprayStatus = new Mojo.$.dss.vector.solutions.irs.HouseholdSprayStatus();
    var sprayStatusAttribs = ["households","sprayedHouseholds","structures","sprayedStructures","rooms","sprayedRooms","locked","refused","other"];
    sprayStatusAttribs = Mojo.Iter.filter(sprayStatusAttribs, function(attrib){
      return this.contains(attrib);
    }, available); 

    Spray_Details = Spray_Details.concat(abstractSprayAtribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:abstractSpray, suffix:'_spray', dropDownMaps:operatorSprayMap, type:'dss.vector.solutions.irs.AbstractSpray'}));
    Spray_Details = Spray_Details.concat(sprayStatusAttribs.map(MDSS.QueryBaseNew.mapInts, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.AbstractSpray'}));

    // The last three unsprayed columns (locked, refused, other) go after the calculations
    var unsprayedLast = Spray_Details.splice(-3,3);
    
   Spray_Details = Spray_Details.concat([
                                  {
                                    
                                    key:"sprayedunits",
                                    type:"sqlinteger",
                                    attributeName:"sprayedunits",
                                    dtoType:'AttributeIntegerDTO'
                                  },   
                                   {
                                     
                                     key:"household_unsprayed",
                                     type:"sqlinteger",
                                     attributeName:"household_unsprayed",
                                     dtoType:'AttributeIntegerDTO'
                                   },
                                  {
                                     
                                     key:"structure_unsprayed",
                                     type:"sqlinteger",
                                     attributeName:"structure_unsprayed",
                                     dtoType:'AttributeIntegerDTO'
                                   },
                                   {
                                     
                                     key:"room_unsprayed",
                                     type:"sqlinteger",
                                     attributeName:"room_unsprayed",
                                     dtoType:'AttributeIntegerDTO'
                                   },
                                   {
                                      
                                      key:"unit_unsprayed",
                                      type:"sqlinteger",
                                      attributeName:"unit_unsprayed",
                                      dtoType:'AttributeIntegerDTO'
                                   }
                                ],
                                unsprayedLast
                                );
  
   
   // HouseholdSprayStatus
   var HouseHold_Structure_Detail = [];
   if(available.contains('<%= HouseholdSprayStatusViewDTO.HOUSEHOLDID %>'))
   {
	   HouseHold_Structure_Detail.push({
           
           key:"household_id",
           type:"sqlcharacter",
           attributeName:"household_id",
         });
   }

   if(available.contains('<%= HouseholdSprayStatusViewDTO.STRUCTUREID %>'))
   {
	   HouseHold_Structure_Detail.push({
           
           key:"structure_id",
           type:"sqlcharacter",
           attributeName:"structure_id",
         });
   }

   // Uses the available filter above
   var netAttribs = [/*"people",*/"prevSprayedHouseholds","prevSprayedStructures","bedNets","roomsWithBedNets"];
   netAttribs = Mojo.Iter.filter(netAttribs, function(attrib){
     return this.contains(attrib);
   }, available);  

   HouseHold_Structure_Detail = HouseHold_Structure_Detail.concat(netAttribs.map(MDSS.QueryBaseNew.mapInts, {obj:sprayStatus, suffix:'_spray', dropDownMaps:{}, type:'dss.vector.solutions.irs.AbstractSpray'}));


    var Coverage = [

                                   {
                                     
                                     key:"unit_application_rate",
                                     type:"sqlfloat",
                                     attributeName:"unit_application_rate",
                                     isAggregate:true
                                   },
                                   {
                                     
                                     key:"unit_application_rate_mg",
                                     type:"sqlfloat",
                                     attributeName:"unit_application_rate_mg",
                                     isAggregate:true
                                   },
                                   {
                                     
                                     key:"unit_application_ratio",
                                     type:"sqldouble",
                                     attributeName:"unit_application_ratio",
                                     isAggregate:true
                                   },
                                   {
                                     
                                     key:"unit_operational_coverage",
                                     type:"sqldouble",
                                     attributeName:"unit_operational_coverage",
                                     isAggregate:true
                                   },                      
                                   {
                                     
                                     key:"calculated_rooms_sprayed",
                                     type:"sqldouble",
                                     attributeName:"calculated_rooms_sprayed",
                                     isAggregate:true
                                   },
                                   {
                                     
                                     key:"calculated_structures_sprayed",
                                     type:"sqldouble",
                                     attributeName:"calculated_structures_sprayed",
                                     isAggregate:true
                                   },
                                   {
                                     
                                     key:"calculated_households_sprayed",
                                     type:"sqldouble",
                                     attributeName:"calculated_households_sprayed",
                                     isAggregate:true
                                   },
                                   
                                  
                                ];

    var Spray_Team_Detail = [
                                {
                                  
                                  key:"sprayoperator_defaultLocale",
                                  type:"sqlcharacter",
                                  attributeName:"sprayoperator_defaultLocale",
                                },
                                /* OLD
                                {
                                  
                                  key:"operator_week",
                                  type:"sqlinteger",
                                  attributeName:"operator_week",
                                },
                                */
                                {
                                  
                                  key:"sprayteam_defaultLocale",
                                  type:"sqlcharacter",
                                  attributeName:"sprayteam_defaultLocale",
                                },
                                {
                                  
                                  key:"sprayleader_defaultLocale",
                                  type:"sqlcharacter",
                                  attributeName:"sprayleader_defaultLocale",
                                },
                                /* OLD
                                {
                                  
                                  key:"team_week",
                                  type:"sqlinteger",
                                  attributeName:"team_week",
                                },
                                */
                                {
                                  
                                  key:"zone_supervisor",
                                  type:"sqlcharacter",
                                  attributeName:"zone_supervisor",
                                },
                                /* OLD
                                {
                                  
                                  key:"zone_week",
                                  type:"sqlinteger",
                                  attributeName:"zone_week",
                                }
                                */


                             ];


    var selectableGroups = [
 	                         {title:"Target_Management_Query", values:targetManagementColumns, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
                             {title:"Insecticide", values:Insecticide_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"Spray_Details", values:Spray_Details, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"Calculations", values:Coverage, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"HouseHold_Structure_Detail", values:HouseHold_Structure_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                         {title:"Spray_Team_Detail", values:Spray_Team_Detail, group:"spray", klass:Mojo.$.dss.vector.solutions.irs.AbstractSpray.CLASS},
 	                     ];

    var query = new MDSS.QueryIRS(selectableGroups, queryList);
    query.render();

    var dm = query.getDependencyManager();
    dm.includes({
      independent: 'concentrationQuantifier_spray',
      dependent: 'concentrationQualifier_spray',
      type: MDSS.Dependent.BOTH,
      bidirectional: true
    });
    dm.includes({
      independent: 'unitQuantifier_spray',
      dependent: 'unitQualifier_spray',
      type: MDSS.Dependent.BOTH,
      bidirectional: true
    });

});

--></script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>