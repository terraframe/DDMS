<%@page import="dss.vector.solutions.generator.MdWebIndicatorDTO"%>
<%@page import="dss.vector.solutions.query.QueryBuilderDTO"%>
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
<%@page import="com.runwaysdk.system.metadata.MdWebFormDTO"%>


<%@page import="com.runwaysdk.system.metadata.MdWebReferenceDTO"%>


<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<script type="text/javascript">


<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
MdWebFormDTO form = MdWebFormDTO.get(requestIF, request.getAttribute("surveyFormId").toString());
String formDisplayLabel = form.getDisplayLabel().getValue();

request.setAttribute("localized_page_title", formDisplayLabel);

String[] types = new String[]{
request.getAttribute("surveyClassType").toString(),
request.getAttribute("householdClassType").toString(),
request.getAttribute("bedNetClassType").toString(),
request.getAttribute("personClassType").toString(),
FormObjectController.CLASS,
MdWebFormDTO.CLASS,
    
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

MdWebIndicatorDTO.CLASS,

MdWebBreakDTO.CLASS,
MdWebHeaderDTO.CLASS,
MdWebCommentDTO.CLASS,

QueryBuilderDTO.CLASS
};



String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>
</script>
<script type="text/javascript">
YAHOO.util.Event.onDOMReady(function(){

  var params = {
    personFormId : '${personFormId}',
    personClassType : '${personClassType}',
    personFields : '${personFields}',
    personViewAllFields : '${personViewAllFields}',
    personSearchFields : '${personSearchFields}',
    bedNetFormId : '${bedNetFormId}',
    bedNetClassType : '${bedNetClassType}',
    bedNetFields : '${bedNetFields}',
    bedNetViewAllFields : '${bedNetViewAllFields}',
    bedNetSearchFields : '${bedNetSearchFields}',    
    householdFormId : '${householdFormId}',
    householdClassType: '${householdClassType}',
    householdFields : '${householdFields}',
    householdViewAllFields : '${householdViewAllFields}',
    householdSearchFields : '${householdSearchFields}',
    surveyFormId: '${surveyFormId}',
    surveyClassType: '${surveyClassType}',
    surveyFields : '${surveyFields}',
    surveyViewAllFields : '${surveyViewAllFields}',
    surveySearchFields : '${surveySearchFields}',
    canDeleteAll : ${canDeleteAll}
  };
  
  var generator = new dss.vector.solutions.SurveyFormGenerator(params);
  generator.render();
});
</script>
<div class="generatorContent" id="generatorContent">
  <div id="surveySearchContainer">
  </div>
  <a href="#" id="surveyNewInstanceCommand">
    <mdss:localize key="New_Form_Survey" />
  </a>
  <br id="surveyOptionBreak" />
  <a href="#" id="surveyViewAllCommand">
    <mdss:localize key="View_All_Form_Surveys" />
  </a>
  <div id="surveyTableContainer" class="yui3-skin-sam">
  </div>
  <div id="surveyFormContainer">
  </div>

  <div id="householdSearchContainer">
  </div>  
  <a href="#" id="householdNewInstanceCommand">
    <mdss:localize key="New_Form_Household" />
  </a>
  <br id="householdParentBreak" />
  <a href="#" id="householdViewParentCommand">
    <mdss:localize key="View_Form_Survey" />
  </a>
  <div id="householdTableContainer" class="yui3-skin-sam">
  </div>
  <div id="householdFormContainer">
  </div>

  <div id="bedNetSearchContainer">
  </div>  
  <a href="#" id="bedNetNewInstanceCommand">
    <mdss:localize key="New_Form_Bed_Net" />
  </a>
  <br id="bedNetParentBreak" />
  <a href="#" id="bedNetViewParentCommand">
    <mdss:localize key="View_Form_Household" />
  </a>
  <div id="bedNetTableContainer" class="yui3-skin-sam">
  </div>
  <div id="bedNetFormContainer">
  </div>
  
  <div id="personSearchContainer">
  </div>
  <a href="#" id="personNewInstanceCommand">
    <mdss:localize key="New_Form_Person" />
  </a>
  <br id="personParentBreak" />
  <a href="#" id="personViewParentCommand">
    <mdss:localize key="View_Form_Household" />
  </a>
  <div id="personTableContainer" class="yui3-skin-sam">
  </div>
  <div id="personFormContainer">
  </div>
  
  <div id="surveyExcelButtons">
    <form action="dss.vector.solutions.generator.ExcelController.surveyExcelExport.mojo" method="post">
      <mdss:localize key="Excel_Export_Header" var="export_label"/>
      <input type="submit" class="submitButton" name="export.button" value="${export_label}"/>
    </form>  

	<form action="dss.vector.solutions.generator.ExcelController.surveyImportType.mojo" method="post">
	  <mdss:localize key="Excel_Import_Header" var="import_label"/>
	  <input type="submit" class="submitButton" name="import.button" value="${import_label}"/>
	</form>    
  </div>  
</div>