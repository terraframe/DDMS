<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>


<mjl:form name="MdFormAdmin.form.name" id="MdWebFormAdmin.form.id">

<mjl:component param="form" item="${form}"/>

<mjl:input value="${form.id}" type="hidden" param="id" />
<ul class="form-row">
    <li>
        <label>Form Name</label>
        <input id="formName" type="text" value="${form.formName}">
    </li>
    <li>
        <label>Form Display Label</label>
        <input id="formDisplayLabel" type="text" value="${form.displayLabel}">
    </li>
    <li>
        <label>Form Type</label>
        <input id="formType" type="text">
    </li>
</ul>
<div class="form-action-row">
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.form.MdFormAdminController.update.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.update.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.form.MdFormAdminController.cancel.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.cancel.button"/>
</div>
</mjl:form>