<%@page import="java.util.Set"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="dss.vector.solutions.intervention.monitor.IndividualCaseDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${person != null && hasPermission}">
  <mjl:component item="${person}" param="#person">
    <mjl:dt attribute="identifier">
      ${person.identifier}
    </mjl:dt>
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