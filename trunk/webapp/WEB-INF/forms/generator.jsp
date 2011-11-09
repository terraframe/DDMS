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
<jsp:include page="../templates/header.jsp" />

<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<script type="text/javascript">


<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

String[] types = new String[]{
request.getAttribute("mdClassType").toString(),
FormObjectController.CLASS,
    
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

TermViewDTO.CLASS,
TermDTO.CLASS,
BrowserRootDTO.CLASS,

GeoEntityTreeController.CLASS,
GeoEntityDTO.CLASS,
GeoEntityViewDTO.CLASS
};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>

YAHOO.util.Event.onDOMReady(function(){
  var generator = new dss.vector.solutions.FormObjectGenerator('','${mdFormId}', '${mdClassType}');
  generator.render();
});
</script>

<jsp:include page="../selectSearch.jsp"/>

<div class="generatorContent" id="generatorContent">
  <a href="#" id="NewInstanceCommand">
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
	  <input type="hidden" value="${mdFormType}" name="type"/>
	  <mdss:localize key="Excel_Export_Header" var="export_label"/>
	  <input type="submit" class="submitButton" name="export.button" value="${export_label}"/>
	</form>
	
	<form action="dss.vector.solutions.generator.ExcelController.importType.mojo" method="post">
	  <mdss:localize key="Excel_Import_Header" var="import_label"/>
	  <input type="hidden" value="${mdFormType}" name="type"/>
	  <input type="submit" class="submitButton" name="import.button" value="${import_label}"/>
	</form>
  </div>
</div>
<jsp:include page="../templates/footer.jsp"></jsp:include>