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
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.SubCollectionDTO"%>
<%@page import="dss.vector.solutions.entomology.InfectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.PooledInfectionAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.MolecularAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.BiochemicalAssayDTO"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%><c:set var="page_title" value="Query_Entomology"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ MosquitoCollectionDTO.CLASS,InfectionAssayDTO.CLASS,PooledInfectionAssayDTO.CLASS,BiochemicalAssayDTO.CLASS,MolecularAssayDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var collectionMaps = {<%=(String) request.getAttribute("collectionMaps")%>};
    var mosquitoCollection = new dss.vector.solutions.entomology.MosquitoCollection;
    var collectionAttribs = ["collectionId","collectionMethod","geoEntity","collectionDate","lifeStage","abundance"];
    collectionColumns =   collectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:mosquitoCollection, suffix:'_collection',dropDownMaps:collectionMaps});

    var infectionMaps = {<%=(String) request.getAttribute("infectionMaps")%>};
    var infectionAssay = new dss.vector.solutions.entomology.InfectionAssay;
    var infectionAttribs = ["mosquitoId","species","identMethod","sex","parasite","testMethod","infected","numberTested","numberPositive"];
    infectionColumns =   infectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:infectionAssay, suffix:'_infection',dropDownMaps:infectionMaps});

    var pooledInfectionMaps = {<%=(String) request.getAttribute("pooledInfectionMaps")%>};
    var pooledInfectionAssay = new dss.vector.solutions.entomology.PooledInfectionAssay;
    var pooledInfectionAttribs = ["poolId","species","identMethod","sex","parasite","testMethod","infected","mosquitosTested","poolsTested","numberPositive"];
    pooledInfectionColumns =   pooledInfectionAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:pooledInfectionAssay, suffix:'_pooledInfection',dropDownMaps:pooledInfectionMaps});

    var biochemicalMaps = {<%=(String) request.getAttribute("biochemicalMaps")%>};
    var biochemicalAssay = new dss.vector.solutions.entomology.BiochemicalAssay;
    var biochemicalAttribs = ["mosquitoId","species","identMethod","sex","generation","isofemale","numberTested","numberElevated"];
    biochemicalColumns =   biochemicalAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:biochemicalAssay, suffix:'_biochemical',dropDownMaps:biochemicalMaps});

    var molecularMaps = {<%=(String) request.getAttribute("molecularMaps")%>};
    var molecularAssay = new dss.vector.solutions.entomology.MolecularAssay;

    var molecularAttribs = ["mosquitoId","species","identMethod","sex","generation","isofemale","assayMethod","target","numberRR","numberRS","numberSS"];
    molecularColumns =   molecularAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:molecularAssay, suffix:'_molecular',dropDownMaps:molecularMaps});

    var selectableGroups = [
                {title:"Collection", values:collectionColumns, group:"collection", klass:mosquitoCollection.CLASS},
                {title:"Infection_Assay", values:infectionColumns, group:"infection", klass:infectionAssay.CLASS},
                {title:"Pooled_Infection_Assay", values:pooledInfectionColumns, group:"pooledInfection", klass:pooledInfectionAssay.CLASS},
                {title:"Biochemical_Assay", values:biochemicalColumns, group:"biochemical", klass:biochemicalAssay.CLASS},
                {title:"Molecular_Assay", values:molecularColumns, group:"molecular", klass:molecularAssay.CLASS}
        ];

    var query = new MDSS.QueryEntomology(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>


<jsp:include page="../templates/footer.jsp"></jsp:include>
