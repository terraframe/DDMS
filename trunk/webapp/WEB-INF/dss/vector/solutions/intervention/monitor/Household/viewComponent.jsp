<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Household.form.name" id="dss.vector.solutions.intervention.monitor.Household.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.householdNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.householdName}
    </dd>
    <dt>
      <label>
        ${item.lastSprayedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lastSprayed}
    </dd>
    <dt>
      <label>
        ${item.netsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.nets}
    </dd>
    <dt>
      <label>
        ${item.netsUsedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.netsUsed}
    </dd>
    <dt>
      <label>
        ${item.peopleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.people}
    </dd>
    <dt>
      <label>
        ${item.roofMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.roof.geoId}" action="dss.vector.solutions.geo.generated.RoofController.view.mojo" name="dss.vector.solutions.geo.generated.Roof.form.view.link">
        <mjl:property value="${item.roof.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.roofInfoMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.roofInfo}
    </dd>
    <dt>
      <label>
        ${item.roomsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.rooms}
    </dd>
    <dt>
      <label>
        ${item.sleptUnderNetsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sleptUnderNets}
    </dd>
    <dt>
      <label>
        ${item.surveyPointMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.surveyPoint.displayLabel}" action="dss.vector.solutions.intervention.monitor.SurveyPointController.view.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.view.link">
        <mjl:property value="${item.surveyPoint.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.urbanMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.urban}
    </dd>
    <dt>
      <label>
        ${item.wallMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.wall.keyName}" action="dss.vector.solutions.geo.generated.WallController.view.mojo" name="dss.vector.solutions.geo.generated.Wall.form.view.link">
        <mjl:property value="${item.wall.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.wallInfoMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.wallInfo}
    </dd>
    <dt>
      <label>
        ${item.hasWindowsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.hasWindows}
    </dd>
    <dt>
      <label>
        ${item.windowTypeMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.windowTypeEnumNames}">
          <li>
            ${item.windowTypeMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label><fmt:message key="Nets"/>  </label>
    </dt>
    <dd>
      <table class="displayTable">
        <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <c:choose>
              <c:when test="${current.child.isAbstract}">
                <td colspan="2">
                  ${current.child.displayLabel}
                </td>
              </c:when>
              <c:otherwise>
                <td>
                  ${current.child.displayLabel}
                </td>
                <td>
                  ${current.amount}
                </td>
              </c:otherwise>
            </c:choose>
          </tr>
        </mjl:components>
      </table>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.HouseholdController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.edit.button" />
</mjl:form>
<dl>
  <dt><fmt:message key="People" /> </dt>
  <dd>
    <table class="displayTable">
      <c:forEach items="${people}" var="current" varStatus="status">
       <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
         <td>
           ${current.personId}
         </td>
         <td>
           <mjl:commandLink display="View" action="dss.vector.solutions.intervention.monitor.PersonController.view.mojo" name="Person.view.link">
             <mjl:property name="id" value="${current.id}"/>
           </mjl:commandLink>
         </td>
        </tr>
      </c:forEach>
    </table>
  </dd>
  <mjl:commandLink display="Add a person" action="dss.vector.solutions.intervention.monitor.PersonController.newInstance.mojo" name="Person.newInstance.link">
    <mjl:property name="householdId" value="${item.id}"/>
  </mjl:commandLink>
</dl>
