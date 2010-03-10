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
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualCaseDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualInstanceDTO"%>
<%@page import="dss.vector.solutions.surveillance.IndividualCaseSymptomDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>




<%@page import="com.terraframe.mojo.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%><c:set var="page_title" value="Query_Individual_Cases"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ IndividualCaseDTO.CLASS, IndividualInstanceDTO.CLASS, PersonDTO.CLASS,IndividualCaseSymptomDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
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
    var caseAttribs = ["age","diagnosisDate","caseReportDate","caseEntryDate",
                           "workplace","workplaceText",
                           "probableSource","probableSourceText",
                           "residence","residenceText"];
    <%
    Halp.setReadableAttributes(request, "caseAttribs", IndividualCaseDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("caseAttribs") %>);
    caseAttribs = Mojo.Iter.filter(caseAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var caseColumns = caseAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualCase, suffix:'_case', dropDownMaps:{}});
    

    var individualInstance = new Mojo.$.dss.vector.solutions.intervention.monitor.IndividualInstance();  
    var instanceAttribs = ["activelyDetected",
                       "admissionDate","anaemiaPatient","clinicalDiagnosis",
                       "detectedBy","diedInFacility","facilityVisit",
                       "patientCategory","pregnant","properlyRelease",
                       "referralReason","labTestDate","symptomComments",
                       "releaseDate","sampleType","malariaType",
                       "testSampleDate","treatment","treatmentMethod",
                       "treatmentStartDate"];
    <%
    Halp.setReadableAttributes(request, "instanceAttribs", IndividualInstanceDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("instanceAttribs") %>);
    instanceAttribs = Mojo.Iter.filter(instanceAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    var hasSymptoms = available.contains('<%= IndividualInstanceDTO.SYMPTOM %>');
    
    var instanceColumns = instanceAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualInstance, suffix:'_ins', dropDownMaps:instanceMaps});

    var person = new Mojo.$.dss.vector.solutions.Person();   
    var personAttribs = ["dateOfBirth","firstName","lastName","sex"];
    <%
    Halp.setReadableAttributes(request, "personAttribs", PersonViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("personAttribs") %>);
    personAttribs = Mojo.Iter.filter(personAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
      
    var personColumns =  personAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_per', dropDownMaps:{}});


    var calculations = ([
                          {
                           
                           key:"instances",
                           type:"sqlinteger",
                           attributeName:"instances",
                           isAggregate:true  
                          },
                          {
                           
                           key:"cases",
                           type:"sqlinteger",
                           attributeName:"cases",
                           isAggregate:true
                          },
                          {
                           
                           key:"deaths",
                           type:"sqlinteger",
                           attributeName:"deaths",
                           isAggregate:true
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
    
    var selectableGroups = [
              {title:"Case", values:caseColumns, group:"c", klass:individualCase.CLASS},
              {title:"Patient", values:personColumns, group:"c", klass:individualCase.CLASS},
              {title:"Instance", values:instanceColumns, group:"c", klass:individualCase.CLASS},
              {title:"Calculations", values:calculations, group:"c", klass:individualCase.CLASS},
    ];

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
