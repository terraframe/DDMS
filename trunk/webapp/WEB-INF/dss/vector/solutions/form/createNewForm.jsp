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

  <!-- start form attributes -->
  <h4>Form Attributes</h4>
    <div class="form-content-box" id="formContentBox">
			<ul class="form-row">
			    <li>
			        <label>Form Name</label>
			        <mjl:input value="${form.formName}" type="text" param="form.formName" />
			    </li>
			    <li>
			        <label>Form Display Label</label>
			        <mjl:input value="${form.displayLabel}" type="text" param="form.displayLabel" />
			    </li>
			</ul>
			<div class="form-action-row" id="formActionRow">
			    <mdss:localize key="Create" var="Localized_Create" />
			    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.form.MdFormAdminController.create.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.create.button"/>
			    <mdss:localize key="Cancel" var="Localized_Cancel" />
			    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.form.MdFormAdminController.cancel.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.cancel.button" />
			</div>      
    </div>
</mjl:form>