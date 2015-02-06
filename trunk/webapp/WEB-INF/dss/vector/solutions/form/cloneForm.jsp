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
	  <%@ include file="form.jsp"%>
	</ul>
	
	<div class="form-action-row" id="formActionRow">
    <mdss:localize key="Clone" var="Localized_Clone" />
    <mjl:command value="${Localized_Clone}" action="dss.vector.solutions.form.MdFormAdminController.clone.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.clone.button"/>
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.form.MdFormAdminController.cancelViewClone.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.cancelViewClone.button" />
	</div>
</mjl:form>