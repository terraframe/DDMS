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
  String[] mosquitoTypes = new String[]{ SurveyPointDTO.CLASS, HouseholdDTO.CLASS, SurveyedPersonDTO.CLASS, ITNInstanceDTO.CLASS};
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
    surveyPointColumns =   surveyPointAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:surveyPoint, suffix:'_collection',dropDownMaps:surveyPointMaps});

 //   var householdMaps = {<%=(String) request.getAttribute("householdMaps")%>};
 //   var household = new dss.vector.solutions.intervention.monitor.SurveyHousehold;
 //   var householdAttribs = ["mosquitoId","species","identMethod","sex","parasite","testMethod","infected","numberTested","numberPositive"];
 //   householdColumns =   householdAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:household, suffix:'_infection',dropDownMaps:householdMaps});

    
    var surveyedPersonMaps = {<%=(String) request.getAttribute("surveyedPersonMap")%>};
    var person = new dss.vector.solutions.intervention.monitor.SurveyedPerson;
    var personAttribs = ["anaemiaTreatment","bloodslideDetail","bloodslideReason","bloodslideResult",
                        "dob","fever","haemoglobin","haemoglobinMeasured","headOfHousehold",
                        "immuneCompromised","iron","malaria","malariaConformationTechnique","payment",
                        "performedBloodslide","performedRDT","personId","pregnant","rdtDetail","rdtResult",
                        "rdtTreatment","sex"];
    personColumns =   personAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_pooledInfection',dropDownMaps:surveyedPersonMaps});

    
    var netMaps = {<%=(String) request.getAttribute("itnMap")%>};
    var net = new dss.vector.solutions.intervention.monitor.ITNInstance;
    var netAttribs = ["damaged","hanging","household","monthRecieved","monthRetreated","netBrand","netId","notUsedForSleeping",
                      "obtained","price","purpose","purposeComments","retreated","steptUnderNet","washFrequency","washPeriod",
                      "yearRecived","yearRetreated"];
    netColumns =   netAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:net, suffix:'net',dropDownMaps:netMaps});

    var selectableGroups = [
                {title:"Survey_Point", values:surveyPointColumns, group:"sp", klass:surveyPoint.CLASS},
               // {title:"Household", values:householdColumns, group:"sp", klass:surveyPoint.CLASS},
                {title:"Person", values:personColumns, group:"sp", klass:surveyPoint.CLASS},
                {title:"ITN", values:netColumns, group:"sp", klass:surveyPoint.CLASS},
        ];

    var query = new MDSS.QuerySurvey(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>