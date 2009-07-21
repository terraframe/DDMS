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
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoView"%>
<%@page import="java.util.Locale"%><c:set var="page_title" value="Query_Entomology"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoDTO.CLASS, SpecieDTO.CLASS, MosquitoViewDTO.CLASS, UninterestingSpecieGroupDTO.CLASS,MosquitoCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};
    MosquitoDTO mosquito = new MosquitoDTO(requestIF);
    MosquitoViewDTO mosquitoViewDTO = new MosquitoViewDTO(requestIF);
    JSONArray mosquitoAttribs = new JSONArray(mosquito.getAttributeNames());
%>

<%=Halp.loadTypes((List<String>) Arrays.asList(mosquitoTypes))%>
<%=Halp.loadTypes((List<String>) Arrays.asList(queryTypes))%>
<script type="text/javascript">
// Setting both values to false will select *all* univerals
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;

(function(){

  YAHOO.util.Event.onDOMReady(function(){

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;
    var assayTree = <%= (String) request.getAttribute("assayTree") %>;

    dropDownMaps = {<%//=Halp.getDropDownMaps(mosquitoView,  requestIF)%>};



    function mapAssayAttribs(arr){
      return arr.map(function(row){
          if(dropDownMaps[row.attributeName]){
            row.dropDownMap = dropDownMaps[row.attributeName];
          }
          var splitType = row.dtoType.split('.');
          row.dtoType = splitType[splitType.length - 1];
          return row;
      });
    }



    var collectionAttribs = ["collectionId","dateCollected"];
    collectionColumns =  mapAttribs(collectionAttribs,'<%=MosquitoCollectionDTO.CLASS%>', mosquitoCollection);

    var groupAttribs = ["sampleId","specie","identificationMethod","quantity"];
    //var groupColumns =  collectionColumns.concat(mapAttribs(groupAttribs,'<%=UninterestingSpecieGroupDTO.CLASS%>', mosquitoGroup));
    var groupColumns =  mapAttribs(groupAttribs,'<%=UninterestingSpecieGroupDTO.CLASS%>', mosquitoGroup);

    
    var ADDA = [
                {
                  displayLabel:"Spray Area Target",
                  dtotype:"sqlcharacter",
                  type:"sqlcharacter",
                  attributeName:"SPRAY_AREA_TARGET",
                },
                {
                  displayLabel:"Oprator Target",
                  dtotype:"sqlcharacter",
                  type:"sqlcharacter",
                  attributeName:"OPRATOR_TARGET",
                },
                {
                  displayLabel:"Team Target",
                  dtotype:"sqlcharacter",
                  type:"sqlcharacter",
                  attributeName:"test",
                }
                ];



    selectableGroups = [
                        {title:"Adult Discrimination Dose Assay ",
                        	values:ADDA},
                        {title:"Adult Diagnostic Dose Assay ",
                        	values:[ADDA
                                  ]},
                         {title:"Larval Discrimination Dose Assay ",
                        	 values:[ADDA
                                   ]},
                         {title:"Pooled Assays",
                         	values:[ADDA
                                   ]},

    ];


    var query = new MDSS.QueryResistance(selectableGroups, queryList);
    query.render();

  });

})();
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

<textarea id="debug_xml" cols="40" rows="40" style="width:1280px">

</textarea>
</div>

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>
<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>


<jsp:include page="../templates/footer.jsp"></jsp:include>
