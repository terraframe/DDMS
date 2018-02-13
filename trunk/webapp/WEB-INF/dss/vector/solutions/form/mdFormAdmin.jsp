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
<%@page import="com.runwaysdk.system.metadata.MdWebGroupDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebReferenceDTO"%>


<%@page import="dss.vector.solutions.util.LocalizationFacadeDTO"%>
<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.BrowserRootDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.GeoEntityDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>
<%@page import="dss.vector.solutions.util.OrientationTypeDTO"%><c:set var="page_title" value="MdForm_Admin"  scope="request"/>
<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
OrientationTypeDTO orientation =  LocalizationFacadeDTO.getSessionLocaleOrientation(requestIF);
%>

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
    display:table-cell;
    padding: 0 10px;
    /* padding: 6px 10px; */
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
  <% if (orientation.equals(OrientationTypeDTO.LTR)) { %>
  background-image: url('/DDMS/imgs/side-bar-back.gif');
  <% } else if (orientation.equals(OrientationTypeDTO.RTL)) { %>
  background-image: url('/DDMS/imgs/side-bar-back-rtl.gif');
  background-position: right;
  <% } %>
  background-repeat: repeat-y;
  font-family:Arial, Helvetica, sans-serif;
}
</style>

<jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
<jwr:script src="/bundles/yui3Bundle.js" useRandomParam="false"/>

<script type="text/javascript">
<%
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
MdWebReferenceDTO.CLASS,

// Indicator
MdWebIndicatorDTO.CLASS,

// Display related
MdWebBreakDTO.CLASS,
MdWebHeaderDTO.CLASS,
MdWebCommentDTO.CLASS,

MdWebGroupDTO.CLASS,

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
</script>

<jsp:include page="../../../../selectSearch.jsp"/>

<script type="text/javascript">

YAHOO.util.Event.onDOMReady(function(){

  new dss.vector.solutions.MdFormAdmin().render();
});
</script>
<!-- start side bar for tree -->
<% %>
<div class="form-side-bar">
    <a href="#" class="import-form-button" id="importForm"><mdss:localize key="Import" /></a>
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
		        <div class="form-item-row" id="formItemRow">
		        </div>
	        </div>
	        <div id="workflowTab">
            <div class="form-top-action-row">
              <div id="workflowTree">
              </div>
            </div>
	        </div>
        </div>
    </div>
  <!-- end fields -->    
</div>

<iframe id="exportIframe" name="exportIframe" style="display: none; width: 1px; height: 1px;"></iframe>

<div style="display: none">
  <form id="exportDefinition" target="exportIframe" method="POST" action="dss.vector.solutions.form.MdFormAdminController.exportDefinition.mojo">
        <input type="hidden" id="export_mdFormId" name="mdFormId" />
  </form>
</div>


<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>