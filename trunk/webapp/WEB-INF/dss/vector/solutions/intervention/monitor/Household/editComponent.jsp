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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<c:set var="page_title" value="Edit_Household"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.intervention.monitor.Household.form.name" id="Household.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mdss:localize key="Update" var="Localized_Update" />

    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.intervention.monitor.HouseholdController.update.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.intervention.monitor.HouseholdController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.intervention.monitor.HouseholdController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.cancel.button" />
  </dl>
</mjl:form>