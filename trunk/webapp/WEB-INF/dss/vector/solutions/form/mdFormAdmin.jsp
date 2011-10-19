<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

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
<%@page import="com.runwaysdk.system.metadata.MdFieldDisplayLabelDTO"%>
<%@page import="dss.vector.solutions.form.MdFormAdminController"%>
<%@page import="dss.vector.solutions.generator.MdFormUtilDTO"%>


<%@page import="com.runwaysdk.system.metadata.MdWebBreakDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebHeaderDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCommentDTO"%>

<c:set var="page_title" value="MdForm_Admin"  scope="request"/>

<style type="text/css">
.yui3-tab-panel {
    display:none;
}

.yui3-tab-panel-selected {
    display:block;
}

.yui3-tabview-list,
.yui3-tab {
    margin:0;
    padding:0;
    list-style:none;
}

.yui3-tabview {
    position:relative; /* contain absolute positioned tabs (left/right) */
}

.yui3-tabview,
.yui3-tabview-list,
.yui3-tabview-panel,
.yui3-tab,
.yui3-tab-panel { /* IE: kill space between horizontal tabs */
    zoom:1;
}

.yui3-tab {
    display:inline-block;
    *display:inline; /* IE */
    vertical-align:bottom; /* safari: for overlap */
    cursor:pointer;
}

.yui3-tab-label {
    display:block;
    display:inline-block;
    padding: 6px 10px;
    position:relative; /* IE: to allow overlap */
    text-decoration: none;
    vertical-align:bottom; /* safari: for overlap */
}

.yui3-tab-panel {
    display: none;
}

.yui3-tab-selected {
  border-right: solid 1px #d5d6d7;  
  border-top: solid 1px #d5d6d7;  
  border-left: solid 1px #d5d6d7; 
  background: #FFF; 
  border-top-right-radius: 8px;
  border-top-left-radius: 8px; 
}

.yui3-tab-selected {
}

.yui3-tab-panel-selected {
    display: block;
}

.yui3-tab {
    display: inline-block;
    margin-right: 0.5em;
    zoom: 1;
};
</style>

<style type="text/css">
body {
  margin: 0px;
  padding: 0px;
  background-image: url('/DDMS/imgs/side-bar-back.gif');
  background-repeat: repeat-y;
  font-family:Arial, Helvetica, sans-serif;
}
</style>

<jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<script type="text/javascript">
<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

//String[] types = new String[]{FormObjectController.CLASS};
String[] types = new String[]{
MdFormAdminController.CLASS,
MdFormUtilDTO.CLASS,
MdFieldDisplayLabelDTO.CLASS,
    
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

// Display related
MdWebBreakDTO.CLASS,
MdWebHeaderDTO.CLASS,
MdWebCommentDTO.CLASS
};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>
</script>

<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

  new dss.vector.solutions.MdFormAdmin().render();
});
</script>
<!-- start side bar for tree -->
<div class="form-side-bar">
    <a href="#" class="create-new-form-button" id="createNewForm"><mdss:localize key="Forms" /></a>
    
    <!-- start tree control -->
    <ul class="tree" id="existingForms">
    </ul>
    <!-- end tree control -->

</div>
<!-- end side bar for tree -->

<div class="form-content" id="formContent">

  <!-- start form attributes -->
    <div class="form-content-box" id="formContentBox">
    </div>
  <!-- end form attributes -->    

  <!-- start fields -->
  <div class="tabbed-form-box" id="tabbedFormBox">
        <ul class="tabs">
            <li><a href="#fieldsTab"><mdss:localize key="Fields"/></a></li>
            <li><a href="#workflowTab"><mdss:localize key="Workflow" /></a></li>
        </ul>
        <div>
	        <div id="fieldsTab">
		        <div class="form-top-action-row">
		            <a href="#" class="form-button-add edit-mode-functionality" id="availableFields"></a>
		        </div>
		        <ul class="form-item-row" id="formItemRow">
		        </ul>
	        </div>
	        <div id="workflowTab">
	          <div class="form-top-action-row" id="workflowTree"></div>
	        </div>
        </div>
    </div>
  <!-- end fields -->    
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>