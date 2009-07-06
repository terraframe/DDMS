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
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%><c:set var="page_title" value="Query_Entomology"  scope="request"/>

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

      String concreteId = md.getValue(MdAttributeVirtualInfo.MD_ATTRIBUTE_CONCRETE);
      MdAttributeConcreteDAOIF concrete = MdAttributeConcreteDAO.get(concreteId);

      MdBusinessDAOIF mdClass = MdBusinessDAO.get(concrete
          .getValue(MdAttributeConcreteInfo.DEFINING_MD_CLASS));


      s += "{";
      s += "viewAccessor:'"+acc+"',";
      s += "attributeName:'testResult.displayLabel.currentValue',";
      s += "type:'"+ mdClass.definesType() + "',";
//    s += "dtoType:'" + md.getMdAttributeConcrete().getType() + "',";
//    s += "dtoType:'" + md.getType() + "',";
      s += "displayLabel:'" + md.getMdAttributeConcrete().getDisplayLabel() + "'";
      s += "},\n";

      try
      {
        Class<?> c = view.getClass();
        String testMdGetter = "get" + acc.substring(0, 1).toUpperCase() + acc.substring(1) + "MethodMd";
        AttributeReferenceMdDTO testMd = (AttributeReferenceMdDTO) c.getMethod(testMdGetter).invoke(view);
        s += "{";
        s += "viewAccessor:'"+acc+"Method',";
        s += "attributeName:'testMethod.displayLabel.currentValue',";
        s += "type:'"+ mdClass.definesType() + "',";
//      s += "dtoType:'" + testMd.getJavaType() + "',";
        s += "displayLabel:'" + md.getMdAttributeConcrete().getDisplayLabel() + " " + testMd.getDisplayLabel() +"'";
        s += "},\n";

      }
      catch(Exception e)
      {
        System.out.print(e);
      }


  }
  return s + "]";
}%>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoDTO.CLASS, SpecieDTO.CLASS, MosquitoViewDTO.CLASS, UninterestingSpecieGroupDTO.CLASS,MosquitoCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};
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


    function mapAssayAttribs(arr,obj){
      return arr.map(function(row){
          if(dropDownMaps[row.viewAccessor]){
            row.dropDownMap = dropDownMaps[row.viewAccessor];
          }
          var attrib = mosquitoView.attributeMap[row.viewAccessor];
          row.dtoType = attrib.dtoType;
          return row;
      });
    }


    var mosquitoView = new Mojo.$.dss.vector.solutions.entomology.MosquitoView();
    var mosquitoGroup = new  Mojo.$.dss.vector.solutions.entomology.UninterestingSpecieGroup();
    var mosquitoCollection = new Mojo.$.dss.vector.solutions.entomology.MosquitoCollection();

    var collectionAttribs = ["collectionId","dateCollected"];
    collectionColumns =  mapAttribs(collectionAttribs,'<%=MosquitoCollectionDTO.CLASS%>', mosquitoCollection);

    var groupAttribs = ["sampleId","specie","identificationMethod","quantity"];
    var groupColumns =  collectionColumns.concat(mapAttribs(groupAttribs,'<%=UninterestingSpecieGroupDTO.CLASS%>', mosquitoGroup));

    var mosquitoAttribs = ["sampleId","specie","identificationMethod","sex","generation","isofemale","testDate"];
    var mosquitoColumns = collectionColumns.concat(mapAttribs(mosquitoAttribs,'<%=MosquitoDTO.CLASS%>', mosquitoView));

    var assays = [];

    var infectivityColumns = <%=getAssayColumns(mosquitoView,InfectivityAssayTestResult.class,requestIF)%>;

    var targetSiteColumns = <%=getAssayColumns(mosquitoView,TargetSiteAssayTestResult.class,requestIF)%>;

    var metabolicColumns = <%=getAssayColumns(mosquitoView,MetabolicAssayTestResult.class,requestIF)%>;

    assays.push(mapAssayAttribs(infectivityColumns));
    assays.push(mapAssayAttribs(targetSiteColumns));
    assays.push(mapAssayAttribs(metabolicColumns));

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

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>
<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>


<jsp:include page="../templates/footer.jsp"></jsp:include>
