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
<c:set var="page_title" value="Query_Intervention_Control"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ ControlInterventionDTO.CLASS, IndividualPremiseVisit.CLASS, IndividualPremiseVisitMethodDTO.CLASS,  AggregatedPremiseReasonDTO.CLASS, AggregatedPremiseVisitDTO.CLASS,PersonInterventionDTO.CLASS ,PersonInterventionMethodDTO.CLASS, InsecticideBrandDTO.CLASS, InsecticideInterventionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">
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
                           
                           key:"total_premises_visited",
                           type:"sqlfloat",
                           attributeName:"total_premises_visited",
                           isAggregate:true
                         },
                         {
                           
                           key:"total_premises_treated",
                           type:"sqlfloat",
                           attributeName:"total_premises_treated",
                           isAggregate:true
                         },
                         {
                           
                           key:"total_premises_not_treated",
                           type:"sqlfloat",
                           attributeName:"total_premises_not_treated",
                           isAggregate:true
                         },
                         {
                           
                           key:"percent_premises_visited",//Percentage of premises that were visited
                           type:"sqlfloat",
                           attributeName:"percent_premises_visited",
                           isAggregate:true
                         },
                         {
                           
                           key:"percent_premises_treated",//Percentage of premises that were treated
                           type:"sqlfloat",
                           attributeName:"percent_premises_treated",
                           isAggregate:true
                         },
                         {
                           
                           key:"percent_visited_treated",//Percentage of visits that resulted in treatment
                           type:"sqlfloat",
                           attributeName:"percent_visited_treated",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"percent_visited_not_treated",//Percentage of visits that failed to result in treatment
                           type:"sqlfloat",
                           attributeName:"percent_visited_not_treated",
                           isAggregate:true
                         },
                         {
                           key:"childId",
                           displayLabel: MDSS.localize('Treatment_Method'),
                           type:"dss.vector.solutions.intervention.monitor.IndividualPremiseVisitMethod",
                           attributeName:"childId",
                           dtoType:"com.runwaysdk.transport.attributes.AttributeReferenceDTO",
                           isTerm : true

                           },

                           {
                             key:"method_qty",
                             type:"sqlfloat",
                             attributeName:"method_qty",
                             isAggregate:true
                           },
                      
                        ]);




    <%
    Halp.setReadableAttributes(request, "ciAttribs", ControlInterventionDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("ciAttribs") %>);
    controlInterventionAttribs = Mojo.Iter.filter(controlInterventionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var controlInterventionColumns =   controlInterventionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:controlIntervention, suffix:'_ci', dropDownMaps:{}});

    //controlInterventionColumns = controlInterventionColumns.concat(calculations);
    

    var individualPremiseVisit = new dss.vector.solutions.intervention.monitor.IndividualPremiseVisit;
    var individualPremiseVisitAttribs = [ "visited","treated","reasonsForNotTreated"];

    var individualPremiseVisitColumns = [{
      key:"subGeoEntity_ic",
      displayLabel: MDSS.localize('Sub_Geo_Entity'),
      type:"sqlcharacter",
      attributeName:"subGeoEntity",
      dtoType:"AttributeCharacterDTO",
      isGeoEntity : true
      }];
    individualPremiseVisitColumns =   individualPremiseVisitColumns.concat(individualPremiseVisitAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualPremiseVisit, suffix:'_ic', dropDownMaps:individualPremiseVisitMethodMaps}));



    
    individualPremiseVisitColumns = individualPremiseVisitColumns.concat(orderedGrids.individualPremiseVisitMethod.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.individualPremiseVisitMethod));

    
    var aggregatedPremiseVisit = new dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit;
    var aggregatedPremiseVisitAttribs = [ "vehicleCoverage","premises","visited","treated"];

    var aggregatedPremiseVisitColumns = [{
      key:"subGeoEntity_ip",
      displayLabel: MDSS.localize('Sub_Geo_Entity'),
      type:"sqlcharacter",
      attributeName:"subGeoEntity",
      dtoType:"AttributeCharacterDTO",
      isGeoEntity : true
      }];
    aggregatedPremiseVisitColumns =   aggregatedPremiseVisitColumns.concat(aggregatedPremiseVisitAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:aggregatedPremiseVisit, suffix:'_ip', dropDownMaps:aggregatedPremiseVisitMaps}));
    
    aggregatedPremiseVisitColumns = aggregatedPremiseVisitColumns.concat(orderedGrids.aggInterventionReasons.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.aggInterventionReasons));
    aggregatedPremiseVisitColumns = aggregatedPremiseVisitColumns.concat(orderedGrids.aggInterventionMethods.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.aggInterventionMethods));
    
    var personIntervention = new dss.vector.solutions.intervention.monitor.PersonIntervention;
    var personInterventionAttribs = ["vehicleDays"];

    
    var personInterventionColumns =  personInterventionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:personIntervention, suffix:'_ap', dropDownMaps:personInterventionMaps});

    personInterventionColumns = personInterventionColumns.concat(orderedGrids.personInterventionMethods.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.personInterventionMethods));
    
    var insecticideIntervention = new dss.vector.solutions.intervention.monitor.InsecticideIntervention;
    var insecticideInterventionAttribs = [ "interventionMethod","quantity","unit"];
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

    var insecticideBrandMap = {<%=(String) request.getAttribute("insecticideBrandMap")%>};
    var insecticideBrandColumns =   insecticideBrandAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insecticideBrand, suffix:'_ii', dropDownMaps:insecticideBrandMap});


    insecticideInterventionColumns = insecticideInterventionColumns.concat(insecticideBrandColumns);

 
      var selectableGroups = [
                {title:"Intervention_Control", values:controlInterventionColumns, group:"ic", klass:controlIntervention.CLASS},
                {title:"Calculations", values:calculations, group:"ic", klass:controlIntervention.CLASS},
                {title:"Individual_Premise_Visit", values:individualPremiseVisitColumns, group:"ip", klass:controlIntervention.CLASS},
                {title:"Aggregated_Premise_Visit", values:aggregatedPremiseVisitColumns, group:"ap", klass:controlIntervention.CLASS},
                {title:"Person_Intervention", values:personInterventionColumns, group:"pi",klass:controlIntervention.CLASS},
                {title:"InsecticideIntervention", values:insecticideInterventionColumns, group:"ii",klass:controlIntervention.CLASS},
      ];


    
    var query = new MDSS.QueryInterventionControl(selectableGroups, queryList);
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
});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
