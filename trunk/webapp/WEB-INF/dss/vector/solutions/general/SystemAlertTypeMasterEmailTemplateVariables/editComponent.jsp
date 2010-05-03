<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_SystemAlertTypeMasterEmailTemplateVariables" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.id" name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command localize="false" name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.update.button" value="Update" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.update.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.delete.button" value="Delete" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.delete.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.cancel.button" value="Cancel" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.cancel.mojo" />
  </mjl:form>
</dl>
