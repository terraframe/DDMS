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
<%@page import="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNCommunityNetDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroupDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>

<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_ITN_Community_Distribution"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ ITNCommunityDistributionDTO.CLASS, ITNCommunityDistributionViewDTO.CLASS, ITNCommunityTargetGroupDTO.CLASS, ITNCommunityNetDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, LayerViewDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var itnMaps = {<%=(String) request.getAttribute("itnMap")%>};

    var ITN = new dss.vector.solutions.intervention.monitor.ITNCommunityDistribution;
  
    var ITNAttribs = ["startDate","endDate","distributionLocation","agentFirstName","agentSurname","entryType","hasBatchNumber","batchNumber","pretreated",
                      "householdAddress","householdName","householdSurname","residents",
                      "itnsReceived","retrieved","numberRetrieved","sold", "currencyReceived"];
    <%
    Halp.setReadableAttributes(request, "ITNAttribs", ITNCommunityDistributionViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("ITNAttribs") %>);
    ITNAttribs = Mojo.Iter.filter(ITNAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    
    var ITNColumns =   ITNAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:ITN, suffix:'_itn', dropDownMaps:itnMaps});
    var selectableGroups = [{title:"ITN", values:ITNColumns, group:"itn", klass:Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.CLASS}];

    if(available.contains('<%= ITNCommunityDistributionViewDTO.DISPLAYNETS %>'))
    {
      var netsColumns = orderedGrids.nets.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.nets);
      selectableGroups.push({title:"Nets", values:netsColumns, group:"itn", klass:Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityNet.CLASS});
    }

    if(available.contains('<%= ITNCommunityDistributionViewDTO.DISPLAYTARGETGROUPS %>'))
    {
    	var targetGroupsColumns = orderedGrids.targetGroups.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.targetGroups);
      selectableGroups.push({title:"TargetGroups", values:targetGroupsColumns, group:"itn", klass:Mojo.$.dss.vector.solutions.intervention.monitor.ITNCommunityTargetGroup.CLASS});
    }
    
    var query = new MDSS.QueryITNCommunityDistribution(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
