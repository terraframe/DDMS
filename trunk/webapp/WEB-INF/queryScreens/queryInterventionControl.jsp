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
//"aggregatedPremiseUniversal","individulPremiseUniversal"

    
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
                           
                           key:"total_person_days",
                           type:"sqlfloat",
                           attributeName:"total_person_days",
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
                           type:"dss.vector.solutions.intervention.monitor.IndividualdPremiseVisitMethod",
                           attributeName:"childId",
                           dtoType:"com.runwaysdk.transport.attributes.AttributeReferenceDTO",
                           isTerm : true

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

    controlInterventionColumns = controlInterventionColumns.concat(calculations);
    

    var individualPremiseVisit = new dss.vector.solutions.intervention.monitor.IndividualPremiseVisit;
    var individualPremiseVisitAttribs = [ "geoEntity","visited","treated","reasonsForNotTreated"];
    var individualPremiseVisitColumns =   individualPremiseVisitAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualPremiseVisit, suffix:'_ci', dropDownMaps:individualPremiseVisitMethodMaps});

    individualPremiseVisitColumns = individualPremiseVisitColumns.concat(orderedGrids.individualPremiseVisitMethod.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.individualPremiseVisitMethod));

    /*
    individualPremiseVisitColumns = individualPremiseVisitColumns.concat([{
      key:"childId",
      displayLabel: MDSS.localize('Method'),
      type:"dss.vector.solutions.intervention.monitor.IndividualdPremiseVisitMethod",
      attributeName:"childId",
      dtoType:"com.runwaysdk.transport.attributes.AttributeReferenceDTO",
      isTerm : true

      }].concat();
    */
    //var individualPremiseVisitMethodAttribs = [ "used"];
    //var individualPremiseVisitMethod = new dss.vector.solutions.intervention.monitor.IndividualdPremiseVisitMethod;
    
    
    var aggregatedPremiseVisit = new dss.vector.solutions.intervention.monitor.AggregatedPremiseVisit;
    var aggregatedPremiseVisitAttribs = [ "geoEntity","vehicleCoverage","premises","visited","treated"];
    var aggregatedPremiseVisitColumns =   aggregatedPremiseVisitAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:aggregatedPremiseVisit, suffix:'_ci', dropDownMaps:aggregatedPremiseVisitMaps});
    
    aggregatedPremiseVisitColumns = aggregatedPremiseVisitColumns.concat(orderedGrids.aggInterventionReasons.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.aggInterventionReasons));
    aggregatedPremiseVisitColumns = aggregatedPremiseVisitColumns.concat(orderedGrids.aggInterventionMethods.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.aggInterventionMethods));
    //var AggregatedVisitMethod = new dss.vector.solutions.intervention.monitor.AggregatedPremiseVisitMethod;
//    var aggregatedPremiseVisitMethodAttribs = [ "amount"];
    
    //var AggregatedPremiseReason = new dss.vector.solutions.intervention.monitor.AggregatedPremiseReason;
   // var aggregatedPremiseReasonAttribs = [ "amount"];
    
    var personIntervention = new dss.vector.solutions.intervention.monitor.PersonIntervention;
    var personInterventionAttribs = ["vehicleDays"];

    
    var personInterventionColumns =  personInterventionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:personIntervention, suffix:'_ci', dropDownMaps:personInterventionMaps});

    personInterventionColumns = personInterventionColumns.concat(orderedGrids.personInterventionMethods.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.personInterventionMethods));
    //var PersonInterventionMethod = new dss.vector.solutions.intervention.monitor.PersonInterventionMethod;
    
    
    var insecticideIntervention = new dss.vector.solutions.intervention.monitor.InsecticideIntervention;
    var insecticideInterventionAttribs = [ "interventionMethod","quantity","unit"];
    var insecticideInterventionColumns =  insecticideInterventionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insecticideIntervention, suffix:'_ci', dropDownMaps:{}});

    
    var insecticideBrand = new dss.vector.solutions.irs.InsecticideBrand;
    var insecticideBrandAttribs = [ "brandName","activeIngredient","amount"];
    var insecticideBrandColumns =   insecticideBrandAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insecticideBrand, suffix:'_ci', dropDownMaps:{}});


    insecticideInterventionColumns = insecticideInterventionColumns.concat(insecticideBrandColumns);

 
      var selectableGroups = [
                {title:"Intervention_Control", values:controlInterventionColumns, group:"c", klass:controlIntervention.CLASS},
                {title:"Individual_Premise_Visit", values:individualPremiseVisitColumns, group:"c", klass:controlIntervention.CLASS},
                {title:"Aggregated_Premise_Visit", values:aggregatedPremiseVisitColumns, group:"c", klass:controlIntervention.CLASS},
                {title:"Person_Intervention", values:personInterventionColumns, group:"c",klass:controlIntervention.CLASS},
                {title:"InsecticideIntervention", values:insecticideInterventionColumns, group:"c",klass:controlIntervention.CLASS},
      ];

      //selectableGroups = selectableGroups.concat(taxonCalcGroups);

    
    var query = new MDSS.QueryInterventionControl(selectableGroups, queryList);
    query.render();

});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
