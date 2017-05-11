<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dss.vector.solutions.ontology.TermTermDisplayLabelDTO"%>
<%@page import="dss.vector.solutions.query.QueryConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr"%>
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
<%@page	import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionViewDTO"%>
<%@page import="dss.vector.solutions.util.ReadableAttributeViewDTO"%>
<%@page import="java.util.Set"%>
<%@page	import="dss.vector.solutions.entomology.SearchMosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.geo.generated.CollectionSiteDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%>
<%@page import="dss.vector.solutions.irs.InsecticideBrandDTO"%>

<c:set var="page_title" value="Query_Mosquito_Collections" scope="request" />

<jsp:include page="../templates/header.jsp" />
<jsp:include page="/WEB-INF/inlineError.jsp" />
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false" />
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS,SubCollectionDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, 
        QueryController.CLASS, QueryBuilderDTO.CLASS, InsecticideBrandDTO.CLASS};


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

    var collectionMaps = {<%=(String) request.getAttribute("collectionMaps")%>};

    <%
      Halp.setReadableAttributes(request, "collectionAttribs", SearchMosquitoCollectionViewDTO.CLASS, requestIF);
    %>
    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection();
    var collectionAttribs = ["collectionId","collectionMethod", "geoEntity","collectionDate", "lifeStage","abundance", "collectionRound","collectionType", "dateLastSprayed","wallType", /*"insecticideBrand",*/, "numberOfHumanOccupants", "numberOfAnimalOccupants", "numberOfLLINs"];
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
     
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_mc',dropDownMaps:collectionMaps});

    <%
	    Halp.setReadableAttributes(request, "subCollectionAttribs", SubCollectionViewDTO.CLASS, requestIF);
    %>    
    var subCollection = new dss.vector.solutions.entomology.SubCollection();
    var subCollectionAttribs = ["subCollectionId","identMethod","taxon","eggs","larvae","pupae","femalesUnfed","femalesFed","femalesHalfGravid","femalesGravid","femalesUnknown","femalesTotal","male","unknowns","total","disected","parous"];
    available = new MDSS.Set(<%= request.getAttribute("subCollectionAttribs") %>);
    subCollectionAttribs = Mojo.Iter.filter(subCollectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    
    subCollectionColumns =   subCollectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:subCollection, suffix:'_subMc',dropDownMaps:{}});
    
    subCollectionColumns.push({
        key:'<%= QueryConstants.PERCENT_PAROUS %>',
        attributeName:'<%= QueryConstants.PERCENT_PAROUS %>',
        displayLabel:MDSS.localize('percent_parous'),
        description:MDSS.localize('percent_parous'),
        type:'sqldouble',
        isAggregate:true
      });
    
    
    var insectcide = new Mojo.$.dss.vector.solutions.irs.InsecticideBrand();

    var insecticideAttrs = [
                             "productName",
                             "activeIngredient",
                             "concentrationQuantifier",
                             "concentrationQualifier",
                             "insecticideUse",
                             "useDetail",
                             "unitsPerApplication",
                             "unitQuantifier",
                             "unitQualifier",
                             ];
    <%
      Halp.setReadableAttributes(request, "insecticideAttrs", InsecticideBrandDTO.CLASS, requestIF);
    %>
    
    var available = new MDSS.Set(<%= request.getAttribute("insecticideAttrs") %>);
    insecticideAttrs = Mojo.Iter.filter(insecticideAttrs, function(attrib){
        return this.contains(attrib);
    }, available);

    var insecticideBrandMap = {<%=(String) request.getAttribute("insecticideBrandMap")%>};
    var insectcideColumns =   insecticideAttrs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_eff', dropDownMaps:insecticideBrandMap});
    
    
   var abundanceColumns = [/* {
     displayLabel:mosquitoCollection.getCollectionMethodMd().getDisplayLabel(),
     description:mosquitoCollection.getCollectionMethodMd().getDescription(),
     dtoType:"com.runwaysdk.transport.attributes.AttributeReferenceDTO",
     key:"collectionMethod_ab",
     type:"dss.vector.solutions.entomology.MosquitoCollection",
     attributeName:"collectionMethod",
     isAggregate:false,
     isTerm:true,
     isTermAggregate:true
   } */];

   abundanceColumns = abundanceColumns.concat( [     
                                {
                                  displayLabel:subCollection.getTaxonMd().getDisplayLabel(),
                                  description:subCollection.getTaxonMd().getDescription(),
                                  key:"taxon",
                                  type:"sqlcharacter",
                                  attributeName:"taxon_displayLabel",
                                  isAggregate:false,
                                  isTerm:true,
                                  isTermAggregate:false
                                },
                                /* {
                                  displayLabel:mosquitoCollection.getCollectionRoundMd().getDisplayLabel(),
                                  description:mosquitoCollection.getCollectionRoundMd().getDescription(),
                                  key:"collectionRound",
                                  type:"sqlcharacter",
                                  attributeName:"collectionRound_displayLabel",
                                  isAggregate:false,
                                  isTerm:true,
                                  isTermAggregate:false
                                }, */
                                /* {
                                  displayLabel:mosquitoCollection.getCollectionTypeMd().getDisplayLabel(),
                                  description:mosquitoCollection.getCollectionTypeMd().getDescription(),
                                  key:"collectionType",
                                  type:"sqlcharacter",
                                  attributeName:"collectionType_displayLabel",
                                  isAggregate:false,
                                  isTerm:true,
                                  isTermAggregate:false
                                }, */
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

    
    var abundanceOnly = abundanceColumns.slice(-8); /* the last 8 in the array */
   

    var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"mc", klass:mosquitoCollection.CLASS},
                {title:"SubCollection", values:subCollectionColumns, group:"mc", klass:subCollection.CLASS},
                {title:"Insecticide_Detail", values:insectcideColumns, group:"mc", klass:insectcide.CLASS},
                {title:"Abundance", values:abundanceColumns, group:"mc", klass:subCollection.CLASS}
        ];

    var query = new MDSS.QueryMosquitoCollections(selectableGroups, queryList);
    query.render();

    var picker = query.getGeoPicker();      
    picker.setPolitical(true);
    picker.setSprayTargetAllowed(false);
    picker.addExtraUniversal('<%=CollectionSiteDTO.CLASS%>');
    picker.addExtraUniversal('<%=SentinelSiteDTO.CLASS%>');

    var dm = query.getDependencyManager();

    var allCollections = subCollectionColumns.concat(collectionColumns);
    dm.excludes({
      independent: subCollectionColumns,
      dependent:abundanceColumns,
      type: MDSS.Dependent.CHECKED,
      bidirectional: true
    });
    dm.includes({
      independent: ['collectionMethod_mc'],
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
      dependent: ['taxon','collectionMethod_mc'],
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: ['collectionCount', 'subCollectionCount', "mosquitoCount"],
      dependent:['collectionMethod_mc', "taxon"],
      type: MDSS.Dependent.CHECKED,
      bidirectional: false
    });
    dm.includes({
      independent: ['taxon'],
      dependent: ['collectionCount', 'subCollectionCount', "mosquitoCount"],
      type: MDSS.Dependent.UNCHECKED,
      bidirectional: false
    });

    Mojo.GLOBAL.query = query;
});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>
<jsp:include page="../templates/footer.jsp"></jsp:include>
