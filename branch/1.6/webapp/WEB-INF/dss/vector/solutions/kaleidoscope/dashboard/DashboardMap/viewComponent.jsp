<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_DashboardMap" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="activeBaseMap">
        ${item.activeBaseMap}
      </mjl:dt>
      <mjl:dt attribute="dashboard">
        ${item.dashboard.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="name">
        ${item.name}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.form.edit.button" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.DashboardMap.viewAll.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardMapController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
