<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_WebFormField" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.WebFormField.form.id" name="com.runwaysdk.system.metadata.WebFormField.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="com.runwaysdk.system.metadata.MdWebForm.form.view.link" action="com.runwaysdk.system.metadata.MdWebFormController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="com.runwaysdk.system.metadata.MdWebField.form.view.link" action="com.runwaysdk.system.metadata.MdWebFieldController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="com.runwaysdk.system.metadata.WebFormField.form.edit.button" value="Edit" action="com.runwaysdk.system.metadata.WebFormFieldController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.system.metadata.WebFormField.viewAll.link" action="com.runwaysdk.system.metadata.WebFormFieldController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
