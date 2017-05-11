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
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.InfectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.InfectionAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.PooledInfectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.PooledInfectionAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MolecularAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.MolecularAssayViewDTO"%>
<%@page import="dss.vector.solutions.entomology.BiochemicalAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.BiochemicalAssayViewDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.runwaysdk.constants.MdAttributeConcreteInfo"%>
<%@page import="com.runwaysdk.constants.MdAttributeVirtualInfo"%>
<%@page import="com.runwaysdk.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.entomology.SearchMosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.ontology.NestedTermsWarningDTO"%><c:set var="page_title" value="Query_Entomology"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jwr:style src="/bundles/queryCssBundle.css" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS,InfectionAssayDTO.CLASS,PooledInfectionAssayDTO.CLASS,BiochemicalAssayDTO.CLASS,MolecularAssayDTO.CLASS,MosquitoCollectionViewDTO.CLASS,InfectionAssayViewDTO.CLASS,PooledInfectionAssayViewDTO.CLASS,BiochemicalAssayViewDTO.CLASS,MolecularAssayViewDTO.CLASS};
    String[] queryTypes = new String[]{NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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
    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection;
    var collectionAttribs = ["collectionId","geoEntity","collectionDate","collectionMethod","lifeStage","abundance"];

    <%
      Halp.setReadableAttributes(request, "collectionAttribs", SearchMosquitoCollectionViewDTO.CLASS, requestIF);
    %>
    var available = new MDSS.Set(<%= request.getAttribute("collectionAttribs") %>);
    collectionAttribs = Mojo.Iter.filter(collectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_collection',dropDownMaps:collectionMaps});


    var infectionMaps = {<%=(String) request.getAttribute("infectionMaps")%>};
    var infectionAssay = new dss.vector.solutions.entomology.InfectionAssay;
    var infectionAttribs = ["uniqueAssayId", "mosquitoId","species","identMethod","sex","parasite","testMethod","infected","numberTested","numberPositive"];
    <%
      Halp.setReadableAttributes(request, "infectionAttribs", InfectionAssayViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("infectionAttribs") %>);
    infectionAttribs = Mojo.Iter.filter(infectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    var infectionColumns =   infectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:infectionAssay, suffix:'_infection',dropDownMaps:infectionMaps});

    infectionColumns = infectionColumns.concat([
                                                      {
                                                         
                                                         key:"prevalence",
                                                         type:"sqlfloat",
                                                         attributeName:"prevalence",
                                                         isAggregate:true
                                                       },
                                                      
                                                    ]);

    var pooledInfectionMaps = {<%=(String) request.getAttribute("pooledInfectionMaps")%>};
    var pooledInfectionAssay = new dss.vector.solutions.entomology.PooledInfectionAssay;
    var pooledInfectionAttribs = ["uniqueAssayId", "poolId","species","identMethod","sex","parasite","testMethod","infected","mosquitosTested","poolsTested","numberPositive"];
    <%
      Halp.setReadableAttributes(request, "pooledInfectionAttribs", PooledInfectionAssayViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("pooledInfectionAttribs") %>);
    pooledInfectionAttribs = Mojo.Iter.filter(pooledInfectionAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
  
    
    pooledInfectionColumns =   pooledInfectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:pooledInfectionAssay, suffix:'_pooledInfection',dropDownMaps:pooledInfectionMaps});


    pooledInfectionColumns = pooledInfectionColumns.concat([
                                                {
                                                   
                                                   key:"minPrevalence",
                                                   type:"sqlfloat",
                                                   attributeName:"minPrevalence",
                                                   isAggregate:true
                                                 },
                                                
                                              ]);

    
    var biochemicalMaps = {<%=(String) request.getAttribute("biochemicalMaps")%>};
    var biochemicalAssay = new dss.vector.solutions.entomology.BiochemicalAssay;
    var biochemicalAttribs = ["uniqueAssayId", "mosquitoId","species","identMethod","sex","generation","isofemale","assay","numberTested","numberElevated"];
    <%
      Halp.setReadableAttributes(request, "biochemicalAttribs", BiochemicalAssayViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("biochemicalAttribs") %>);
    biochemicalAttribs = Mojo.Iter.filter(biochemicalAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
      
    biochemicalColumns =   biochemicalAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:biochemicalAssay, suffix:'_biochemical',dropDownMaps:biochemicalMaps});

    biochemicalColumns = biochemicalColumns.concat([
                                                        {
                                                           
                                                           key:"elevated",
                                                           type:"sqlfloat",
                                                           attributeName:"elevated",
                                                           isAggregate:true
                                                         },
                                                        
                                                      ]);

    var molecularMaps = {<%=(String) request.getAttribute("molecularMaps")%>};
    var molecularAssay = new dss.vector.solutions.entomology.MolecularAssay;

    var molecularAttribs = ["uniqueAssayId", "mosquitoId","species","identMethod","sex","generation","isofemale","assayMethod","target","numberRR","numberRS","numberSS"];
    <%
      Halp.setReadableAttributes(request, "molecularAttribs", MolecularAssayViewDTO.CLASS, requestIF);
    %>
    available = new MDSS.Set(<%= request.getAttribute("molecularAttribs") %>);
    molecularAttribs = Mojo.Iter.filter(molecularAttribs, function(attrib){
      return this.contains(attrib);
    }, available);
    
    molecularColumns =   molecularAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:molecularAssay, suffix:'_molecular',dropDownMaps:molecularMaps});


    molecularColumns = molecularColumns.concat([
                                                      {
                                                         
                                                         key:"percentRR",
                                                         type:"sqlfloat",
                                                         attributeName:"percentRR",
                                                         isAggregate:true
                                                       },
                                                       {
                                                         
                                                         key:"percentRS",
                                                         type:"sqlfloat",
                                                         attributeName:"percentRS",
                                                         isAggregate:true
                                                       },
                                                       {
                                                         
                                                         key:"percentSS",
                                                         type:"sqlfloat",
                                                         attributeName:"percentSS",
                                                         isAggregate:true
                                                       },
                                                       {
                                                         
                                                         key:"frequencyR",
                                                         type:"sqlfloat",
                                                         attributeName:"frequencyR",
                                                         isAggregate:true
                                                       },
                                                       {
                                                         
                                                         key:"frequencyS",
                                                         type:"sqlfloat",
                                                         attributeName:"frequencyS",
                                                         isAggregate:true
                                                       },
                                                      
                                                    ]);

    
    var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"collection", klass:mosquitoCollection.CLASS},
                {title:"Infection_Assay", values:infectionColumns, group:"infection", klass:infectionAssay.CLASS},
                {title:"Combined_Data", values:pooledInfectionColumns, group:"pooledInfection", klass:pooledInfectionAssay.CLASS},
                {title:"Biochemical_Assay", values:biochemicalColumns, group:"biochemical", klass:biochemicalAssay.CLASS},
                {title:"Molecular_Assay", values:molecularColumns, group:"molecular", klass:molecularAssay.CLASS}
        ];

    var query = new MDSS.QueryEntomology(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
