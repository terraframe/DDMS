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
<%@page import="dss.vector.solutions.form.MdFormAdminController"%>


<%@page import="com.runwaysdk.system.metadata.MdWebBreakDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebHeaderDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebCommentDTO"%><c:set var="page_title" value="MdForm_Admin"  scope="request"/>

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
    <a href="#" class="create-new-form-button" id="createNewForm"><mdss:localize key="Create_a_new_form" /></a>
    
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
            <li class="tabs-on"><a href="#">Fields</a></li>
            <li><a href="#">Workflow</a></li>
        </ul>
        <div class="form-top-action-row">
            <a href="#" class="form-button-add edit-mode-functionality" id="availableFields">Add Field</a>
        </div>
        <ul class="form-item-row" id="formItemRow">
        </ul>
    </div>
  <!-- end fields -->    
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>