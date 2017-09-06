<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_OfflineBasemapManagement" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.name" id="dss.vector.solutions.basemap.OfflineBasemapManagement.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="config">
        ${item.config}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.edit.button" action="dss.vector.solutions.basemap.OfflineBasemapManagementController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.basemap.OfflineBasemapManagement.viewAll.link" action="dss.vector.solutions.basemap.OfflineBasemapManagementController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
