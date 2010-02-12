<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<%@page import="dss.vector.solutions.intervention.monitor.SurveyPointDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.HouseholdDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.SurveyedPersonDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocationDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNInstanceDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>

<c:set var="page_title" value="Query_Indicator_Surveys"  scope="request" />

<jsp:include page="../templates/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/inlineError.jsp" flush="false"  />
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>


<%
  ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  String[] mosquitoTypes = new String[]{ SurveyPointDTO.CLASS, HouseholdDTO.CLASS, SurveyedPersonDTO.CLASS, SurveyedPersonTreatmentLocationDTO.CLASS,SurveyedPersonTreatmentDTO.CLASS, ITNInstanceDTO.CLASS};
  String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


  List<String> loadables = new ArrayList<String>();
  loadables.addAll(Arrays.asList(mosquitoTypes));
  loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>
<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

    //var queryList = <%= (String) request.getAttribute("queryList") %>;
    //var nets = <%= (String) request.getAttribute("nets") %>;
    //var rdtResults = <%= (String) request.getAttribute("rdtResults") %>


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

    var surveyPointMaps = {};
    var surveyPoint = new dss.vector.solutions.intervention.monitor.SurveyPoint;
    var surveyPointAttribs = ["geoEntity","surveyDate"];
    var surveyPointColumns =   surveyPointAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:surveyPoint, suffix:'_surveyPoint',dropDownMaps:surveyPointMaps});

    var householdMaps = {<%=(String) request.getAttribute("householdMap")%>};
    var household = new dss.vector.solutions.intervention.monitor.Household;
    var householdAttribs = ["householdName","urban","people","wall","wallInfo","roof","roofInfo",
                            "hasWindows","windowType","rooms","hasBeenSprayed","lastSprayed","nets"];
    var householdColumns =   householdAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:household, suffix:'_household',dropDownMaps:householdMaps});
    
    var surveyedPersonMaps = {<%=(String) request.getAttribute("surveyedPersonMap")%>};
    var person = new dss.vector.solutions.intervention.monitor.SurveyedPerson;
    var personAttribs = ["personId","headOfHousehold","dob","age","sex","pregnant","immuneCompromised",
                         "haemoglobinMeasured","haemoglobin", "anaemiaTreatment","iron",
                         "performedRDT","rdtResult","rdtDetail","rdtTreatment",
                         "performedBloodslide","bloodslideReason","bloodslideResult","bloodslideDetail",
                        "fever", "malaria","malariaConformationTechnique","payment"
                        ];
    var personColumns =   personAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_person',dropDownMaps:surveyedPersonMaps});

    personColumns = personColumns.concat(
        [                          {
          
          key:"prevalence",
          type:"sqlfloat",
          attributeName:"prevalence",
          isAggregate:true
        },
        ]);

    var netMaps = {<%=(String) request.getAttribute("itnMap")%>};
    var net = new dss.vector.solutions.intervention.monitor.ITNInstance;
    var netAttribs = ["netId","netBrand","monthReceived","yearReceived","receivedDate","obtained","price",
                      "retreated","monthRetreated","yearRetreated","retreatedDate",
                      "damaged","hanging","notUsedForSleeping","purpose","purposeComments",
                      "washFrequency","washPeriod","sleptUnderNet"
                     ];
    var netColumns =   netAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:net, suffix:'_net',dropDownMaps:netMaps});

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var locationColumns = orderedGrids.locations.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.locations);

    var treatmentColumns = orderedGrids.treatments.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.treatments);

    var selectableGroups = [
                {title:"Survey_Point", values:surveyPointColumns, group:"sp", klass:surveyPoint.CLASS},
                {title:"Household", values:householdColumns, group:"sp", klass:surveyPoint.CLASS},
                {title:"ITN", values:netColumns, group:"sp", klass:surveyPoint.CLASS},
                {title:"Person", values:personColumns, group:"sp", klass:surveyPoint.CLASS},
                {title:"Locations", values:locationColumns, group:"sp", klass:dss.vector.solutions.intervention.monitor.SurveyedPersonTreatmentLocation.CLASS},
                {title:"Treatments", values:treatmentColumns, group:"sp", klass:dss.vector.solutions.intervention.monitor.SurveyedPersonTreatment.CLASS}
        ];

    var query = new MDSS.QuerySurvey(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>