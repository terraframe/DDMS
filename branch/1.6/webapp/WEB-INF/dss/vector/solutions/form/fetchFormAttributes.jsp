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
  <input type="hidden" name="mdFormId" value="${form.id}">

			<ul class="form-row">
              <c:if test="${form.formNameReadable}">
                <li>
                  <label>${form.formNameMd.displayLabel}</label>
                  ${form.formName}
                </li>
              </c:if>
              <c:if test="${form.displayLabelReadable}">
                <li>
                  <label>${form.displayLabelMd.displayLabel}</label>
                  ${form.displayLabel}
                </li>
              </c:if>			
			</ul>
			<div class="form-action-row" id="formActionRow">
			    <mdss:localize key="Edit" var="Localized_Edit" />
          <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.form.MdFormAdminController.editFormAttributes.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.editFormAttributes.button"/>
			    <mdss:localize key="Delete" var="Localized_Delete" />
          <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.form.MdFormAdminController.delete.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.delete.button"/>
			    <mdss:localize key="Export" var="Localized_Export" />
          <mjl:command value="${Localized_Export}" action="dss.vector.solutions.form.MdFormAdminController.exportDefinition.mojo" name="export.button"/>
          <c:if test="${form.keyName != 'dss.vector.solutions.form.FormHousehold' && form.keyName != 'dss.vector.solutions.form.FormSurvey' && form.keyName != 'dss.vector.solutions.form.FormPerson' && form.keyName != 'dss.vector.solutions.form.FormBedNet'}">
            <mdss:localize key="Clone" var="Localized_Clone" />
            <mjl:command value="${Localized_Clone}" action="dss.vector.solutions.form.MdFormAdminController.viewClone.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.viewClone.button"/>
            <mdss:localize key="Dataset" var="Dataset" />
            <mjl:command value="${Dataset}" action="dss.vector.solutions.form.MdFormAdminController.exportDataset.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.exportDataset.button"/>
          </c:if>
			</div>

</mjl:form>