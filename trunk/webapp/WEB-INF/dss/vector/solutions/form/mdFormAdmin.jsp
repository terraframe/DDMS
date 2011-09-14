<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

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

<c:set var="page_title" value="MdForm_Admin"  scope="request"/>

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
//MdWebFloatDTO.CLASS, MdWebFloatController.CLASS,

// WebMoment (excluding DateTime, Time)
MdWebDateDTO.CLASS,
// Character, Text, Boolean
MdWebCharacterDTO.CLASS,
MdWebTextDTO.CLASS,
MdWebBooleanDTO.CLASS
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
    <a href="#" class="create-new-form-button">Create New Form</a>
    
    <!-- start tree control -->
    <ul class="tree" id="existingForms">
    </ul>
    <!-- end tree control -->

</div>
<!-- end side bar for tree -->

<div class="form-content">

  <!-- start form attributes -->
  <h4>Form Attributes</h4>
    <div class="form-content-box" id="formContentBox">
        <ul class="form-row">
            <li>
                <label>Form Name</label>
                <input id="formName" type="text">
            </li>
            <li>
                <label>Form Display Label</label>
                <input id="formDisplayLabel" type="text">
            </li>
            <li>
                <label>Form Type</label>
                <input id="formType" type="text">
            </li>
         </ul>
         <div class="form-action-row">
            <a href="#" class="form-standard-button">Update</a>         
          <a href="#" class="form-standard-button">Cancel</a>
         </div>
    </div>
  <!-- end form attributes -->    

  <!-- start fields -->
  <div class="tabbed-form-box">
        <ul class="tabs">
            <li class="tabs-on"><a href="#">Fields</a></li>
            <li><a href="#">Workflow</a></li>
        </ul>
        <div class="form-top-action-row">
            <a href="#" class="form-button-done">Done</a>
            <a href="#" class="form-button-add" id="availableFields">Add Field</a>
        </div>
        <ul class="form-item-row">
            <li><input value="First Name" type="text">
            <a href="#" class="form-item-row-delete">Delete</a>
            </li>
            <li><input value="Last Name" type="text">
            <a href="#" class="form-item-row-delete">Delete</a>            
            </li>
            <li><input value="Phone #" type="text">
            <a href="#" class="form-item-row-delete">Delete</a>
            </li>
            <li><input value="Email" type="text">
            <a href="#" class="form-item-row-delete">Delete</a>            
            </li>            
        </ul>
    </div>
  <!-- end fields -->    

</div>

<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>