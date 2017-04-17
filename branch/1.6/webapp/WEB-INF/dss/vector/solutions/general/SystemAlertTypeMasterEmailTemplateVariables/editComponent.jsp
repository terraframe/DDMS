<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_SystemAlertTypeMasterEmailTemplateVariables" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.id" name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.update.button" value="${Localized_Update}" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.update.mojo" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.delete.button" value="${Localized_Delete}" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.delete.mojo" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.cancel.button" value="${Localized_Cancel}" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.cancel.mojo" />
  </mjl:form>
</dl>
