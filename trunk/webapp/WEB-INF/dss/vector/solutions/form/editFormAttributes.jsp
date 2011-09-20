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
<mjl:input value="${form.id}" type="hidden" param="id" />
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
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.form.MdFormAdminController.update.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.update.button"/>
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.form.MdFormAdminController.cancel.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.cancel.button" />
</div>
</mjl:form>