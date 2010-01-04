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
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
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
<%@page import="dss.vector.solutions.entomology.SubCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%><c:set var="page_title" value="Query_Mosquito_Collections"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS,SubCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var collectionMaps = {<%=(String) request.getAttribute("collectionMaps")%>};

    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection;
    var collectionAttribs = ["collectionId","collectionMethod","geoEntity","collectionDate","lifeStage","abundance"];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_mc',dropDownMaps:collectionMaps});
    

    var subCollection = new dss.vector.solutions.entomology.SubCollection;
    var subCollectionAttribs = ["subCollectionId","identMethod","taxon","eggs","larvae","pupae","female","male","unknowns","total"];
    subCollectionColumns =   subCollectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:subCollection, suffix:'_mc',dropDownMaps:{}});


    subCollectionColumns = subCollectionColumns.concat([
                                                       {
                                                         displayLabel:"Abundance x 1",
                                                         key:"abundance_1",
                                                         type:"sqlfloat",
                                                         attributeName:"abundance_1",
                                                         isAggregate:true
                                                       },
                                                       {
                                                         displayLabel:"Abundance x 10",
                                                         key:"abundance_10",
                                                         type:"sqlfloat",
                                                         attributeName:"abundance_10",
                                                         isAggregate:true
                                                       },
                                                       {
                                                         displayLabel:"Abundance x 100",
                                                         key:"abundance_100",
                                                         type:"sqlfloat",
                                                         attributeName:"abundance_100",
                                                         isAggregate:true
                                                       },
                                                       {
                                                         displayLabel:"Abundance x 1000",
                                                         key:"abundance_1000",
                                                         type:"sqlfloat",
                                                         attributeName:"abundance_1000",
                                                         isAggregate:true
                                                       },
                                                    
                                                    ]);

    var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"ipt", klass:mosquitoCollection.CLASS},
                {title:"SubCollection", values:subCollectionColumns, group:"ipt", klass:subCollection.CLASS},
                //{title:"Calcuations", values:subCollectionColumns, group:"ipt", klass:subCollection.CLASS}
        ];

    var query = new MDSS.QueryMosquitoCollections(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>
<jsp:include page="../templates/footer.jsp"></jsp:include>
