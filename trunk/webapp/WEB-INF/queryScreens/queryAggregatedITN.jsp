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
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF"%>
<%@page import="com.runwaysdk.dataaccess.metadata.MdAttributeConcreteDAO"%>
<%@page import="com.runwaysdk.dataaccess.MdBusinessDAOIF"%>
<%@page import="com.runwaysdk.dataaccess.metadata.MdBusinessDAO"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNDataDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNDataViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNNetDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNTargetGroupDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNServiceDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="com.runwaysdk.business.BusinessDTO"%>


<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Aggregated_ITN_Data_Distribution"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ ITNDataDTO.CLASS, ITNDataViewDTO.CLASS, ITNTargetGroupDTO.CLASS, ITNNetDTO.CLASS, ITNServiceDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
    loadables.add(dss.vector.solutions.geo.generated.HealthFacilityDTO.CLASS);
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

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    
    var aggreatedITN = new Mojo.$.dss.vector.solutions.intervention.monitor.ITNData();
    
    var aITNAttribs = ["geoEntity","startDate","endDate","batchNumber","numberSold","currencyReceived",
                       "numberDistributed","receivedForCommunityResponse","receivedForTargetGroups"];
    
    <%
      Halp.setReadableAttributes(request, "aITNAttribs", ITNDataViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("aITNAttribs") %>);
    aITNAttribs = Mojo.Iter.filter(aITNAttribs, function(attrib){
        if(attrib === 'geoEntity')
        {
          return this.contains('<%= ITNDataViewDTO.GEOID %>');
        }
        else
        {
          return this.contains(attrib);
        }
    }, available);

    
    var aITNColumns =   aITNAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:aggreatedITN, suffix:'_aitn', dropDownMaps:{}});

    selectableGroups = [{title:"ITN", values:aITNColumns, group:"itn", klass:Mojo.$.dss.vector.solutions.intervention.monitor.ITNData.CLASS}];

    if(available.contains('<%= ITNDataViewDTO.DISPLAYNETS %>'))
    {
      var netsColumns = orderedGrids.nets.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.nets);
      selectableGroups.push({title:"Nets", values:netsColumns, group:"itn", klass:Mojo.$.dss.vector.solutions.intervention.monitor.ITNNet.CLASS});
    }

    if(available.contains('<%= ITNDataViewDTO.DISPLAYSERVICES %>'))
    {
      var servicesColumns = orderedGrids.services.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.services);
      selectableGroups.push({title:"Services", values:servicesColumns, group:"itn", klass:Mojo.$.dss.vector.solutions.intervention.monitor.ITNService.CLASS});
    }

    if(available.contains('<%= ITNDataViewDTO.DISPLAYTARGETGROUPS %>'))
    {
      var targetGroupsColumns = orderedGrids.targetGroups.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.targetGroups);
      selectableGroups.push({title:"TargetGroups", values:targetGroupsColumns, group:"itn", klass:Mojo.$.dss.vector.solutions.intervention.monitor.ITNTargetGroup.CLASS});
    }

    var query = new MDSS.QueryAggreatedITN(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>
