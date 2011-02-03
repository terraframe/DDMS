<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<c:set scope="request" var="page_title" value="Edit_MenuRoot" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.MenuItem.form.id" name="dss.vector.solutions.general.MenuItem.form.name" method="POST">
  <mjl:input param="id" value="${item.id}" type="hidden" />
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="menuRoot">
    <mdss:mo param="menuRoot" value="${term}" />
  </mjl:dt>
</mjl:component>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command name="dss.vector.solutions.general.MenuItem.form.updateDisease.button" value="${Localized_Update}" action="dss.vector.solutions.general.MenuItemController.updateDisease.mojo" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command name="dss.vector.solutions.general.MenuItem.form.cancelDisease.button" value="${Localized_Cancel}" action="dss.vector.solutions.general.MenuItemController.cancelDisease.mojo" />
  </mjl:form>
</dl>