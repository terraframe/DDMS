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
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.intervention.monitor.*"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandDTO"%>
<%@page import="com.runwaysdk.business.BusinessDTO"%>


<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%>
<%@page import="dss.vector.solutions.query.QueryConstants"%>

<%@page import="dss.vector.solutions.querybuilder.InterventionControlQB"%>
<%@page import="com.runwaysdk.generation.loader.RunwayClassLoader"%>
<%@page import="java.lang.reflect.Field"%><c:set var="page_title" value="Query_Intervention_Control"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ ControlInterventionDTO.CLASS, IndividualPremiseVisitDTO.CLASS, IndividualPremiseVisitViewDTO.CLASS, IndividualPremiseVisitMethodDTO.CLASS,  AggregatedPremiseReasonDTO.CLASS, AggregatedPremiseVisitDTO.CLASS,PersonInterventionDTO.CLASS ,PersonInterventionMethodDTO.CLASS, InsecticideBrandDTO.CLASS, InsecticideInterventionDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>
<script type="text/javascript"><!--

// Setting both values to false will select *all* univerals

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

    var individualPremiseVisitMethodMaps = {<%=(String) request.getAttribute("individualPremiseVisit")%>};
    var aggregatedPremiseVisitMaps = {<%=(String) request.getAttribute("aggregatedPremiseVisit")%>};
    var personInterventionMaps = {<%=(String) request.getAttribute("PersonIntervention")%>};
    
    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var controlIntervention = new dss.vector.solutions.intervention.monitor.ControlIntervention;
    var controlInterventionAttribs = [ "startDate","endDate","geoEntity","comments"];

    
    var calculations = ([
                         {
                           key:'<%= QueryConstants.TOTAL_PREMISES_AVAILABLE %>',
                           type:'sqlfloat',
                           attributeName:'<%= QueryConstants.TOTAL_PREMISES_AVAILABLE %>',
                           isAggregate:true
                         },
                         {
                           
                           key:"<%= QueryConstants.TOTAL_PREMISES_VISITED %>",
                           type:"sqlfloat",
                           attributeName:"<%= QueryConstants.TOTAL_PREMISES_VISITED %>",
                           isAggregate:true
                         },
                         {
                           
                           key:"<%= QueryConstants.TOTAL_PREMISES_TREATED %>",
                           type:"sqlfloat",
                           attributeName:"<%= QueryConstants.TOTAL_PREMISES_TREATED %>",
                           isAggregate:true
                         },
                         {
                           
                           key:"<%= QueryConstants.TOTAL_PREMISES_NOT_TREATED %>",
                           type:"sqlfloat",
                           attributeName:"<%= QueryConstants.TOTAL_PREMISES_NOT_TREATED %>",
                           isAggregate:true
                         },
                         {
                           
                           key:"<%= QueryConstants.PERCENT_PREMISES_VISITED %>",//Percentage of premises that were visited
                           type:"sqlfloat",
                           attributeName:"<%= QueryConstants.PERCENT_PREMISES_VISITED %>",
                           isAggregate:true
                         },
                         {
                           key:"<%= QueryConstants.PERCENT_PREMISES_TREATED %>",//Percentage of premises that were treated
                           type:"sqlfloat",
                           attributeName:"<%= QueryConstants.PERCENT_PREMISES_TREATED %>",
                           isAggregate:true
                         },
                         {
                           
                           key:"<%= QueryConstants.PERCENT_VISITED_TREATED %>",//Percentage of visits that resulted in treatment
                           type:"sqlfloat",
                           attributeName:"<%= QueryConstants.PERCENT_VISITED_TREATED %>",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"<%= QueryConstants.PERCENT_VISITED_NOT_TREATED %>",//Percentage of visits that failed to result in treatment
                           type:"sqlfloat",
                           attributeName:"<%= QueryConstants.PERCENT_VISITED_NOT_TREATED %>",
                           isAggregate:true
                         },
                         {
                           key:"childId_tm",
                           displayLabel: MDSS.localize('Treatment_Method'),
                           description: MDSS.localize('Treatment_Method_desc'),
                           type:"dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethod",
                           attributeName:"childId",
                           dtoType:"com.runwaysdk.transport.attributes.AttributeReferenceDTO",
                           isTerm : true

                          },

                          {
                            key:'childId_r',
                            displayLabel: MDSS.localize('reason_not_treated'),
                            description: MDSS.localize('reason_not_treated_desc'),
                            attributeName:'childId',
                            type:"dss.vector.solutions.intervention.monitor.AggregatedPremiseReason",
                            dtoType:"com.runwaysdk.transport.attributes.AttributeReferenceDTO",
                            isTerm: true
                          }
                      
                        ]);




    <%
    Halp.setReadableAttributes(request, "ciAttribs", ControlInterventionDTO.CLASS, requestIF);
    Halp.setReadableAttributes(request, "civAttribs", ControlInterventionViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("ciAttribs") %>);
    controlInterventionAttribs = Mojo.Iter.filter(controlInterventionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var controlInterventionColumns =   controlInterventionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:controlIntervention, suffix:'_ci', dropDownMaps:{}});

    var individualPremiseVisit = new dss.vector.solutions.intervention.monitor.IndividualPremiseVisit;
    var individualPremiseVisitAttribs = [];

    var individualPremiseVisitColumns = [
      {
        key:"subGeoEntity_ic",
        displayLabel: MDSS.localize('Sub_Geo_Entity'),
        description: MDSS.localize('Sub_Geo_Entity_desc'),
        type:"sqlcharacter",
        attributeName:"subGeoEntity",
        dtoType:"AttributeCharacterDTO",
        isGeoEntity : true
      },
      {
        key: individualPremiseVisit.constructor.VISITED+'_ind',
        type:"sqlinteger",
        displayLabel:individualPremiseVisit.getVisitedMd().getDisplayLabel(),
        description:individualPremiseVisit.getVisitedMd().getDescription(),
        attributeName:'visited',
        dtoType:"com.runwaysdk.transport.attributes.AttributeIntegerDTO",
      },
      {
        key:individualPremiseVisit.constructor.TREATED+'_ind',
        type:"sqlinteger",
        displayLabel:individualPremiseVisit.getTreatedMd().getDisplayLabel(),
        description:individualPremiseVisit.getTreatedMd().getDescription(),
        attributeName:'treated',
        dtoType:"com.runwaysdk.transport.attributes.AttributeIntegerDTO",
      }

      /*
      {
        key:individualPremiseVisit.constructor.REASONSFORNOTTREATED+'_ind',
        type:"sqlfloat",
        displayLabel:individualPremiseVisit.getReasonsForNotTreatedMd().getDisplayLabel(),
        attributeName:'reasonsForNotTreated',
        dtoType:"com.runwaysdk.transport.attributes.AttributeFloatDTO",
      }
      */ 
    ];
    individualPremiseVisitColumns =   individualPremiseVisitColumns.concat(individualPremiseVisitAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualPremiseVisit, suffix:'_ic', dropDownMaps:individualPremiseVisitMethodMaps}));

    var func = Mojo.Util.curry(MDSS.QueryBaseNew.mapMoWithPrepend, '<%=request.getAttribute("individualMethodLabel")%>');
    individualPremiseVisitColumns = individualPremiseVisitColumns.concat(orderedGrids.individualPremiseVisitMethod.options.map(func, orderedGrids.individualPremiseVisitMethod));

    var reasons = <%= request.getAttribute("reasons") %>;
    var reasonCols = [];
    for(var i=0; i<reasons.length; i++)
    {
      var reason = reasons[i];
      reasonCols.push({
        key: reason.key,
        displayLabel: '<%=request.getAttribute("individualReasonLabel")%> - ' + reason.label,
        attributeName: reason.key,
        dtoType:"com.runwaysdk.transport.attributes.AttributeIntegerDTO",
        type:'sqlinteger'
      });
    }
    individualPremiseVisitColumns = individualPremiseVisitColumns.concat(reasonCols);
    
    var aggregatedPremiseVisit = new dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit;
    var aggregatedPremiseVisitAttribs = ["premises","visited","treated"];

    <%
    Halp.setReadableAttributes(request, "aggregatedPremiseVisitAttribs", AggregatedPremiseVisitViewDTO.CLASS, requestIF);
    %>
    
    var availableForAggregatedIntervention = new MDSS.Set(<%= request.getAttribute("aggregatedPremiseVisitAttribs") %>);

    aggregatedPremiseVisitAttribs = Mojo.Iter.filter(aggregatedPremiseVisitAttribs, function(attrib){
      return this.contains(attrib);
    }, availableForAggregatedIntervention);    
    

    var aggregatedPremiseVisitColumns = [{
      key:"subGeoEntity_ip",
      displayLabel: MDSS.localize('Sub_Geo_Entity'),
      description: MDSS.localize('Sub_Geo_Entity_desc'),
      type:"sqlcharacter",
      attributeName:"subGeoEntity",
      dtoType:"AttributeCharacterDTO",
      isGeoEntity : true
      }];
    aggregatedPremiseVisitColumns =   aggregatedPremiseVisitColumns.concat(aggregatedPremiseVisitAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:aggregatedPremiseVisit, suffix:'_ip', dropDownMaps:aggregatedPremiseVisitMaps}));

    var aggMethod = Mojo.Util.curry(MDSS.QueryBaseNew.mapMoWithPrepend, '<%=request.getAttribute("aggMethodLabel")%>');
    var aggReason = Mojo.Util.curry(MDSS.QueryBaseNew.mapMoWithPrepend, '<%=request.getAttribute("aggReasonLabel")%>');
    
    aggregatedPremiseVisitColumns = aggregatedPremiseVisitColumns.concat(orderedGrids.aggInterventionMethods.options.map(aggMethod, orderedGrids.aggInterventionMethods));
    aggregatedPremiseVisitColumns = aggregatedPremiseVisitColumns.concat(orderedGrids.aggInterventionReasons.options.map(aggReason, orderedGrids.aggInterventionReasons));
    
    var personIntervention = new dss.vector.solutions.intervention.monitor.PersonIntervention;
    var personInterventionAttribs = ["vehicleDays"];

    <%
    Halp.setReadableAttributes(request, "personInterventionAttribs", PersonInterventionViewDTO.CLASS, requestIF);
    %>
    
    var availableForPersonIntervention = new MDSS.Set(<%= request.getAttribute("personInterventionAttribs") %>);

    personInterventionAttribs = Mojo.Iter.filter(personInterventionAttribs, function(attrib){
      return this.contains(attrib);
    }, availableForPersonIntervention);    
    

    var vehicleBasedSprayingColumns = [
      {
        key:"subGeoEntity_v",
        displayLabel: MDSS.localize('Sub_Geo_Entity'),
        description: MDSS.localize('Sub_Geo_Entity_desc'),
        type:"sqlcharacter",
        attributeName:"subGeoEntity",
        dtoType:"AttributeCharacterDTO",
        isGeoEntity : true
      },
      {
        key:'<%= QueryConstants.PREMISES_AVAILABLE_FOR_VEHICLE_SPRAYING %>',
        type:'sqlfloat',
        displayLabel: aggregatedPremiseVisit.getPremisesAvailableMd().getDisplayLabel(),
        description:aggregatedPremiseVisit.getPremisesAvailableMd().getDescription(),
        attributeName:'<%= QueryConstants.PREMISES_AVAILABLE_FOR_VEHICLE_SPRAYING %>',
        isAggregate:true
      },
      {
        key:'<%= QueryConstants.PREMISES_INCLUDED_FOR_VEHICLE_SPRAYING %>',
        type:'sqlfloat',
        displayLabel: aggregatedPremiseVisit.getPremisesIncludedMd().getDisplayLabel(),
        description:aggregatedPremiseVisit.getPremisesIncludedMd().getDescription(),
        attributeName:'<%= QueryConstants.PREMISES_INCLUDED_FOR_VEHICLE_SPRAYING %>',
        isAggregate:true
      },
      {
        key:'<%= QueryConstants.PERCENT_TREATED_WITH_VEHICLE_SPRAYING %>',
        type:'sqlfloat',
        attributeName:'<%= QueryConstants.PERCENT_TREATED_WITH_VEHICLE_SPRAYING %>',
        isAggregate:true
      }
    ];
    
    var personInterventionColumns =  personInterventionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:personIntervention, suffix:'_ap', dropDownMaps:personInterventionMaps});

    var personMethod = Mojo.Util.curry(MDSS.QueryBaseNew.mapMoWithPrepend, '<%=request.getAttribute("personMethodLabel")%>');
    
    personInterventionColumns = personInterventionColumns.concat(orderedGrids.personInterventionMethods.options.map(personMethod, orderedGrids.personInterventionMethods));
    
    var insecticideIntervention = new dss.vector.solutions.intervention.monitor.InsecticideIntervention;
    var insecticideInterventionAttribs = [ "interventionMethod","quantity","unit"];

    <%
    Halp.setReadableAttributes(request, "insecticideInterventionAttribs", InsecticideInterventionViewDTO.CLASS, requestIF);
    %>
    
    var availableForInsecticideIntervention = new MDSS.Set(<%= request.getAttribute("insecticideInterventionAttribs") %>);

    insecticideInterventionAttribs = Mojo.Iter.filter(insecticideInterventionAttribs, function(attrib){
      return this.contains(attrib);
    }, availableForInsecticideIntervention);    

    
    var insecticideInterventionColumns =  insecticideInterventionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insecticideIntervention, suffix:'_pi', dropDownMaps:{}});
    
    
    var insecticideBrand = new dss.vector.solutions.irs.InsecticideBrand;
    var insecticideBrandAttribs = [
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
    Halp.setReadableAttributes(request, "insecticideAttribs", InsecticideBrandDTO.CLASS, requestIF);
    %>
    
    var availableForInsecticide = new MDSS.Set(<%= request.getAttribute("insecticideAttribs") %>);

    insecticideBrandAttribs = Mojo.Iter.filter(insecticideBrandAttribs, function(attrib){
      return this.contains(attrib);
    }, availableForInsecticide);    

    var insecticideBrandMap = {<%=(String) request.getAttribute("insecticideBrandMap")%>};
    var insecticideBrandColumns =   insecticideBrandAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insecticideBrand, suffix:'_ii', dropDownMaps:insecticideBrandMap});

    // move quantity and unit to the end of the array
    var endColumns = insecticideInterventionColumns.splice(1,2);
    insecticideInterventionColumns = insecticideInterventionColumns.concat(insecticideBrandColumns, endColumns);

    var calculationsSection = "Calculations_for_premises_visit_outcome";
 
    var selectableGroups = [
      {title:"Intervention_Monitoring", values:controlInterventionColumns, group:"ic", klass:controlIntervention.CLASS},
      {title:calculationsSection, values:calculations, group:"ic", klass:controlIntervention.CLASS}
    ];

    var availableForView = new MDSS.Set(<%= request.getAttribute("civAttribs") %>);

    if(availableForView.contains("<%=ControlInterventionViewDTO.INDIVIDULPREMISEUNIVERSAL%>"))
    {
      selectableGroups = selectableGroups.concat({title:"Individual_Premise_Visit", values:individualPremiseVisitColumns, group:"ic", klass:controlIntervention.CLASS});
    }
  
    if(availableForView.contains("<%=ControlInterventionViewDTO.AGGREGATEDPREMISEUNIVERSAL%>"))
    {
      selectableGroups = selectableGroups.concat({title:"Aggregated_Premise_Visit", values:aggregatedPremiseVisitColumns, group:"ic", klass:controlIntervention.CLASS});
    }
    
    selectableGroups = selectableGroups.concat({title:"Vehicle_Calculator_Subheading", values:vehicleBasedSprayingColumns, group:"ic", klass:controlIntervention.CLASS});
  
    if(availableForView.contains("<%=ControlInterventionViewDTO.PERSONINTERVENTION%>"))
    {
      selectableGroups = selectableGroups.concat({title:"Person_Intervention", values:personInterventionColumns, group:"ic",klass:controlIntervention.CLASS});
    }
  
    if(availableForView.contains("<%=ControlInterventionViewDTO.INSECTICIDEINTERVENTION%>"))
    {
      selectableGroups = selectableGroups.concat({title:"InsecticideIntervention", values:insecticideInterventionColumns, group:"ic",klass:controlIntervention.CLASS});
    }
      
    var query = new MDSS.QueryInterventionControl(selectableGroups, queryList, calculationsSection);
    query.render();

    var dm = query.getDependencyManager();

    
    dm.includes({
      independent: 'concentrationQuantifier_ii',
      dependent: 'concentrationQualifier_ii',
      type: MDSS.Dependent.BOTH,
      bidirectional: true
    });
    dm.includes({
      independent: 'unitQuantifier_ii',
      dependent: 'unitQualifier_ii',
      type: MDSS.Dependent.BOTH,
      bidirectional: true
    });
    // TODO optimize these excludes within a dynamic loop
    dm.excludes({
      independent: controlInterventionAttribs,
      dependent:[],
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.excludes({
      independent: query.getDateGroupIds(),
      dependent:[],
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.excludes({
      independent:calculations,
      dependent:individualPremiseVisitColumns.concat(vehicleBasedSprayingColumns,aggregatedPremiseVisitColumns,personInterventionColumns,insecticideInterventionColumns),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.excludes({
      independent:individualPremiseVisitColumns,
      dependent:calculations.concat(vehicleBasedSprayingColumns,aggregatedPremiseVisitColumns,personInterventionColumns,insecticideInterventionColumns),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false,
      name: MDSS.QueryInterventionControl.INDIVIDUALS_GROUP
    });
    dm.excludes({
      independent:aggregatedPremiseVisitColumns,
      dependent:individualPremiseVisitColumns.concat(vehicleBasedSprayingColumns,calculations,personInterventionColumns,insecticideInterventionColumns),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false,
      name: MDSS.QueryInterventionControl.AGGREGATES_GROUP
    });
    dm.excludes({
      independent:personInterventionColumns,
      dependent:individualPremiseVisitColumns.concat(vehicleBasedSprayingColumns,aggregatedPremiseVisitColumns,calculations,insecticideInterventionColumns),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.excludes({
      independent:insecticideInterventionColumns,
      dependent:individualPremiseVisitColumns.concat(vehicleBasedSprayingColumns,aggregatedPremiseVisitColumns,personInterventionColumns,calculations),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.excludes({
      independent:vehicleBasedSprayingColumns,
      dependent:individualPremiseVisitColumns.concat(insecticideInterventionColumns,aggregatedPremiseVisitColumns,personInterventionColumns,calculations),
      type: MDSS.Dependent.CHECKED,
      bidirectional: false,
      name: MDSS.QueryInterventionControl.VEHICLES_GROUP
    });
    dm.excludes({
      independent: 'childId_tm',
      dependent: 'childId_r',
      type: MDSS.Dependent.CHECKED,
      bidirectional: true
    });

    var handler = Mojo.Util.bind(query, query.togglePremises);
    dm.addAllTransactionsFinishListener(handler);
});

--></script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
