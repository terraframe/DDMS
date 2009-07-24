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
<%@page import="dss.vector.solutions.query.ThematicLayerDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoDTO"%>
<%@page import="dss.vector.solutions.mo.SpecieDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoViewDTO"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.assay.biochemical.MetabolicAssayTestResult"%>
<%@page import="dss.vector.solutions.entomology.UninterestingSpecieGroupDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
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
<%@page import="dss.vector.solutions.entomology.MosquitoView"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.KnockDownAssayDTO"%>
<%@page import="dss.vector.solutions.general.InsecticideDTO"%>



<%@page import="com.terraframe.mojo.business.BusinessDTO"%><c:set var="page_title" value="Query_Resistance"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS, AdultDiscriminatingDoseAssayDTO.CLASS, SpecieDTO.CLASS, LarvaeDiscriminatingDoseAssayDTO.CLASS, EfficacyAssayDTO.CLASS, KnockDownAssayDTO.CLASS, InsecticideDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, LayerViewDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};


    MosquitoDTO mosquito = new MosquitoDTO(requestIF);
    MosquitoViewDTO mosquitoViewDTO = new MosquitoViewDTO(requestIF);
    JSONArray mosquitoAttribs = new JSONArray(mosquito.getAttributeNames());
    List loadables = new ArrayList();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">
// Setting both values to false will select *all* univerals
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;


YAHOO.util.Event.onDOMReady(function(){

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    dropDownMaps = {<%=Halp.getBusinessDropDownMaps((BusinessDTO)new AdultDiscriminatingDoseAssayDTO(requestIF),  requestIF)%>};



    var mapAttribs = function(attribName,index){
      var attrib = this.obj.attributeMap[attribName];
      var row = {};
      if(attrib){
        row.attributeName = attrib.attributeName;
        if(attrib.dtoType === 'AttributeReferenceDTO')
        {
          row.attributeName += '.displayLabel.currentValue';
        }
        if(attrib.dtoType === 'AttributeEnumerationDTO')
        {
          row.attributeName += '.displayLabel.currentValue';
        }
        row.key = attrib.attributeName + this.suffix;
        row.type = this.obj.getType();
        row.dtoType = attrib.dtoType;
        row.displayLabel = attrib.attributeMdDTO.displayLabel;
        if(dropDownMaps[attrib.attributeName]){
          row.dropDownMap = dropDownMaps[attrib.attributeName];
        }
      }else{
        row.attributeName = attribName;
        row.type = 'sqlcharacter';
        row.displayLabel = attribName;
        row.key = attribName;

      }
      return row;
    }




    var mosquitoCollection = new Mojo.$.dss.vector.solutions.entomology.MosquitoCollection();
    var collectionAttribs = ["collectionId","dateCollected","collectionMethod"];
    var collectionColumns = [];

    var Insectcide = new Mojo.$.dss.vector.solutions.general.Insecticide();
    var insectcideAttribs = ["activeIngredient","amount","units"];

    var abstractAssayAttribs = ["specie","identificationMethod","generation","isofemale","exposureTime","testDate"];
    var abstractCalculations = ["resistanceStatus"];

    var adultAssay = new  Mojo.$.dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay();
    var adultAttribs = abstractAssayAttribs.concat(["quantityTested" ,"quantityDead","quantityLive","sex","fed","gravid","holdingTime","mortality","kd50","kd95","controlTestMortality"]);
    var adultCalulations = [];
    collectionColumns =   collectionAttribs.map(mapAttribs, {obj:mosquitoCollection, suffix:'_adult'});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(mapAttribs, {obj:insectcide, suffix:'_adult'}));
    var adultColumns =  collectionColumns.concat(adultAttribs.map(mapAttribs, {obj:adultAssay, suffix:'_adult'}));

    var larvaeAssay = new  Mojo.$.dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay();
    var larvaeAttribs = abstractAssayAttribs.concat(["quantityTested","quantityDead","quantityLive","quantityDead","controlTestMortality","lt50","lt95","mortality"]);
    var larvaeCalculations = ["quanityAlive","percentMortality"];
    collectionColumns =   collectionAttribs.map(mapAttribs, {obj:mosquitoCollection, suffix:'_larvae'});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(mapAttribs, {obj:insectcide, suffix:'_larvae'}));
    var larvaeColumns =  collectionColumns.concat(larvaeAttribs.map(mapAttribs, {obj:larvaeAssay, suffix:'_larvae'}));

    var knockDownAssay = new  Mojo.$.dss.vector.solutions.entomology.assay.KnockDownAssay();
    var knockDownAttribs = abstractAssayAttribs.concat(["quantityTested","sex","fed","gravid","kd50","kd95"]);
    var knockDownCalulations = [];
    collectionColumns =   collectionAttribs.map(mapAttribs, {obj:mosquitoCollection, suffix:'_knockDown'});
    collectionColumns =  collectionColumns.concat(insectcideAttribs.map(mapAttribs, {obj:insectcide, suffix:'_knockDown'}));
    var knockDownColumns =  collectionColumns.concat(knockDownAttribs.map(mapAttribs, {obj:knockDownAssay, suffix:'_knockDown'}));

    //var efficacyAssay = new Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay();
    //public static java.lang.String GEOENTITY = "geoEntity";
    //var efficayAttribs = ["testMethod","holdingTime","quantityTested","colonyName","ageStart","ageEnd","quantity","sex","fed","gravid","quantityDead","quantityLive","mortality","surfacePostion","timeOnSurface"];
    //var efficayCalculations = ["quanityAlive","percentMortality","controlTestMortality"];

    var selectableGroups = [
              {title:"Adult_DDA", values:adultColumns, group:"adult", klass:Mojo.$.dss.vector.solutions.entomology.assay.AdultDiscriminatingDoseAssay.CLASS},
              {title:"Larvae_DDA", values:larvaeColumns, group:"lavae", klass:Mojo.$.dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.CLASS},
              {title:"Knock_Down_Assay", values:knockDownColumns, group:"knockDown", klass:Mojo.$.dss.vector.solutions.entomology.assay.KnockDownAssay.CLASS}
    ];

    var query = new MDSS.QueryResistance(selectableGroups, queryList);
    query.render();

});

</script>

<div class="yui-skin-sam">

<div id="tabSet" class="yui-navset">
    <ul class="yui-nav">
        <li class="selected"><a href="#tab1"><em><fmt:message key="Query_Tab" /></em></a></li>
        <li><a href="#tab2"><em><fmt:message key="Map_Tab" /></em></a></li>
    </ul>
    <div class="yui-content">
        <div><div id="queryPanel"></div></div>
        <div><div id="mapPanel"></div></div>
    </div>
</div>

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>

<textarea id="debug_xml" cols="40" rows="40" style="width:1280px"> </textarea>


</div>

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>
<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>


<jsp:include page="../templates/footer.jsp"></jsp:include>
