<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_All_Dashboard" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table even="evenRow" var="item" query="${query}" classes="displayTable" odd="oddRow">
  <mjl:context action="dss.vector.solutions.kaleidoscope.dashboard.DashboardController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="dashboardRole">
      <mjl:row>
        ${item.dashboardRole.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="description">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="filterDate">
      <mjl:row>
        ${item.filterDate.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="fromDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.fromDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="name">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="toDate">
      <mjl:row>
        <fmt:formatDate pattern="${dateFormatPattern}" value="${item.toDate}" />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink name="DashboardController.newInstance" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Dashboard" />
</mjl:commandLink>
