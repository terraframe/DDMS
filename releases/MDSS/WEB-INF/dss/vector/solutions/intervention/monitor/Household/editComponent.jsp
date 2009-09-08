<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<c:set var="page_title" value="Edit_Household"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.intervention.monitor.Household.form.name" id="Household.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.HouseholdController.update.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.HouseholdController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.HouseholdController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.cancel.button" />
  </dl>
</mjl:form>