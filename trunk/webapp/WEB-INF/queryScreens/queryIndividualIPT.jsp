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
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.system.metadata.MdAttributeVirtualDTO"%>
<%@page import="org.json.JSONException"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeConcreteInfo"%>
<%@page import="com.terraframe.mojo.constants.MdAttributeVirtualInfo"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTViewDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualIPTDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
<%@page import="dss.vector.solutions.PersonDTO"%>




<%@page import="com.terraframe.mojo.business.BusinessDTO"%><c:set var="page_title" value="Query_Individual_IPT"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] mosquitoTypes = new String[]{ IndividualIPTViewDTO.CLASS, IndividualIPTDTO.CLASS, PersonDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS};


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
          //row.attributeName += '.name';
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
        row.type = 'sqlinteger';
        row.displayLabel = attribName;
        row.key = attribName;
        row.dtoType = "AttributeIntegerDTO";

      }
      return row;
    };


    var mapMo = function(term,index){
    	var row = {};
        //row.attributeName = this.relAttribute;
        //row.key = 'term' + term.MOID.replace(':','') +'_'+ term.id;
        //row.type = this.relType;
        row.dtoType = "AttributeIntegerDTO";
        row.displayLabel = term.displayLabel;
        
        row.key = this.relAttribute +'__'+ this.relType.replace(/[.]/g,'_') +'__'+ term.id;;
        row.type = 'sqlinteger';
        row.attributeName = 'term' + term.MOID.replace(':','');
        
      return row;
    };

    // TODO move into QueryPanel, and pass el ids as params
	var tabs = new YAHOO.widget.TabView("tabSet");

    var queryList = <%= (String) request.getAttribute("queryList") %>;

    var iptMaps = {<%=(String) request.getAttribute("iptMap")%>};
    
    var personMaps = {<%=(String) request.getAttribute("iptMap")%>};

    var orderedGrids = <%=(String) request.getAttribute("orderedGrids")%>;

    var individualIPT = new Mojo.$.dss.vector.solutions.intervention.monitor.IndividualIPT();

    var iIPTAttribs = ["serviceDate",
                       "doseNumber","doseType","isANCVisit",
                       "numberOfRecievedITNs","patientType","recievedITN",
                       "recievedSupplement","visitNumber","administratorName","administratorSurname",];

    
    var iIPTColumns =   iIPTAttribs.map(mapAttribs, {obj:individualIPT, suffix:'_ipt', dropDownMaps:iptMaps});

    var person = new Mojo.$.dss.vector.solutions.Person();
    
    var personAttribs = ["dateOfBirth","firstName","lastName","sex","age","residentialInformation","workInformation"];
    
    var personColumns =  personAttribs.map(mapAttribs, {obj:person, suffix:'_per', dropDownMaps:personMaps});

    
    var selectableGroups = [
              {title:"IPT", values:iIPTColumns, group:"ipt", klass:individualIPT.CLASS},
              {title:"Patient", values:personColumns, group:"ipt", klass:individualIPT.CLASS}
    ];

    var query = new MDSS.QueryIndividualIPT(selectableGroups, queryList);
    query.render();

});

</script>
<jsp:include page="queryContainer.jsp"></jsp:include>


<textarea id="debug_xml" cols="40" rows="40" style="width:1280px"> </textarea>

<jsp:include page="../templates/footer.jsp"></jsp:include>
