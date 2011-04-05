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
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
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
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionViewDTO"%>
<%@page import="dss.vector.solutions.util.ReadableAttributeViewDTO"%>
<%@page import="java.util.Set"%>
<%@page import="dss.vector.solutions.entomology.SearchMosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.geo.generated.CollectionSiteDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Mosquito_Collections"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS,SubCollectionDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var collectionMaps = {<%=(String) request.getAttribute("collectionMaps")%>};

    <%
      Halp.setReadableAttributes(request, "collectionAttribs", SearchMosquitoCollectionViewDTO.CLASS, requestIF);
    %>
    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection();
    var collectionAttribs = ["collectionId","collectionMethod","geoEntity","collectionDate","lifeStage","abundance"];
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
     
    
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_mc',dropDownMaps:collectionMaps});

    <%
	    Halp.setReadableAttributes(request, "subCollectionAttribs", SubCollectionViewDTO.CLASS, requestIF);
    %>    
    var subCollection = new dss.vector.solutions.entomology.SubCollection();
    var subCollectionAttribs = ["subCollectionId","identMethod","taxon","eggs","larvae","pupae","female","male","unknowns","total"];
    available = new MDSS.Set(<%= request.getAttribute("subCollectionAttribs") %>);
    subCollectionAttribs = Mojo.Iter.filter(subCollectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    
    subCollectionColumns =   subCollectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:subCollection, suffix:'_subMc',dropDownMaps:{}});


  var abundanceColumns = ["collectionMethod"].map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_ab',dropDownMaps:collectionMaps});

 //  abundanceColumns = abundanceColumns.concat( ["taxon"].map(MDSS.QueryBaseNew.mapAttribs, {obj:subCollection, suffix:'_ab',dropDownMaps:{}}));



   abundanceColumns = abundanceColumns.concat( [     
                                {
                                  displayLabel:subCollection.getTaxonMd().getDisplayLabel(),
                                  description:subCollection.getTaxonMd().getDescription(),
                                  key:"taxon",
                                  type:"sqlcharacter",
                                  attributeName:"taxon_displayLabel",
                                  isAggregate:false
                                },
                                {
                                  
                                  key:"mosquitoCount",
                                  type:"sqlinteger",
                                  attributeName:"mosquitoCount",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"collectionCount",
                                  type:"sqlinteger",
                                  attributeName:"collectionCount",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"subCollectionCount",
                                  type:"sqlinteger",
                                  attributeName:"subCollectionCount",
                                  isAggregate:true
                                },
                                {
                                                      
                                  key:"abundance_1",
                                  type:"sqlfloat",
                                  attributeName:"abundance_1",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"abundance_10",
                                  type:"sqlfloat",
                                  attributeName:"abundance_10",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"abundance_100",
                                  type:"sqlfloat",
                                  attributeName:"abundance_100",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"abundance_1000",
                                  type:"sqlfloat",
                                  attributeName:"abundance_1000",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"abundance_subcol_1",
                                  type:"sqlfloat",
                                  attributeName:"abundance_subcol_1",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"abundance_subcol_10",
                                  type:"sqlfloat",
                                  attributeName:"abundance_subcol_10",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"abundance_subcol_100",
                                  type:"sqlfloat",
                                  attributeName:"abundance_subcol_100",
                                  isAggregate:true
                                },
                                {
                                  
                                  key:"abundance_subcol_1000",
                                  type:"sqlfloat",
                                  attributeName:"abundance_subcol_1000",
                                  isAggregate:true
                                },
                             
                             ]);

    
    var abundanceOnly = abundanceColumns.slice(-8);
   

    var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"mc", klass:mosquitoCollection.CLASS},
                {title:"SubCollection", values:subCollectionColumns, group:"mc", klass:subCollection.CLASS},
                {title:"Abundance", values:abundanceColumns, group:"mc", klass:subCollection.CLASS}
        ];

    var query = new MDSS.QueryMosquitoCollections(selectableGroups, queryList);
    query.render();

    var picker = query.getGeoPicker();
    picker.addExtraUniversal('<%= CollectionSiteDTO.CLASS %>');

    var dm = query.getDependencyManager();

    var allCollections = subCollectionColumns.concat(collectionColumns);
    dm.excludes({
      independent: allCollections,
      dependent:abundanceColumns,
      type: MDSS.Dependent.CHECKED,
      bidirectional: true
    });
    dm.includes({
      independent: ['collectionMethod_ab'],
      dependent:abundanceColumns,
      type: MDSS.Dependent.UNCHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: ['taxon'],
      dependent: abundanceOnly,
      type: MDSS.Dependent.UNCHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: abundanceOnly,
      dependent: ['taxon','collectionMethod_ab'],
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: ['collectionCount', 'subCollectionCount'],
      dependent:['collectionMethod_ab'],
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });

    Mojo.GLOBAL.query = query;
});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>
<jsp:include page="../templates/footer.jsp"></jsp:include>
