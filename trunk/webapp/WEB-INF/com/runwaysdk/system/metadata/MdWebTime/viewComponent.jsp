<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_MdWebTime" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebTime.form.id" name="com.runwaysdk.system.metadata.MdWebTime.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="definingMdAttribute">
        ${item.definingMdAttribute.keyName}
      </mjl:dt>
      <mjl:dt attribute="definingMdForm">
        ${item.definingMdForm.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="fieldName">
        ${item.fieldName}
      </mjl:dt>
      <mjl:dt attribute="fieldOrder">
        ${item.fieldOrder}
      </mjl:dt>
      <mjl:dt attribute="required">
        ${item.required ? item.requiredMd.positiveDisplayLabel : item.requiredMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="description">
        ${item.description}
      </mjl:dt>
      <mjl:dt attribute="remove">
        ${item.remove ? item.removeMd.positiveDisplayLabel : item.removeMd.negativeDisplayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="com.runwaysdk.system.metadata.MdWebTime.form.edit.button" value="Edit" action="com.runwaysdk.system.metadata.MdWebTimeController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.system.metadata.MdWebTime.viewAll.link" action="com.runwaysdk.system.metadata.MdWebTimeController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>