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
<%@page import="dss.vector.solutions.query.EntomologySearch"%>
<%@page import="dss.vector.solutions.query.EntomologySearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.ThematicLayerDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.query.AggregatedCasesSearchDTO"%>
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
<%@page import="dss.vector.solutions.entomology.MorphologicalSpecieGroupDTO"%>
<%@page import="dss.vector.solutions.entomology.ConcreteMosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<c:set var="page_title" value="Query_Entomology"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%!static String getAssayColumns(MosquitoViewDTO view, Class superAssayClass , ClientRequestIF clientRequest) throws JSONException{
  String s = "[";
  Class viewClass = view.getClass();
  MdAttributeVirtualDTO[] mdArray = MosquitoViewDTO.getAccessors(clientRequest,superAssayClass.getCanonicalName());
  for (MdAttributeVirtualDTO md : mdArray)
  {
    String acc = md.getAccessor();
      if(acc.length() == 0)
      {
        acc =  md.getAttributeName();
      }
      s += "'"+ acc + "',";
  }
  return s + "]";
}%>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoDTO.CLASS, SpecieDTO.CLASS, EntomologySearchDTO.CLASS, MosquitoViewDTO.CLASS, MorphologicalSpecieGroupDTO.CLASS,MosquitoCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};
    MosquitoDTO mosquito = new MosquitoDTO(requestIF);
    MosquitoViewDTO mosquitoView = new MosquitoViewDTO(requestIF);
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

     dropDownMaps = {<%=Halp.getDropDownMaps(mosquitoView,  requestIF)%>};

    //var mosquitoAttribs = <%=mosquitoAttribs%>

    function mapAttribs(arr, type, obj){
      tmpType = type;
      obj = obj;
      return arr.map(function(attribName){
        var attrib = obj.attributeMap[attribName];
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
          if(attrib.dtoType === 'AttributeIntegerDTO')
          {
          	row.numeric = true;
          }
          row.type = tmpType;
          row.dtoType = attrib.dtoType;
          row.displayLabel = attrib.attributeMdDTO.displayLabel;
          if(dropDownMaps[attrib.attributeName]){
            row.dropDownMap = dropDownMaps[attrib.attributeName];
          }
        }else{
          row.attributeName = attribName;
          row.type = 'sqlcharacter';
          row.displayLabel = attribName;
        }
        return row;
      });
      delete tmpType;
      delete obj;
    }

    function mapAttribs(arr, type, obj){
      tmpType = type;
      obj = obj;
      return arr.map(function(attribName){
        var attrib = obj.attributeMap[attribName];
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
          if(attrib.dtoType === 'AttributeIntegerDTO')
          {
            row.numeric = true;
          }
          row.type = tmpType;
          row.dtoType = attrib.dtoType;
          row.displayLabel = attrib.attributeMdDTO.displayLabel;
          if(dropDownMaps[attrib.attributeName]){
            row.dropDownMap = dropDownMaps[attrib.attributeName];
          }
        }else{
          row.attributeName = attribName;
          row.type = 'sqlcharacter';
          row.displayLabel = attribName;
        }
        return row;
      });
      delete tmpType;
      delete obj;
    }



    var mosquitoView = new Mojo.$.dss.vector.solutions.entomology.MosquitoView();
    var mosquitoGroup = new  Mojo.$.dss.vector.solutions.entomology.MorphologicalSpecieGroup();
    var mosquitoCollection = new Mojo.$.dss.vector.solutions.entomology.MosquitoCollection();

    var collectionAttribs = ["collectionId"];
    collectionColumns =  mapAttribs(collectionAttribs,'<%=MosquitoCollectionDTO.CLASS%>', mosquitoCollection);

    var groupAttribs = ["specie","identificationMethod","quantityFemale","quantityMale","quantity"];
    var groupColumns =  collectionColumns.concat(mapAttribs(groupAttribs,'<%=MorphologicalSpecieGroupDTO.CLASS%>', mosquitoGroup));

    var mosquitoAttribs = ["sampleId","specie","identificationMethod","sex","generation","isofemale","testDate"];
    var mosquitoColumns = collectionColumns.concat(mapAttribs(mosquitoAttribs,'<%=MosquitoDTO.CLASS%>', mosquitoView));

    var assays = [];

    var infectivityColumns = <%=getAssayColumns(mosquitoView,InfectivityAssayTestResult.class,requestIF)%>;
    infectivityColumns = infectivityColumns.concat(infectivityColumns.map(function(a){return a + 'Method'}));
    infectivityColumns.sort();

    var targetSiteColumns = <%=getAssayColumns(mosquitoView,TargetSiteAssayTestResult.class,requestIF)%>;
    targetSiteColumns = targetSiteColumns.concat(targetSiteColumns.map(function(a){return a + 'Method'}));
    targetSiteColumns.sort();

    var metabolicColumns = <%=getAssayColumns(mosquitoView,MetabolicAssayTestResult.class,requestIF)%>;
    metabolicColumns.sort();

    assays.push(mapAttribs(infectivityColumns ,'<%=MosquitoDTO.CLASS%>', mosquitoView));
    assays.push(mapAttribs(targetSiteColumns ,'<%=MosquitoDTO.CLASS%>', mosquitoView));
    assays.push(mapAttribs(metabolicColumns ,'<%=MosquitoDTO.CLASS%>', mosquitoView));

    var query = new MDSS.QueryEntomology(groupColumns,mosquitoColumns, assays, queryList);
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
<jsp:include page="../templates/footer.jsp"></jsp:include>
