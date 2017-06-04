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
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
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
<%@page import="dss.vector.solutions.intervention.monitor.AggregatedIPTDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTANCVisitDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTDoseDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTTreatmentDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTPatientsDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="com.runwaysdk.business.BusinessDTO"%>


<%@page import="dss.vector.solutions.geo.generated.HealthFacilityDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.AggregatedIPTViewDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Aggregated_IPT"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ AggregatedIPTDTO.CLASS, IPTANCVisitDTO.CLASS, IPTDoseDTO.CLASS, IPTTreatmentDTO.CLASS, IPTPatientsDTO.CLASS};
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

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    <%
      Halp.setReadableAttributes(request, "aIPTAttribs", AggregatedIPTViewDTO.CLASS, requestIF);
    %>    

    var aggreatedIPT = new Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT();
    
    var aIPTAttribs = ["geoEntity","startDate","endDate","numberNatalCare","numberPregnant","numberPregnantITN","numberPregnantIron","totalITN"];
    var available = new MDSS.Set(<%= request.getAttribute("aIPTAttribs") %>);
    aIPTAttribs = Mojo.Iter.filter(aIPTAttribs, function(attrib){

      if(attrib === 'geoEntity')
      {
        return this.contains('<%= AggregatedIPTViewDTO.GEOID %>');
      }
      else
      {
        return this.contains(attrib);
      }
    }, available);
    
    var aIPTColumns =   aIPTAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:aggreatedIPT, suffix:'_aipt', dropDownMaps:iptMaps});

   var selectableGroups = [{title:"IPT", values:aIPTColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS}];
   
   if(available.contains('<%= AggregatedIPTViewDTO.DISPLAYDOSE %>'))
   {
	   var dosesColumns = orderedGrids.doses.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.doses);
	   selectableGroups.push({title:"Doses", values:dosesColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTDose.CLASS});
   }

   if(available.contains('<%= AggregatedIPTViewDTO.DISPLAYPATIENTS %>'))
   {
     var patientsColumns = orderedGrids.patients.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.patients);
	   selectableGroups.push({title:"Patients", values:patientsColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTPatients.CLASS});
   }

   if(available.contains('<%= AggregatedIPTViewDTO.DISPLAYTREATMENTS %>'))
   {
     var treatmentsColumns = orderedGrids.treatments.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.treatments);
	   selectableGroups.push({title:"Treatments", values:treatmentsColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS});
   }

   if(available.contains('<%= AggregatedIPTViewDTO.DISPLAYVISITS %>'))
   {
     var visitsColumns = orderedGrids.visits.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.visits);
	   selectableGroups.push({title:"Visits", values:visitsColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS});
   }
   
    var query = new MDSS.QueryAggreatedIPT(selectableGroups, queryList);

    var picker = query.getGeoPicker();
    picker.setPolitical(true);
    picker.setSprayTargetAllowed(false);
    picker.setPopulated(false);
    picker.addExtraUniversal('<%= HealthFacilityDTO.CLASS %>');
    
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>
