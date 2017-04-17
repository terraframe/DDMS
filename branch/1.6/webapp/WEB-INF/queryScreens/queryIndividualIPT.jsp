<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTCaseViewDTO"%>
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
<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>




<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Individual_IPT"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ IndividualIPTViewDTO.CLASS, IndividualIPTDTO.CLASS, PersonDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    var iptMaps = {<%=(String) request.getAttribute("iptMap")%>};
    
    var personMaps = {<%=(String) request.getAttribute("iptMap")%>};

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var individualIPT = new Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT();

    var iIPTAttribs = ["facility","serviceDate",
                       "doseNumber","doseType","isANCVisit",
                       "numberOfReceivedITNs","patientType","receivedITN",
                       "receivedSupplement","visitNumber","administratorName","administratorSurname"];
    <%
    Halp.setReadableAttributes(request, "iIPTAttribs", IndividualIPTViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("iIPTAttribs") %>);
    iIPTAttribs = Mojo.Iter.filter(iIPTAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    
    var iIPTColumns =   iIPTAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:individualIPT, suffix:'_ipt', dropDownMaps:iptMaps});

    var person = new Mojo.$.dss.vector.solutions.Person();
    
    var personAttribs = ["dateOfBirth","firstName","lastName","sex","age","residentialGeoEntity","residentialInformation","workGeoEntity","workInformation"];
    <%
    Halp.setReadableAttributes(request, "personAttribs", PersonViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("personAttribs") %>);
    personAttribs = Mojo.Iter.filter(personAttribs, function(attrib){
        if(attrib === 'workGeoEntity')
        {
          return this.contains('<%= PersonViewDTO.WORKGEOID%>');
        }
        else if(attrib === 'residentialGeoEntity')
        {
          return this.contains('<%= PersonViewDTO.RESIDENTIALGEOID%>');
        }
        else
        {
          return this.contains(attrib);
        }
    }, available);    
    
    var personColumns =  personAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_per', dropDownMaps:personMaps});
    MDSS.QueryBase.filterFunctions(personColumns, ['age'], MDSS.QueryXML.F_SET1);
    
    var caseAttribs = ["<%= IndividualIPTCaseViewDTO.PATIENT %>"];
    
    <%
    Halp.setReadableAttributes(request, "caseAttribs", IndividualIPTCaseViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("caseAttribs") %>);
    caseAttribs = Mojo.Iter.filter(caseAttribs, function(attrib){
      return this.contains(attrib);
    }, available);

    var hasPatient = available.contains('<%= IndividualIPTCaseViewDTO.PATIENT %>');
    
    var selectableGroups = [{title:"IPT", values:iIPTColumns, group:"ipt", klass:individualIPT.CLASS}];
    
    if(hasPatient)
    {    
      selectableGroups.push({title:"Recipient", values:personColumns, group:"ipt", klass:individualIPT.CLASS});
    }


    var query = new MDSS.QueryIndividualIPT(selectableGroups, queryList);
    query.render();

    var picker = query.getGeoPicker();
    picker.setPolitical(true);
    picker.setSprayTargetAllowed(false);
    picker.setPopulated(false);
    picker.addExtraUniversal('<%= HealthFacilityDTO.CLASS %>');

});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>




<jsp:include page="../templates/footer.jsp"></jsp:include>
