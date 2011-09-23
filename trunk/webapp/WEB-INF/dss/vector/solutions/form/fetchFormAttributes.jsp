<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.system.metadata.MdWebFormDTO"%>
<%@page import="dss.vector.solutions.form.MdFormAdminController"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<mjl:form name="MdFormAdmin.form.name" id="MdWebFormAdmin.form.id" method="POST">
<mjl:component param="form" item="${form}"/>
<mjl:input value="${form.id}" type="hidden" param="id" id="MdFormId"/>

  <!-- start form attributes -->
  <h4>Form Attributes</h4>
    <div class="form-content-box" id="formContentBox">
			<ul class="form-row">
			    <li>
			        <label>Form Name</label>
			        <label>${form.formName}</label>
			    </li>
			    <li>
			        <label>Form Display Label</label>
			        <label>${form.displayLabel}</label>
			    </li>
			</ul>
			<div class="form-action-row" id="formActionRow">
			    <mdss:localize key="Edit" var="Localized_Edit" />
          <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.form.MdFormAdminController.editFormAttributes.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.editFormAttributes.button"/>
			    <mdss:localize key="Delete" var="Localized_Delete" />
          <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.form.MdFormAdminController.delete.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.delete.button"/>
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
        <ul class="form-item-row" id="formItemRow">
        </ul>
    </div>
  <!-- end fields -->    

</mjl:form>