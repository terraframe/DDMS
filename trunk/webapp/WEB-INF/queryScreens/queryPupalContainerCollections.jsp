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
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.entomology.PupalCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.PupalPremiseDTO"%>
<%@page import="dss.vector.solutions.entomology.PupalContainerDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>


<%@page import="com.runwaysdk.business.BusinessDTO"%>
<c:set var="page_title" value="Query_Pupae_by_Individual_Container"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ PupalPremiseDTO.CLASS, PupalContainerDTO.CLASS, PupalCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    var collectionMaps = {<%=(String) request.getAttribute("collectionMap")%>};
    var containerMaps = {<%=(String) request.getAttribute("containerMap")%>};
    
    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var collection = new dss.vector.solutions.entomology.PupalCollection;
    var pt = new dss.vector.solutions.entomology.PupalPremise;
    var premise = new dss.vector.solutions.entomology.PupalPremise;
    var container = new dss.vector.solutions.entomology.PupalContainer;

    var collectionAttribs = [ "startDate","endDate","collectionId","geoEntity"];
    <%
    Halp.setReadableAttributes(request, "collectionAttribs", PupalCollectionDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:collection, suffix:'_col', dropDownMaps:collectionMaps});

    var premiseTaxonAttribs = [ ];
    
    var collectionColumns =   collectionColumns.concat(premiseTaxonAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:pt, suffix:'_col', dropDownMaps:collectionMaps}));

    var premiseAttribs = [ "numberExamined","numberInhabitants", "premiseSize", "premiseType"];
    
    var collectionColumns =   collectionColumns.concat(premiseAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:premise, suffix:'_col', dropDownMaps:collectionMaps}));

    var containerAttribs = [ "containerId","containerLength", "containerType", "diameter","drawdownFrequency","drawdownPercent","fillFrequency","fillMethod","height",
                             "lid","openingDiameter","openingLength","openingWidth","roof","shading","shape","width"];


    
    var containerColumns =   containerAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:container, suffix:'_cont', dropDownMaps:containerMaps});

    var taxonAmmountsColumns = orderedGrids.pupaeAmmount.options.map(MDSS.QueryBaseNew.mapMo, orderedGrids.pupaeAmmount);

    
    var calculations = ([
                                       
                         {
                           
                           key:"percent_pupae_contribution",
                           type:"sqlfloat",
                           attributeName:"percent_pupae_contribution",
                           isAggregate:true
                         },

                         {
                           
                           key:"percent_lidclass_contribution",
                           type:"sqlfloat",
                           attributeName:"percent_lidclass_contribution",
                           isAggregate:true
                         },

                         {
                           
                           key:"percent_drawdown_contribution",
                           type:"sqlfloat",
                           attributeName:"percent_drawdown_contribution",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"pupae_per_premise_by_taxon",
                           type:"sqlfloat",
                           attributeName:"pupae_per_premise_by_taxon",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"pupae_per_hectare_by_taxon",
                           type:"sqlfloat",
                           attributeName:"pupae_per_hectare_by_taxon",
                           isAggregate:true
                         },
                         {
                           
                           key:"pupae_per_person_per_taxon",
                           type:"sqlfloat",
                           attributeName:"pupae_per_person_per_taxon",
                           isAggregate:true
                         },

                        ]);


      var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"c", klass:collection.CLASS},
                {title:"Container", values:containerColumns, group:"c", klass:collection.CLASS},
                {title:"Container_Calculations", values:calculations, group:"cc", klass:collection.CLASS},
                {title:"Pupae_Amount", values:taxonAmmountsColumns, group:"c",klass:collection.CLASS}
      ];

    
    var query = new MDSS.QueryPupalContainerCollection(selectableGroups, queryList);
    query.render();

});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
