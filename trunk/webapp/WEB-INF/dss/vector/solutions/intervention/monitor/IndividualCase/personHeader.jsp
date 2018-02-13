<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
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