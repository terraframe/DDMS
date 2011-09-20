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
    <a href="#"  id="editBtn">${Localized_Edit}</a>
</div>
</mjl:form>