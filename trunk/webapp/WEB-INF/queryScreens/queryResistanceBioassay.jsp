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
<%@page import="dss.vector.solutions.entomology.DiagnosticAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.TimeResponseAssayDTO"%>
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
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS, DiagnosticAssayDTO.CLASS, TimeResponseAssayDTO.CLASS, InsecticideDTO.CLASS};
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

    diagnosticAssayMaps = {<%=(String) request.getAttribute("diagnosticMap")%>};

    timeResponseMaps = {<%=(String) request.getAttribute("timeResponseMap")%>};

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection;

    var collectionAttribs = ["collectionId","collectionDate","collectionMethod","geoEntity"];
    <%
    Halp.setReadableAttributes(request, "collectionAttribs", SearchMosquitoCollectionViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);    
    var collectionColumns = [];



    
    var diagnosticAssayAttribs = ["activeIngredient","assay","lifeStage","outcome","species","synergist"];

    var diagnosticAssay = new  dss.vector.solutions.entomology.DiagnosticAssay();
    <%
    Halp.setReadableAttributes(request, "diagnosticAttribs", DiagnosticAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("diagnosticAttribs") %>);
    diagnosticAssayAttribs = Mojo.Iter.filter(diagnosticAssayAttribs, function(attrib){
      return this.contains(attrib);
    }, available);   
       
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_diag', dropDownMaps:diagnosticAssayMaps});
    var diagnosticColumns =  collectionColumns.concat(diagnosticAssayAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:diagnosticAssay, suffix:'_diag', dropDownMaps:diagnosticAssayMaps}));

    
    var timeAssay = new  dss.vector.solutions.entomology.TimeResponseAssay();
    var timeAttribs = ["activeIngredient","assay","lifeStage","species","synergist","testStrainResult","referenceStrainResult"]
    <%
    Halp.setReadableAttributes(request, "timeAttribs", TimeResponseAssayDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("timeAttribs") %>);
    timeAttribs = Mojo.Iter.filter(timeAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    collectionColumns =  collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_time', dropDownMaps:timeResponseMaps});
    var timeColumns =  collectionColumns.concat(timeAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:timeAssay, suffix:'_time', dropDownMaps:timeResponseMaps}));



    var mapterm = function(term,index){
     
       var row = {};
       row.displayLabel = term.displayLabel;
       row.attributeName = term.MOID.replace(':','');

       MDSS.Localized[row.attributeName]= term.displayLabel;

       var calculations = ([

                           ]);
       
       this.lifeStage.options.forEach( function(stage){

      	var attributeName = stage.MOID.replace(':','');

        var key = row.attributeName+attributeName

        MDSS.Localized[key]= row.displayLabel + " " + stage.displayLabel;
        
      	calculations = calculations.concat([
                              
                              {
                                key:key,
                                type:"sqlfloat",
                                attributeName:key,
                                isAggregate:true
                              },

                             ]);
         
       });
       
       var group = {title:row.attributeName, values:calculations, group:"time", klass:dss.vector.solutions.entomology.TimeResponseAssay.CLASS};
       
      return group;      
    }

   var taxonCalcGroups = orderedGrids.assayType.options.map(mapterm, orderedGrids);

    
    var selectableGroups = [
              {title:"Diagnostic_Dose_Assay", values:diagnosticColumns, group:"diag", klass:dss.vector.solutions.entomology.DiagnosticAssay.CLASS},
              {title:"Time_Response_Assay", values:timeColumns, group:"time", klass:dss.vector.solutions.entomology.TimeResponseAssay.CLASS},
    ];

    selectableGroups = selectableGroups.concat(taxonCalcGroups);

    
    var query = new MDSS.QueryResistanceBioassay(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>

<jsp:include page="../templates/footer.jsp"></jsp:include>
