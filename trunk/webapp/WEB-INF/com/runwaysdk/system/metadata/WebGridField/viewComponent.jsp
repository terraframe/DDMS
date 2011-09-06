<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_WebGridField" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.WebGridField.form.id" name="com.runwaysdk.system.metadata.WebGridField.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="com.runwaysdk.system.metadata.MdWebSingleTermGrid.form.view.link" action="com.runwaysdk.system.metadata.MdWebSingleTermGridController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="com.runwaysdk.system.metadata.MdWebPrimitive.form.view.link" action="com.runwaysdk.system.metadata.MdWebPrimitiveController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="com.runwaysdk.system.metadata.WebGridField.form.edit.button" value="Edit" action="com.runwaysdk.system.metadata.WebGridFieldController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.system.metadata.WebGridField.viewAll.link" action="com.runwaysdk.system.metadata.WebGridFieldController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
