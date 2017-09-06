<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_Dashboard" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.Dashboard.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.Dashboard.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="dashboardRole">
        ${item.dashboardRole.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="description">
        ${item.description}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="filterDate">
        ${item.filterDate.keyName}
      </mjl:dt>
      <mjl:dt attribute="fromDate">
        <span class="formatDate">
          ${item.fromDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="name">
        ${item.name}
      </mjl:dt>
      <mjl:dt attribute="toDate">
        <span class="formatDate">
          ${item.toDate}
        </span>
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.Dashboard.form.edit.button" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.Dashboard.viewAll.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
