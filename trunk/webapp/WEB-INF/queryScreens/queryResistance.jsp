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
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
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
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.general.InsecticideDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>




<%@page import="com.runwaysdk.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.entomology.SearchMosquitoCollectionViewDTO"%><c:set var="page_title" value="Query_Resistance"  scope="request"/>

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
    <%
    Halp.setReadableAttributes(request, "collectionAttribs", SearchMosquitoCollectionViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);    
    var collectionColumns = [];

    var insectcide = new dss.vector.solutions.general.Insecticide;
    var insectcideAttribs = ["activeIngredient","amount","units"];
    <%
    Halp.setReadableAttributes(request, "insectcideAttribs", InsecticideDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("insectcideAttribs") %>);
    insectcideAttribs = Mojo.Iter.filter(insectcideAttribs, function(attrib){
      return this.contains(attrib);
    }, available);    
    var collectionColumns = [];    

    var adultAssay = new  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay();
    var adultAttribs = ["specie","identificationMethod","generation","sex","isofemale","testDate","exposureTime",
                        "holdingTime","quantityTested","fed","gravid","quantityLive","quantityDead","controlTestMortality",
                        "mortality","kd50","kd95"];
    <%
    Halp.setReadableAttributes(request, "adultAttribs", AdultDiscriminatingDoseAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("adultAttribs") %>);
    adultAttribs = Mojo.Iter.filter(adultAttribs, function(attrib){
      return this.contains(attrib);
    }, available);   
       
    var adultCalulations = [];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_adult', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_adult', dropDownMaps:insecticideMaps}));
    var adultColumns =  collectionColumns.concat(adultAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:adultAssay, suffix:'_adult', dropDownMaps:adultMaps}));
    adultColumns.push({
        key:"resistance_result_adult",
        attributeName:"resistance_result",
        type:"sqlcharacter",
      	displayLabel: MDSS.Localized.resistance_result
       });

    var larvaeAssay = new  dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay();
    var larvaeAttribs = ["specie","identificationMethod","generation","isofemale","startPoint","endPoint","testDate","exposureTime",
                         "quantityTested","quantityLive","quantityDead","controlTestMortality","mortality","lt50","lt95"];
    <%
    Halp.setReadableAttributes(request, "larvaeAttribs", LarvaeDiscriminatingDoseAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("larvaeAttribs") %>);
    larvaeAttribs = Mojo.Iter.filter(larvaeAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var larvaeCalculations = ["quanityAlive","percentMortality"];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_larvae', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_larvae', dropDownMaps:insecticideMaps}));
    var larvaeColumns =  collectionColumns.concat(larvaeAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:larvaeAssay, suffix:'_larvae', dropDownMaps:larvaeMaps}));
    larvaeColumns.push({
        key:"resistance_result_larvae",
        attributeName:"resistance_result",
        type:"sqlcharacter",
        displayLabel: MDSS.Localized.resistance_result
       });

    var knockDownAssay = new  dss.vector.solutions.entomology.assay.KnockDownAssay();
    var knockDownAttribs = ["specie","identificationMethod","generation","isofemale","sex","testDate","exposureTime",
                            "quantityTested","fed","gravid","quantityLive","quantityDead","kd50","kd95"];
    <%
    Halp.setReadableAttributes(request, "knockDownAttribs", KnockDownAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("knockDownAttribs") %>);
    knockDownAttribs = Mojo.Iter.filter(knockDownAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var knockDownCalulations = [];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_knockDown', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_knockDown', dropDownMaps:insecticideMaps}));
    var knockDownColumns =  collectionColumns.concat(knockDownAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:knockDownAssay, suffix:'_knockDown', dropDownMaps:knockDownMaps}));
    knockDownColumns.push({
        key:"resistance_result_knockDown",
        attributeName:"resistance_result",
        type:"sqlcharacter",
        displayLabel: MDSS.Localized.resistance_result
       });

    var pooledAssay = new  dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay();
    var pooledAttribs = ["specie","identificationMethod","generation","isofemale","testDate","exposureTime","quantityTested"];
    <%
    Halp.setReadableAttributes(request, "pooledAttribs", AdultDiscriminatingDoseAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("pooledAttribs") %>);
    pooledAttribs = Mojo.Iter.filter(pooledAttribs, function(attrib){
      return this.contains(attrib);
    }, available);   
    
    var pooledCalulations = [];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_collection', dropDownMaps:adultMaps});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_collection', dropDownMaps:insecticideMaps}));
    var pooledColumns =  collectionColumns.concat(pooledAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:pooledAssay, suffix:'_collection', dropDownMaps:adultMaps, type:'dss.vector.solutions.entomology.assay.CollectionAssay'}));
    pooledColumns.push({
        key:"resistance_result_collection",
        attributeName:"resistance_result",
        type:"sqlcharacter",
        displayLabel: MDSS.Localized.resistance_result
       });

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
