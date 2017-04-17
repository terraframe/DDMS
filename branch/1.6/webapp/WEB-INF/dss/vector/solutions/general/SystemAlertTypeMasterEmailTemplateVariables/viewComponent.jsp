<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_SystemAlertTypeMasterEmailTemplateVariables" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.id" name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="defaultLocale">
        ${item.defaultLocale}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariables.viewAll.link" action="dss.vector.solutions.general.SystemAlertTypeMasterEmailTemplateVariablesController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
