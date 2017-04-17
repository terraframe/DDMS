<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_MdWebForm" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebForm.form.id" name="com.runwaysdk.system.metadata.MdWebForm.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="formMdClass">
        ${item.formMdClass.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="formName">
        ${item.formName}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="exported">
        ${item.exported ? item.exportedMd.positiveDisplayLabel : item.exportedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="jsBase">
        ${item.jsBase}
      </mjl:dt>
      <mjl:dt attribute="jsStub">
        ${item.jsStub}
      </mjl:dt>
      <mjl:dt attribute="packageName">
        ${item.packageName}
      </mjl:dt>
      <mjl:dt attribute="typeName">
        ${item.typeName}
      </mjl:dt>
      <mjl:dt attribute="description">
        ${item.description}
      </mjl:dt>
      <mjl:dt attribute="remove">
        ${item.remove ? item.removeMd.positiveDisplayLabel : item.removeMd.negativeDisplayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="com.runwaysdk.system.metadata.MdWebForm.form.edit.button" value="Edit" action="com.runwaysdk.system.metadata.MdWebFormController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.system.metadata.MdWebForm.viewAll.link" action="com.runwaysdk.system.metadata.MdWebFormController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
