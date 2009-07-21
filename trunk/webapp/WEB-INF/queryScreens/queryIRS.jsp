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
<%@page import="dss.vector.solutions.query.SavedSearchViewDTO"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.ThematicLayerDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>
<%@page import="dss.vector.solutions.mo.SpecieDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.general.EpiDateDTO"%>
<%@page import="dss.vector.solutions.irs.*"%>


<c:set var="page_title" value="Query_IRS"  scope="request"/>

<jsp:include page="../templates/header.jsp"/>
<jsp:include page="/WEB-INF/inlineError.jsp"/>
<jwr:script src="/bundles/queryBundle.js" useRandomParam="false"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String[] types = new String[]{ AbstractSprayDTO.CLASS};
    String[] queryTypes = new String[]{EpiDateDTO.CLASS, ThematicLayerDTO.CLASS, ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS, NonRangeCategoryController.CLASS, MappingController.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS};
%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types))%>
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

    dropDownMaps = {<%//=Halp.getDropDownMaps(mosquitoView,  requestIF)%>};



    function mapAssayAttribs(arr){
      return arr.map(function(row){
          if(dropDownMaps[row.attributeName]){
            row.dropDownMap = dropDownMaps[row.attributeName];
          }
          var splitType = row.dtoType.split('.');
          row.dtoType = splitType[splitType.length - 1];
          return row;
      });
    }

    selectableGroups = [
                        {title:"Planed_Targets",
                        	values:[
                                  {
                                    displayLabel:"Spray Area Target",
                                    dtotype:"sqlcharacter",
                                    type:"sqlcharacter",
                                    attributeName:"SPRAY_AREA_TARGET",
                                  },
                                  {
                                    displayLabel:"Oprator Target",
                                    dtotype:"sqlcharacter",
                                    type:"sqlcharacter",
                                    attributeName:"OPRATOR_TARGET",
                                  },
                                  {
                                    displayLabel:"Team Target",
                                    dtotype:"sqlcharacter",
                                    type:"sqlcharacter",
                                    attributeName:"test",
                                  }
                                  ]},
                        {title:"Actual_Targets",
                        	values:[
                                  {
                                    displayLabel:"Oprator Target",
                                    dtotype:"sqlcharacter",
                                    type:"sqlcharacter",
                                    attributeName:"test",
                                  },
                                  {
                                    displayLabel:"Team Target",
                                    dtotype:"sqlcharacter",
                                    type:"sqlcharacter",
                                    attributeName:"test",
                                  }
                                  ]},
                         {title:"Spray_Details",
                        	 values:[
                                   {
                                     displayLabel:"Spray Method",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Total Structures",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Total Households",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Total Rooms",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Reason Locked",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Reason Refused",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Reason Other",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Insectcide Active Ingredint",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Instecticde Brand Name",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Insectcide Refills",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Insecticde Sachets Used",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Unsprayed Strucures",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Unsprayed Households",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Unsprayed Rooms",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   }
                                   ]},
                         {title:"Coverege",
                         	values:[
                                   {
                                     displayLabel:"Operational Coverage",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Planned Coverage",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Application Rate",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Application Ratio",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   }
                                   ]},
                         {title:"HouseHold_Structure_Detail",
                         	values:[
                                   {
                                     displayLabel:"Number Of People Protected",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Number Of Rooms With Bed Nets",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Total Number Of Bed Nets",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"Structures Sprayed Last Year",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   },
                                   {
                                     displayLabel:"HouseHolds Sprayed Last Year",
                                     dtotype:"sqlcharacter",
                                     type:"sqlcharacter",
                                     attributeName:"test",
                                   }
                                   ]},
                          {title:"Spray_Team_Detail",
                          	 values:[
                                     {
                                       displayLabel:"Spray Team",
                                       dtotype:"sqlcharacter",
                                        type:"sqlcharacter",
                                       attributeName:"test",
                                     },
                                     {
                                       displayLabel:"Spray Team Week",
                                       dtotype:"sqlcharacter",
                                       type:"sqlcharacter",
                                       attributeName:"test",
                                     },
                                     {
                                       displayLabel:"Operator",
                                       dtotype:"sqlcharacter",
                                       type:"sqlcharacter",
                                       attributeName:"test",
                                     },
                                     {
                                       displayLabel:"Oporator Spray Week",
                                       dtotype:"sqlcharacter",
                                       type:"sqlcharacter",
                                       attributeName:"test",
                                     }
                             ]}
    ];


    var query = new MDSS.QueryIRS(selectableGroups, queryList);
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
