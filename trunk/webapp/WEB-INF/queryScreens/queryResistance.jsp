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
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.dataaccess.MdAttributeConcreteDAOIF"%>
<%@page import="com.terraframe.mojo.dataaccess.metadata.MdAttributeConcreteDAO"%>
<%@page import="com.terraframe.mojo.dataaccess.MdBusinessDAOIF"%>
<%@page import="com.terraframe.mojo.dataaccess.metadata.MdBusinessDAO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.general.InsecticideDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>




<%@page import="com.terraframe.mojo.business.BusinessDTO"%><c:set var="page_title" value="Query_Resistance"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS, AdultDiscriminatingDoseAssayDTO.CLASS, LarvaeDiscriminatingDoseAssayDTO.CLASS, EfficacyAssayDTO.CLASS, KnockDownAssayDTO.CLASS, InsecticideDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, LayerViewDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    adultMaps = {<%=(String) request.getAttribute("adultMap")%>};

    larvaeMaps = {<%=(String) request.getAttribute("larvaeMap")%>};

    knockDownMaps = {<%=(String) request.getAttribute("knockDownMap")%>};

    insecticideMaps = {<%=(String) request.getAttribute("insecticideMap")%>};



    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection;

    var collectionAttribs = ["collectionId","collectionDate","collectionMethod"];
    var collectionColumns = [];

    var insectcide = new dss.vector.solutions.general.Insecticide;
    var insectcideAttribs = ["activeIngredient","amount","units"];

    var abstractAssayAttribs = ["specie","identificationMethod","generation","isofemale","exposureTime","testDate","quantityTested"];
    var abstractCalculations = {
        key:"resistance_result",
    	attributeName:"resistance_result",
      	type:"sqlcharacter",
      	
     };

    var adultAssay = new  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay();
    var adultAttribs = abstractAssayAttribs.concat(["quantityLive","quantityDead","sex","fed","gravid","holdingTime","mortality","kd50","kd95","controlTestMortality"]);
    var adultCalulations = [];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_adult', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_adult', dropDownMaps:insecticideMaps}));
    var adultColumns =  collectionColumns.concat(adultAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:adultAssay, suffix:'_adult', dropDownMaps:adultMaps}));
    adultColumns.push(abstractCalculations);

    var larvaeAssay = new  dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay();
    var larvaeAttribs = abstractAssayAttribs.concat(["quantityLive","quantityDead","startPoint","endPoint","controlTestMortality","lt50","lt95","mortality"]);
    var larvaeCalculations = ["quanityAlive","percentMortality"];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_larvae', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_larvae', dropDownMaps:insecticideMaps}));
    var larvaeColumns =  collectionColumns.concat(larvaeAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:larvaeAssay, suffix:'_larvae', dropDownMaps:larvaeMaps}));
    larvaeColumns.push(abstractCalculations);

    var knockDownAssay = new  dss.vector.solutions.entomology.assay.KnockDownAssay();
    var knockDownAttribs = abstractAssayAttribs.concat(["sex","fed","gravid","kd50","kd95"]);
    var knockDownCalulations = [];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_knockDown', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_knockDown', dropDownMaps:insecticideMaps}));
    var knockDownColumns =  collectionColumns.concat(knockDownAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:knockDownAssay, suffix:'_knockDown', dropDownMaps:knockDownMaps}));
    knockDownColumns.push(abstractCalculations);

    var pooledAssay = new  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay();
    var pooledAttribs = abstractAssayAttribs;
    var pooledCalulations = [];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_collection', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_collection', dropDownMaps:insecticideMaps}));
    var pooledColumns =  collectionColumns.concat(pooledAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:pooledAssay, suffix:'_collection', dropDownMaps:adultMaps, type:'dss.vector.solutions.entomology.assay.CollectionAssay'}));
    pooledColumns.push(abstractCalculations);

    pooledColumns = pooledColumns.map(function(attrib){attrib.type = attrib.type.replace('AdultDiscriminatingDose','Collection');return attrib;});

    var selectableGroups = [
              {title:"Adult_DDA", values:adultColumns, group:"adult", klass:dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS},
              {title:"Larvae_DDA", values:larvaeColumns, group:"lavae", klass:dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS},
              {title:"Knock_Down_Assay", values:knockDownColumns, group:"knockDown", klass:dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS},
              {title:"Pooled_Assays", values:pooledColumns, group:"pooled", klass:dss.vector.solutions.entomology.assay.CollectionAssay.CLASS},
    ];

    var query = new MDSS.QueryResistance(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>
