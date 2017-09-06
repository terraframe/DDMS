<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="Edit_OfflineBasemapManagement" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.name" id="dss.vector.solutions.basemap.OfflineBasemapManagement.form.id">
    <%@include file="form.jsp" %>
    <mdss:localize var="Update_Localize" key="Update" />
    <mjl:command name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.update.button" action="dss.vector.solutions.basemap.OfflineBasemapManagementController.update.mojo" value="${Update_Localize}" />
    <mdss:localize var="Delete_Localize" key="Delete" />
    <mjl:command name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.delete.button" action="dss.vector.solutions.basemap.OfflineBasemapManagementController.delete.mojo" value="${Delete_Localize}" />
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.cancel.button" action="dss.vector.solutions.basemap.OfflineBasemapManagementController.cancel.mojo" value="${Cancel_Localize}" />
  </mjl:form>
</dl>
