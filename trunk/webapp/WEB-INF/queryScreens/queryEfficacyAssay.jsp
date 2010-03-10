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
<%@page import="dss.vector.solutions.surveillance.AggregatedAgeGroupDTO"%>
<%@page import="dss.vector.solutions.surveillance.AggregatedCaseDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>

<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>

<%@page import="dss.vector.solutions.general.InsecticideDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>




<%@page import="com.terraframe.mojo.business.BusinessDTO"%>
<%@page import="dss.vector.solutions.export.EfficacyAssayExcelViewDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayView"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO"%><c:set var="page_title" value="Query_Efficacy"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{  EfficacyAssayDTO.CLASS, InsecticideDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

//    MosquitoViewDTO mosquitoViewDTO = new MosquitoViewDTO(requestIF);

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

    var efficacyMaps = {<%//=(String) request.getAttribute("adultMap")%>};

    var insecticideMaps = {<%//=(String) request.getAttribute("insecticideMap")%>};


    var insectcide = new Mojo.$.dss.vector.solutions.general.Insecticide();

    <%
      Halp.setReadableAttributes(request, "insectcideAttribs", InsecticideDTO.CLASS, requestIF);
    %>
    
    var insectcideAttribs = ["activeIngredient","amount","units"];
    var available = new MDSS.Set(<%= request.getAttribute("insectcideAttribs") %>);
    insectcideAttribs = Mojo.Iter.filter(insectcideAttribs, function(attrib){
        return this.contains(attrib);
    }, available);

    var insectcideColumns =   insectcideAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:insectcide, suffix:'_eff', dropDownMaps:insecticideMaps});

    
    var efficacyAssay = new Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay();

    <%
      Halp.setReadableAttributes(request, "efficacyAttribs", EfficacyAssayViewDTO.CLASS, requestIF);
    %>
    
    //public static java.lang.String GEOENTITY = "geoEntity";
    var efficacyAttribs = ["testDate","specie","testMethod","holdingTime","colonyName","sex","fed","gravid","quantityTested","quantityDead","quantityLive","mortality","surfacePostion","timeOnSurface"];
    available = new MDSS.Set(<%= request.getAttribute("efficacyAttribs") %>);
    efficacyAttribs = Mojo.Iter.filter(efficacyAttribs, function(attrib){
        return this.contains(attrib);
    }, available);
    
    //var efficacyCalculations = ["quanityAlive","percentMortality","controlTestMortality"];

    var efficacyColumns =  efficacyAttribs.map(MDSS.QueryBaseNew.mapAttribs, {obj:efficacyAssay, suffix:'_efficacy', dropDownMaps:efficacyMaps});

    //efficacyColumns[0].type = Mojo.$.dss.vector.solutions.entomology.assay.AbstractAssay.CLASS;

    var selectableGroups = [
              {title:"Efficacy", values:efficacyColumns, group:"eff", klass:Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS},
              {title:"Insecticide", values:insectcideColumns, group:"eff", klass:Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay.CLASS},
    ];

    var query = new MDSS.QueryEfficacyAssay(selectableGroups, queryList);
    query.render();

});

</script>

<jsp:include page="queryContainer.jsp"></jsp:include>




<jsp:include page="../templates/footer.jsp"></jsp:include>
