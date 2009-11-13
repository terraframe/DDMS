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
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoViewDTO"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
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
<%@page import="dss.vector.solutions.intervention.monitor.IndividualCaseDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualInstanceDTO"%>
<%@page import="dss.vector.solutions.surveillance.IndividualCaseSymptomDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>




<%@page import="com.terraframe.mojo.business.BusinessDTO"%><c:set var="page_title" value="Query_Individual_Cases"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ IndividualCaseDTO.CLASS, IndividualInstanceDTO.CLASS, PersonDTO.CLASS,IndividualCaseSymptomDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, LayerViewDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

    MosquitoViewDTO mosquitoViewDTO = new MosquitoViewDTO(requestIF);

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">
// Setting both values to false will select *all* univerals
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;


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

    var mapAttribs = function(attribName,index){
      var attrib = this.obj.attributeMap[attribName];
      var row = {};
      if(attrib){
        row.attributeName = attrib.attributeName;
        if(attrib.dtoType.contains('AttributeReferenceDTO'))
        {
          row.attributeName += '.name';
        }
        if(attrib.dtoType.contains('AttributeEnumerationDTO'))
        {
          row.attributeName += '.displayLabel.currentValue';
        }
        row.key = attrib.attributeName + this.suffix;
        row.type = this.obj.getType();
        row.dtoType = attrib.dtoType;
        row.displayLabel = attrib.attributeMdDTO.displayLabel;
        var uppFirst = attrib.attributeName.slice(0,1).toUpperCase() + attrib.attributeName.slice(1);
        if(this.dropDownMaps[uppFirst]){
          row.dropDownMap = this.dropDownMaps[uppFirst];
        }
      }else{
        row.attributeName = attribName;
        row.type = 'sqlinteger';
        row.displayLabel = attribName;
        row.key = attribName;
        row.dtoType = "AttributeIntegerDTO";

      }
      return row;
    };


    var mapMo = function(term,index){
    	var row = {};
        //row.attributeName = this.relAttribute;
        //row.key = 'term' + term.MOID.replace(':','') +'_'+ term.id;
        //row.type = this.relType;
        row.dtoType = "AttributeBooleanDTO";
        row.displayLabel = term.displayLabel;
        
        row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;;
        row.type = 'sqlcharacter';
        row.attributeName = 'term' + term.MOID.replace(':','');
        
      return row;
    };

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var instanceMaps = {<%=(String) request.getAttribute("instanceMaps")%>};
    
    var personMaps = {};

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var individualCase = new Mojo.$.dss.vector.solutions.intervention.monitor.IndividualCase();
    var caseAttribs = ["age",
                           "caseEntryDate","caseReportDate","diagnosisDate",
                           "workplaceText","probableSourceText","residenceText"];
    var caseColumns = caseAttribs.map(mapAttribs, {obj:individualCase, suffix:'_case', dropDownMaps:{}});
    

    var individualInstance = new Mojo.$.dss.vector.solutions.intervention.monitor.IndividualInstance();  
    var instanceAttribs = ["activelyDetected",
                       "admissionDate","anaemiaPatient","clinicalDiagnosis",
                       "detectedBy","diedInFacility","facilityVisit",
                       "patientCategory","pregnant","properlyRelease",
                       "referralReason","labTestDate","symptomComments",
                       "releaseDate","sampleType","malariaType",
                       "testSampleDate","treatment","treatmentMethod",
                       "treatmentStartDate"];
    
    var instanceColumns = instanceAttribs.map(mapAttribs, {obj:individualInstance, suffix:'_ins', dropDownMaps:{}});

    var person = new Mojo.$.dss.vector.solutions.Person();   
    var personAttribs = ["dateOfBirth","firstName","lastName","sex"];    
    var personColumns =  personAttribs.map(mapAttribs, {obj:person, suffix:'_per', dropDownMaps:{}});


    var calculations = ([
                          {
                           displayLabel:"Instances",
                           key:"instances",
                           type:"sqlinteger",
                           attributeName:"instances"
                          },
                          {
                           displayLabel:"Cases",
                           key:"cases",
                           type:"sqlinteger",
                           attributeName:"cases",
                          },
                          {
                           displayLabel:"Deaths",
                           key:"deaths",
                           type:"sqlinteger",
                           attributeName:"deaths",
                          },                       
                          {
                            displayLabel:"Incidence",
                            key:"incidence",
                            type:"sqlfloat",
                            attributeName:"incidence",
                            dropDownMap:{'100':'1000','1,000':'1000','10,000':'10000','100,000':'100000'}
                          },
                          {
                            displayLabel:"CFR",
                            key:"cfr",
                            type:"sqlfloat",
                            attributeName:"cfr",
                            dropDownMap:{'100':'1000','1,000':'1000','10,000':'10000','100,000':'100000'}
                          },

                         ]);
    
    var symptomsColumns = orderedGrids.symptoms.options.map(mapMo, orderedGrids.symptoms);
     
    var selectableGroups = [
              {title:"Case", values:caseColumns, group:"c", klass:individualCase.CLASS},
              {title:"Patient", values:personColumns, group:"c", klass:individualCase.CLASS},
              {title:"Instance", values:instanceColumns, group:"c", klass:individualCase.CLASS},
              {title:"Calculations", values:calculations, group:"c", klass:individualCase.CLASS},
              {title:"Symptoms", values:symptomsColumns, group:"c", klass:Mojo.$.dss.vector.solutions.surveillance.IndividualCaseSymptom.CLASS}
    ];

    var query = new MDSS.QueryIndividualCases(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<textarea id="debug_xml" cols="40" rows="40" style="width:1280px"> </textarea>

<jsp:include page="../templates/footer.jsp"></jsp:include>
