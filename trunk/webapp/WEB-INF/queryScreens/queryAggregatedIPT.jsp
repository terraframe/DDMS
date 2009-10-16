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
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.entomology.MosquitoViewDTO"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
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
<%@page import="dss.vector.solutions.intervention.monitor.AggregatedIPTDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTANCVisitDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTDoseDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTTreatmentDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IPTPatientsDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>




<%@page import="com.terraframe.mojo.business.BusinessDTO"%><c:set var="page_title" value="Query_Aggregated_IPT"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ AggregatedIPTDTO.CLASS, IPTANCVisitDTO.CLASS, IPTDoseDTO.CLASS, IPTTreatmentDTO.CLASS, IPTPatientsDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, LayerViewDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};

    MosquitoViewDTO mosquitoViewDTO = new MosquitoViewDTO(requestIF);

    List<String> loadables = new ArrayList<String>();
    loadables.addAll(Arrays.asList(mosquitoTypes));
    loadables.addAll(Arrays.asList(queryTypes));
%>

<%=Halp.loadTypes(loadables)%>

<script type="text/javascript">
// Setting both values to false will select *all* univerals
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = false;


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

    var mapAttribs = function(attribName,index){
      var attrib = this.obj.attributeMap[attribName];
      var row = {};
      if(attrib){
        row.attributeName = attrib.attributeName;
        if(attrib.dtoType.contains('AttributeReferenceDTO'))
        {
          row.attributeName += '.displayLabel.currentValue';
        }
        if(attrib.dtoType.contains('AttributeEnumerationDTO'))
        {
          row.attributeName += '.displayLabel.currentValue';
        }
        row.key = attrib.attributeName + this.suffix;
        row.type = this.obj.getType();
        row.dtoType = attrib.dtoType;
        row.displayLabel = attrib.attributeMdDTO.displayLabel;
        var uppFirst = attrib.attributeName.slice(0,1).toUpperCase() + attrib.attributeName.slice(1);
        if(this.dropDownMaps[uppFirst]){
          row.dropDownMap = this.dropDownMaps[uppFirst];
        }
      }else{
        row.attributeName = attribName;
        row.type = 'sqlcharacter';
        row.displayLabel = attribName;
        row.key = attribName;

      }
      return row;
    };


    var mapMo = function(term,index){
    	var row = {};
        //row.attributeName = this.relAttribute;
        //row.key = 'term' + term.MOID.replace(':','') +'_'+ term.id;
        //row.type = this.relType;
        //row.dtoType = "com.terraframe.mojo.AttributeasdfDTO";
        row.displayLabel = term.displayLabel;
        
        row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;;
        row.type = 'sqlcharacter';
        row.attributeName = 'term' + term.MOID.replace(':','');
        
      return row;
    };

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    iptMaps = {<%=(String) request.getAttribute("iptMap")%>};

    orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var aggreatedIPT = new Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT();

    
    var aIPTAttribs = ["startDate","endDate","numberNatalCare","numberPregnant","numberPregnantITN","numberPregnantIron","totalITN"];


    
    aIPTColumns =   aIPTAttribs.map(mapAttribs, {obj:aggreatedIPT, suffix:'_aipt', dropDownMaps:iptMaps});

    
    //IPTANCVisit[] getIPTANCVisits()
    //IPTDose[] getIPTDoses()
    //IPTPatients[] getIPTPatients()
    //IPTTreatment[] getIPTTreatments()

    
   dosesColumns = orderedGrids.doses.options.map(mapMo, orderedGrids.doses);
   patientsColumns = orderedGrids.doses.options.map(mapMo, orderedGrids.patients);
   treatmentsColumns = orderedGrids.doses.options.map(mapMo, orderedGrids.treatments);
   visitsColumns = orderedGrids.doses.options.map(mapMo, orderedGrids.visits);
    
    var selectableGroups = [
              {title:"Aggreated_IPT", values:aIPTColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.AggregatedIPT.CLASS},
              {title:"Doses", values:dosesColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTDose.CLASS},
              {title:"Patients", values:patientsColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTPatients.CLASS},
              {title:"Treatments", values:treatmentsColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTTreatment.CLASS},
              {title:"Visits", values:visitsColumns, group:"ipt", klass:Mojo.$.dss.vector.solutions.intervention.monitor.IPTANCVisit.CLASS}
    ];

    var query = new MDSS.QueryAggreatedIPT(selectableGroups, queryList);
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

</div>

<div style="display: none" id="XLSFormContainer"></div>
<div style="display: none" id="CSVFormContainer"></div>
<div style="display: none" id="ReportFormContainer"></div>
<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>

<textarea id="debug_xml" cols="40" rows="40" style="width:1280px"> </textarea>

<jsp:include page="../templates/footer.jsp"></jsp:include>
