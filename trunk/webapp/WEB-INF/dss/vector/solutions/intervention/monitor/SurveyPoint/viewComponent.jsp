<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.name" id="dss.vector.solutions.intervention.monitor.SurveyPoint.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
        <dt>
      <label>
        ${item.geoEntityMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoEntity.geoId} (${item.geoEntity.entityName})" action="dss.vector.solutions.geo.generated.GeoEntityController.view.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt><label> ${item.surveyDateMd.displayLabel} </label></dt>
    <dd class="formatDate">${item.surveyDate}</dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.SurveyPointController.edit.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.edit.button" />
  <br />
</mjl:form>



<dl>
  <dt><fmt:message key="Households" /></dt>
  <dd>
  <table class="displayTable">
  <%--
  <tr>
      <th>HouseHold Name</th>
      <th>HouseHold Type</th>
      <th>Rooms</th>
    </tr>
  --%>
    <c:forEach items="${households}" var="current" varStatus="status">
      <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
        <td>${current.householdName}</td>
        <td><mjl:commandLink display="View" action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="Household.view.link">
          <mjl:property name="id" value="${current.id}" />
        </mjl:commandLink></td>
      </tr>
    </c:forEach>
  </table>
  </dd>
  <mjl:commandLink display="Add a household" action="dss.vector.solutions.intervention.monitor.HouseholdController.newInstance.mojo" name="Household.newInstance.link">
    <mjl:property name="surveyId" value="${item.id}" />
  </mjl:commandLink>
</dl>

<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.SurveyPointController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.viewAll.link" />
