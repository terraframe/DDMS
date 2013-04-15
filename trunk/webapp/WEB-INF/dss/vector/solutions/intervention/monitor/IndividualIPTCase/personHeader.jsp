<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${person != null && hasPermission}">
  <mjl:component item="${person}" param="#person">
    <mjl:dt attribute="firstName">
      ${person.firstName}
    </mjl:dt>
    <mjl:dt attribute="lastName">
      ${person.lastName}
    </mjl:dt>
    <mjl:dt attribute="sex">
      ${person.sex.displayLabel}
    </mjl:dt>
    <mjl:dt attribute="dateOfBirth">
      <span class="formatDate">${person.dateOfBirth}</span>
    </mjl:dt>
  </mjl:component>
</c:if>
