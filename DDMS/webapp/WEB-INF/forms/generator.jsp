<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@page import="dss.vector.solutions.generator.MdWebIndicatorDTO"%>
<%@page import="dss.vector.solutions.generator.MdFormUtilDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.AttributeGeoHierarchyDTO"%>

<%@page import="dss.vector.solutions.form.FormObjectController"%>

<%@page import="com.runwaysdk.system.metadata.MdWebInteger"%>
<%@page import="com.runwaysdk.system.metadata.MdWebLongDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebIntegerDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDecimalDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDoubleDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebFloatDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebDateDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebTextDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebBooleanDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCharacterDTO"%>



<%@page import="dss.vector.solutions.form.MdFormAdminController"%>
<%@page import="com.runwaysdk.system.metadata.MdWebBreakDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCommentDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebHeaderDTO"%>

<%@page import="com.runwaysdk.system.metadata.MdWebReferenceDTO"%>


<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>

<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>

<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>

<jsp:include page="../templates/header.jsp" />

<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/jqueryBundle.js" useRandomParam="false"/>
<jwr:script src="/bundles/formBundle.js" useRandomParam="false"/>

<script type="text/javascript">


<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

String[] types = new String[]{
request.getAttribute("mdClassType").toString(),
FormObjectController.CLASS,
MdFormUtilDTO.CLASS,
    
// WebNumber (excluding float)
MdWebIntegerDTO.CLASS,
MdWebLongDTO.CLASS,
MdWebDecimalDTO.CLASS,
MdWebDoubleDTO.CLASS,
MdWebFloatDTO.CLASS,

// WebMoment (excluding DateTime, Time)
MdWebDateDTO.CLASS,
// Character, Text, Boolean
MdWebCharacterDTO.CLASS,
MdWebTextDTO.CLASS,
MdWebBooleanDTO.CLASS,
MdWebReferenceDTO.CLASS,

MdWebBreakDTO.CLASS,
MdWebHeaderDTO.CLASS,
MdWebCommentDTO.CLASS,

MdWebIndicatorDTO.CLASS,

TermViewDTO.CLASS,
TermDTO.CLASS,
BrowserRootDTO.CLASS,

GeoEntityTreeController.CLASS,
GeoEntityDTO.CLASS,
GeoEntityViewDTO.CLASS,

QueryBuilderDTO.CLASS,

MosquitoCollectionViewDTO.CLASS
};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>

Mojo.Meta.newClass("MDSS.ValidationBridge", {

  IsSingleton : true,
  
  Instance : {
  
    initialize : function()
    {
    },
    
    setHandler : function(handler) {
      this._handler = handler;
    },

    handleEvent : function(evt){      
      if(this._handler != null)
      {
        if(evt instanceof dss.vector.solutions.ontology.TermSelectedEvent)
        {        
          if(!evt.getOnOpen())
          {
            this._handler.validate(evt);          
          }
        }
        else
        {        
          this._handler.validate(evt);
        }
      }
    }    
  },
});

YAHOO.util.Event.onDOMReady(function(){
  var generator = new dss.vector.solutions.FormObjectGenerator('','${mdFormId}', '${mdClassType}', ${fields}, ${viewAllFields}, ${searchFields}, ${canDeleteAll});
  generator.render();
});
</script>

<jsp:include page="../selectSearch.jsp"/>

<div class="generatorContent" id="generatorContent">
  <h2 class="pageTitle">${localized_page_title}</h2>
  <div id="SearchContainer">
  </div>
  <a href="#" id="NewInstanceCommand" style="display:none">
    <mdss:localize key="New_Instance" />
  </a>
  <br id="OptionBreak" />
  <a href="#" id="ViewAllCommand">
    <mdss:localize key="View_All" />
  </a>
	<div id="TableContainer" class="yui3-skin-sam">
	</div>
	<div id="FormContainer">
	</div>
	
  <div id="ExcelButtons">
	<form action="dss.vector.solutions.generator.ExcelController.excelExport.mojo" method="post">
	  <input type="hidden" value="${mdFormType}" name="excelType"/>
	  <mdss:localize key="Excel_Export_Header" var="export_label"/>
	  <input type="submit" class="submitButton" name="export.button" value="${export_label}"/>
	</form>
	<form id="mobile.export.button" name="${mdClassType}.mobile" action="mobile/export" method="get">
    <input type="hidden" value="${mdClassType}" name="type"/>
    <mdss:localize key="Mobile_Export_Button" var="mobile_label"/>
    <input type="submit" class="mobileButton" name="mobile.button" value="${mobile_label}"/>
  </form>
	<form action="dss.vector.solutions.generator.ExcelController.importType.mojo" method="post">
	  <mdss:localize key="Excel_Import_Header" var="import_label"/>
	  <input type="hidden" value="${mdFormType}" name="excelType"/>
	  <input type="submit" class="submitButton" name="import.button" value="${import_label}"/>
	</form>
  </div>
</div>
<jsp:include page="../templates/footer.jsp"></jsp:include>