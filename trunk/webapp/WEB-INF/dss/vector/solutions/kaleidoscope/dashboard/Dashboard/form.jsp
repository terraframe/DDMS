<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="dashboardRole">
    <mjl:select valueAttribute="id" param="dashboardRole" var="current" items="${dashboardRole}">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="filterDate">
    <mjl:select valueAttribute="id" param="filterDate" var="current" items="${_filterDate}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="fromDate" type="text" />
  <mjl:dt attribute="name">
    <mjl:input param="name" type="text" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="toDate" type="text" />
</mjl:component>
