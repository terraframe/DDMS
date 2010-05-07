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
<%@page import="dss.vector.solutions.entomology.ImmatureCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.PremiseTaxonDTO"%>
<%@page import="dss.vector.solutions.entomology.CollectionPremiseDTO"%>
<%@page import="dss.vector.solutions.entomology.CollectionContainerDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>


<%@page import="com.runwaysdk.business.BusinessDTO"%>
<c:set var="page_title" value="Query_Control_of_Immatures"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ ImmatureCollectionDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS, PremiseTaxonDTO.CLASS, CollectionPremiseDTO.CLASS, CollectionContainerDTO.CLASS};


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
    //var collectionMaps = {<%=(String) request.getAttribute("collectionMap")%>};
    
    var orderedGrids = {} ;//<%=(String) request.getAttribute("orderedGrids")%>;

    var collection = new dss.vector.solutions.entomology.ImmatureCollection;
    var pt = new dss.vector.solutions.entomology.PremiseTaxon;
    var premise = new dss.vector.solutions.entomology.CollectionPremise;
    //var container = new dss.vector.solutions.entomology.CollectionContainer;

    var collectionAttribs = [ "startDate","endDate","collectionId","geoEntity"];
    <%
    Halp.setReadableAttributes(request, "collectionAttribs", ImmatureCollectionDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:collection, suffix:'_col', dropDownMaps:collectionMaps});

    var premiseTaxonAttribs = [ "taxon"];
    
    var collectionColumns =   collectionColumns.concat(premiseTaxonAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:pt, suffix:'_col', dropDownMaps:collectionMaps}));

    var premiseAttribs = [ "numberExamined","numberInhabitants", "premiseSize", "premiseType"];
    
    var collectionColumns =   collectionColumns.concat(premiseAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:premise, suffix:'_col', dropDownMaps:collectionMaps}));

    var containerAttribs = [ "numberContainers","numberDestroyed", "numberImmatures", "numberLarvae","numberLarvaeCollected","numberPupae","numberPupaeCollected","numberWithLarvicide","numberWithWater"];
    
    //var containerColumns =   containerAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:container, suffix:'_col', dropDownMaps:collectionMaps});

    var indexes = ([
                                       
                         {
                           
                           key:"hi_lp",
                           type:"sqlfloat",
                           attributeName:"hi_lp",
                           isAggregate:true
                         },

                         {
                           
                           key:"hi_p",
                           type:"sqlfloat",
                           attributeName:"hi_p",
                           isAggregate:true
                         },

                         {
                           
                           key:"ci_lp",
                           type:"sqlfloat",
                           attributeName:"ci_lp",
                           isAggregate:true
                         },

                         {
                           
                           key:"ci_l",
                           type:"sqlfloat",
                           attributeName:"ci_l",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"ci_p",
                           type:"sqlfloat",
                           attributeName:"ci_p",
                           isAggregate:true
                         },
                         {
                           
                           key:"bi_lp",
                           type:"sqlfloat",
                           attributeName:"bi_lp",
                           isAggregate:true
                         },

                         {
                           
                           key:"bi_l",
                           type:"sqlfloat",
                           attributeName:"bi_l",
                           isAggregate:true
                         },

                         {
                           
                           key:"bi_p",
                           type:"sqlfloat",
                           attributeName:"bi_p",
                           isAggregate:true
                         },

                         {
                           
                           key:"pi",
                           type:"sqlfloat",
                           attributeName:"pi",
                           isAggregate:true
                         },
                         
                 {
                           
                           key:"pppr",
                           type:"sqlfloat",
                           attributeName:"pppr",
                           isAggregate:true
                         },

                         {
                           
                           key:"ppha",
                           type:"sqlfloat",
                           attributeName:"ppha",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"pppe",
                           type:"sqlfloat",
                           attributeName:"pppe",
                           isAggregate:true
                         },

                        ]);



   
/*

    
    Based on totals for collections within the selected time and space:
    a) House Index for Immatures (HI-LP; percentage of premises with immatures [larvae or pupae]):  (from column Immatures)

    b) House Index for Larvae (HI-L; percentage of premises with larvae): 

    c) House Index for Pupae (HI-P; percentage of premises with pupae): 

    d) Container Index for Immatures (CI-LP; percentage of water-holding containers with immatures [larvae or pupae]): (from column Immatures)

    e) Container Index for Larvae (CI-L; percentage of water-holding containers with larvae): 

    f) Container Index for Pupae (CI-P; percentage of water-holding containers with pupae): 

    g) Breteau Index for Immatures (BI-LP; numbers of containers with immatures [larvae or pupae] per 100 examined premises): (from column Immatures)

    h) Breteau Index for Larvae (BI-L; numbers of containers with larvae per 100 examined premises): 

    i) Breteau Index for Pupae (BI-P; numbers of containers with pupae per 100 examined premises): 

    j) Pupal Index (PI; total number of pupae per 100 premises): 

    k) Pupae per Premise (PPPr; number of pupae per premise): 

    l) Pupae per Hectare (PPHa; number of pupae per hectare):

    m) Pupae per Person (PPPe; number of pupae per person): 
      percent_water_holding_immatures
      percent_water_holding_larvae
      percent_water_holding_pupae
      percent_immature_contribution
      percent_larve_contribution
      percent_pupae_contribution

      */



    
    var calculations = ([
                                       
                         {
                           
                           key:"percent_water_holding_immatures",
                           type:"sqlfloat",
                           attributeName:"percent_water_holding_immatures",
                           isAggregate:true
                         },

                         {
                           
                           key:"percent_water_holding_larvae",
                           type:"sqlfloat",
                           attributeName:"percent_water_holding_larvae",
                           isAggregate:true
                         },

                         {
                           
                           key:"percent_water_holding_pupae",
                           type:"sqlfloat",
                           attributeName:"percent_water_holding_pupae",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"percent_immature_contribution",
                           type:"sqlfloat",
                           attributeName:"percent_immature_contribution",
                           isAggregate:true
                         },
                         
                         {
                           
                           key:"percent_larve_contribution",
                           type:"sqlfloat",
                           attributeName:"percent_larve_contribution",
                           isAggregate:true
                         },
                         {
                           
                           key:"percent_pupae_contribution",
                           type:"sqlfloat",
                           attributeName:"percent_pupae_contribution",
                           isAggregate:true
                         },



                        ]);
    

    /*

    By container type:
    a) Percentage of Water-holding Containers with Immatures [Larvae or Pupae] by Container Type (from column Immatures): Number with immatures/Number with water*100

    b) Percentage of Water-holding Containers with Larvae by Container Type:

    c) Percentage of Water-holding Containers with Pupae by Container Type:

    d) Percentage Contribution to Immature Production [Larvae or Pupae] by a Single Container Type Relative to All Container Types (from column Immatures):

    e) Percentage Contribution to Larval Production by a Single Container Type Relative to All Container Types: 

    f) Percentage Contribution to Pupae Production by a Single Container Type Relative to All Container Types: 

*/
      var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"l", klass:collection.CLASS},
                //{title:"Container", values:calculations, group:"c", klass:collection.CLASS},
                {title:"Indices", values:indexes, group:"i", klass:collection.CLASS},
                {title:"Container Calculations", values:calculations, group:"cc", klass:collection.CLASS},
      ];

    
    var query = new MDSS.QueryImmatureContainerCollection(selectableGroups, queryList);
    query.render();

});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>




<jsp:include page="../templates/footer.jsp"></jsp:include>
