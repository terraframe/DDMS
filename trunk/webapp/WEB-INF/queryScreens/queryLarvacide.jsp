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
<%@page import="dss.vector.solutions.intervention.monitor.LarvacideDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.LarvacideInstanceDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>


<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.LarvacideInstanceViewDTO"%>
<%@page import="dss.vector.solutions.PersonViewDTO"%><c:set var="page_title" value="Query_Control_of_Immatures"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ LarvacideDTO.CLASS, LarvacideInstanceDTO.CLASS, PersonDTO.CLASS};
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

    var larvacideMaps = {<%=(String) request.getAttribute("larvacideMap")%>};
    
    var personMaps = {};

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var larvacide = new Mojo.$.dss.vector.solutions.intervention.monitor.Larvacide();

    var larvacideAttribs = ["geoEntity","geoDescription","startDate","completionDate","natureOfControl", "personCount"];
    <%
    Halp.setReadableAttributes(request, "larvacideAttribs", LarvacideDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("larvacideAttribs") %>);
    larvacideAttribs = Mojo.Iter.filter(larvacideAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var larvacideColumns =   larvacideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:larvacide, suffix:'_lar', dropDownMaps:larvacideMaps});

    var larvacideInstance = new Mojo.$.dss.vector.solutions.intervention.monitor.LarvacideInstance();
    
    var larvacideInstanceAttribs = ["controlMethod","target","treated","unit","unitsUsed"];
    <%
    Halp.setReadableAttributes(request, "larvacideInstanceAttribs", LarvacideInstanceViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("larvacideInstanceAttribs") %>);
    larvacideInstanceAttribs = Mojo.Iter.filter(larvacideInstanceAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var larvacideInstanceColumns =   larvacideInstanceAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:larvacideInstance, suffix:'_lar', dropDownMaps:larvacideMaps});

    var person = new Mojo.$.dss.vector.solutions.Person();
    
    var personAttribs = ["firstName","lastName"];
    <%
    Halp.setReadableAttributes(request, "personAttribs", PersonViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("personAttribs") %>);
    personAttribs = Mojo.Iter.filter(personAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var personColumns =  personAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:person, suffix:'_per', dropDownMaps:personMaps});

    var selectableGroups = [
              {title:"Spray", values:larvacideColumns, group:"l", klass:larvacide.CLASS},
              {title:"Details", values:larvacideInstanceColumns, group:"l", klass:larvacide.CLASS},
              {title:"Leader", values:personColumns, group:"l", klass:larvacide.CLASS}
    ];

    var query = new MDSS.QueryLarvacide(selectableGroups, queryList);
    query.render();

});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>




<jsp:include page="../templates/footer.jsp"></jsp:include>
