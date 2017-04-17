<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_MdForm" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.form.MdFormAdminController.id" name="dss.vector.solutions.form.MdFormAdminController.name" method="POST">
    <mjl:input value="${form.id}" type="hidden" param="id" />
    <mjl:component param="form" item="${form}">
      <mjl:dt attribute="formName">
        ${form.formName}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        ${form.displayLabel}
      </mjl:dt>

    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="MdFormAdminController.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.form.MdFormAdminController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="MdFormAdminController.viewAll.link" action="dss.vector.solutions.form.MdFormAdminController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
