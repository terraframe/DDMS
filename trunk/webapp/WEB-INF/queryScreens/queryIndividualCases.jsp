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
<%@page import="dss.vector.solutions.query.QueryConstants"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
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
<%@page import="dss.vector.solutions.intervention.monitor.IndividualCaseDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualInstanceDTO"%>
<%@page import="dss.vector.solutions.surveillance.IndividualCaseSymptomDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>
<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.PhysicianDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%>

<c:set var="page_title" value="Query_Individual_Cases"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] types = new String[]{ IndividualCaseDTO.CLASS, IndividualInstanceDTO.CLASS, PersonDTO.CLASS,IndividualCaseSymptomDTO.CLASS,PhysicianDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(types));
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


    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var instanceMaps = {<%=(String) request.getAttribute("instanceMaps")%>};
    
    var personMaps = {};

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var individualCase = new Mojo.$.dss.vector.solutions.intervention.monitor.IndividualCase();
    var caseAttribs = ["diagnosisDate","caseReportDate","caseEntryDate", "residence",
                           "workplace", "probableSource", "origin",
                           "symptomOnset", "plasmaLeakageOnset", "hemorrhagicOnset"];
    <%
    Halp.setReadableAttributes(request, "caseAttribs", IndividualCaseDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("caseAttribs") %>);
    caseAttribs = Mojo.Iter.filter(caseAttribs, function(attrib){
      return this.contains(attrib);
    }, available);

    var hasPatient = available.contains('<%= IndividualCaseDTO.PATIENT %>');
    
    var caseColumns = caseAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualCase, suffix:'_case', dropDownMaps:{}});
    

    var individualInstance = new Mojo.$.dss.vector.solutions.intervention.monitor.IndividualInstance();  
    var instanceAttribs = ["healthFacility", "caseIdentifier", "referredTo", "referredFrom",
                           "referralReason","facilityVisit", "admissionDate","releaseDate", "properlyRelease",
                           "diedInFacility", "dateOfDeath",   "activelyDetected", "caseDetection", "detectedBy","patientCategory",
                           "diagnosisType","diagnosis", "confirmedDiagnosis", "confirmedDiagnosisDate",
                           "classification", "sampleType", "labTest","testSampleDate","labTestDate",
                           "testResult","malariaType", "primaryInfection", "anaemiaPatient", 
                           "pregnant", "treatment","treatmentMethod", "treatmentStartDate"];
    <%
    Halp.setReadableAttributes(request, "instanceAttribs", IndividualInstanceDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("instanceAttribs") %>);
    instanceAttribs = Mojo.Iter.filter(instanceAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    var hasSymptoms = available.contains('<%= IndividualInstanceDTO.SYMPTOM %>');
    var hasPhysician = available.contains('<%= IndividualInstanceDTO.PHYSICIAN %>');
        
    var instanceColumns = instanceAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualInstance, suffix:'_ins', dropDownMaps:instanceMaps});

    var person = new Mojo.$.dss.vector.solutions.Person();   
    var personAttribs = ["identifier", "firstName","lastName", "dateOfBirth", "age", "birthEntity", "sex"];
    <%
    Halp.setReadableAttributes(request, "personAttribs", PersonViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("personAttribs") %>);
    personAttribs = Mojo.Iter.filter(personAttribs, function(attrib){
      return this.contains(attrib);
    }, available);

    var personColumns =  personAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_per', dropDownMaps:{}});
    MDSS.QueryBase.filterFunctions(personColumns, 'age', MDSS.QueryXML.F_SET1);
    
    // physician delegates to a person so we use the person metadata
    var physicianAttribs = ["firstName","lastName"];
    <%
    Halp.setReadableAttributes(request, "physicianAttribs", PersonViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("physicianAttribs") %>);
    physicianAttribs = Mojo.Iter.filter(physicianAttribs, function(attrib, ind){
      return this.contains(attrib);
    }, available);
    
    var physicianColumns = physicianAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_phy', dropDownMaps:{}, deref:{'firstName':'person.', 'lastName':'person.'}, type:Mojo.$.dss.vector.solutions.Physician.CLASS});
    // Prepend with "Physician"
    var physicianPrepend = MDSS.localize('Physician_Prepend');
    for(var p=0; p<physicianColumns.length; p++)
    {
      physicianColumns[p].displayLabel = physicianPrepend+' '+physicianColumns[p].displayLabel.toLowerCase();
    }
    
    var calculations = ([
                          {
                           
                           key:"instances",
                           type:"sqlinteger",
                           attributeName:"instances",
                           isAggregate:true  
                          },
                          {
                           
                           key:"cases",
                           type:"sqlfloat",
                           attributeName:"cases",
                           displayLabel:MDSS.localize("Adjusted_Case_Count"),
                           description:MDSS.localize("Adjusted_Case_Count_desc"),
                           isAggregate:true
                          },
                          {
                           key:"deaths",
                           type:"sqlinteger",
                           attributeName:"deaths",
                           isAggregate:true
                          },
                          {
                            key:"<%= QueryConstants.POPULATION %>",
                            attributeName:"<%= QueryConstants.POPULATION %>",
                            displayLabel:MDSS.localize('population'),
                            description:MDSS.localize('population'),
                            type:'sqlfloat',
                            isAggregate:false
                          },
                          {
                            
                            key:"incidence_100",
                            type:"sqlfloat",
                            attributeName:"incidence_100",
                            isAggregate:true
                          },

                          {
                            
                            key:"incidence_1000",
                            type:"sqlfloat",
                            attributeName:"incidence_1000",
                            isAggregate:true
                          },

                          {
                            
                            key:"incidence_10000",
                            type:"sqlfloat",
                            attributeName:"incidence_10000",
                            isAggregate:true
                          },

                          {
                            
                            key:"incidence_100000",
                            type:"sqlfloat",
                            attributeName:"incidence_100000",
                            //dropDownMap:{'100':'100','1,000':'1000','10,000':'10000','100,000':'100000'},
                            isAggregate:true
                          },
                          
                          {
                            
                            key:"cfr",
                            type:"sqlfloat",
                            attributeName:"cfr",
                            isAggregate:true
                          },

                         ]);

    var selectableGroups = [{title:"Case", values:caseColumns, group:"c", klass:individualCase.CLASS}];

    if(hasPatient)
    {    
      selectableGroups.push({title:"Patient", values:personColumns, group:"c", klass:individualCase.CLASS});
    }

    if(hasPhysician)
    {    
      selectableGroups.push({title:"Physician", values:physicianColumns, group: "c", klass:Mojo.$.dss.vector.solutions.Physician.CLASS});
    }
    
    selectableGroups.push({title:"Instance", values:instanceColumns, group:"c", klass:individualCase.CLASS});
    selectableGroups.push({title:"Calculations", values:calculations, group:"c", klass:individualCase.CLASS});

    if(hasSymptoms)
    {
      var symptomsColumns = orderedGrids.symptoms.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.symptoms);
      selectableGroups.push({title:"Symptoms", values:symptomsColumns, group:"c", klass:Mojo.$.dss.vector.solutions.surveillance.IndividualCaseSymptom.CLASS});
    }

    var query = new MDSS.QueryIndividualCases(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>



<jsp:include page="../templates/footer.jsp"></jsp:include>
