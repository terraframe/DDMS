<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_MDSSRole" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.permission.MDSSRole.form.id" name="dss.vector.solutions.permission.MDSSRole.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.permission.MDSSRole.form.edit.button" value="Edit" action="dss.vector.solutions.permission.MDSSRoleController.edit.mojo" />
    <mjl:command name="url.button" value="Edit_URL_Permissions" action="dss.vector.solutions.permission.MDSSRoleController.getPermissions.mojo" />
    <mjl:command name="universal.button" value="Edit_Universal_Permissions" action="dss.vector.solutions.permission.MDSSRoleController.getUniversalPermissions.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.permission.MDSSRole.viewAll.link" action="dss.vector.solutions.permission.MDSSRoleController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
