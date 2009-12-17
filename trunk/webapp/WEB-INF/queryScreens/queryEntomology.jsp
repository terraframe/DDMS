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
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%><c:set var="page_title" value="Query_Entomology"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};


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

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;    

    var mosquito = new Mojo.$.dss.vector.solutions.entomology.Mosquito();
    var mosquitoView = new Mojo.$.dss.vector.solutions.entomology.MosquitoView();
    var mosquitoGroup = new  Mojo.$.dss.vector.solutions.entomology.UninterestingSpecieGroup();
    var mosquitoCollection = new Mojo.$.dss.vector.solutions.entomology.MosquitoCollection();

    var collectionAttribs = ["collectionId","dateCollected"];
    var collectionColumns = [];


    collectionColumns =   collectionAttribs.map(mapAttribs, {obj:mosquitoCollection, suffix:'_group',dropDownMaps:ddMaps});
    var groupAttribs = ["sampleId","specie","identificationMethod","quantity"];
    var groupColumns =  collectionColumns.concat(groupAttribs.map(mapAttribs, {obj:mosquitoGroup, suffix:'_group',dropDownMaps:ddMaps}));

    collectionColumns =   collectionAttribs.map(mapAttribs, {obj:mosquitoCollection, suffix:'_individual',dropDownMaps:ddMaps});
    var mosquitoAttribs = ["sampleId","specie","identificationMethod","sex","generation","isofemale","testDate"];
    var mosquitoColumns =  collectionColumns.concat(mosquitoAttribs.map(mapAttribs, {obj:mosquito, suffix:'_individual',dropDownMaps:ddMaps}));
    var assays = [];

    assays.push(mapAssayAttribs(infectivityColumns));
    assays.push(mapAssayAttribs(targetSiteColumns));
    assays.push(mapAssayAttribs(metabolicColumns));

    var query = new MDSS.QueryEntomology(groupColumns,mosquitoColumns, assays, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>
<textarea id="debug_xml" cols="40" rows="40" style="width:1280px"> </textarea>

<jsp:include page="../templates/footer.jsp"></jsp:include>
