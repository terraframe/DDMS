<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="Create_OfflineBasemapManagement" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.name" id="dss.vector.solutions.basemap.OfflineBasemapManagement.form.id">
    <%@include file="form.jsp" %>
    <mdss:localize var="Create_Localize" key="Create" />
    <mjl:command name="dss.vector.solutions.basemap.OfflineBasemapManagement.form.create.button" action="dss.vector.solutions.basemap.OfflineBasemapManagementController.create.mojo" value="${Create_Localize}" />
  </mjl:form>
</dl>
