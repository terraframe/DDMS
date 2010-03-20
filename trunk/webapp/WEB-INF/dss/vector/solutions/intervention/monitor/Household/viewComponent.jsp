<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_Household"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.intervention.monitor.Household.form.name" id="dss.vector.solutions.intervention.monitor.Household.form.id" method="POST">
  <mjl:input value="${item.concreteId}" type="hidden" param="id" />
  <dl>
    <mjl:component item="${item}" param="dto">
    <mjl:dt attribute="householdName">
      ${item.householdName}
    </mjl:dt>

    <mjl:dt attribute="urban">
      ${item.urban != null && item.urban?item.urbanMd.positiveDisplayLabel : item.urbanMd.negativeDisplayLabel}
    </mjl:dt>

    <mjl:dt attribute="people">
      ${item.people}
    </mjl:dt>

    <mjl:dt attribute="wall">
      <c:if test="${wall != null}">
        ${wall.displayLabel}
      </c:if>
    </mjl:dt>

    <mjl:dt attribute="wallInfo">
      ${item.wallInfo}
    </mjl:dt>

    <mjl:dt attribute="roof">
      <c:if test="${roof != null}">
        ${roof.displayLabel}
      </c:if>
    </mjl:dt>

    <mjl:dt attribute="roofInfo">
      ${item.roofInfo}
    </mjl:dt>

    <mjl:dt attribute="hasWindows">
      ${item.hasWindows?item.hasWindowsMd.positiveDisplayLabel : item.hasWindowsMd.negativeDisplayLabel}
    </mjl:dt>

    <mjl:dt attribute="windowType">
      <c:if test="${windowType != null}">
        ${windowType.displayLabel}
      </c:if>
    </mjl:dt>

    <mjl:dt attribute="rooms">
      ${item.rooms}
    </mjl:dt>
    
    <mjl:dt attribute="hasBeenSprayed">
      <ul>
        <c:forEach items="${item.hasBeenSprayedEnumNames}" var="enumName">
          <li>
            ${item.hasBeenSprayedMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </mjl:dt>    

    <mjl:dt attribute="lastSprayed">
      ${item.lastSprayed}
    </mjl:dt>

    <mjl:dt attribute="nets">
      ${item.nets}
    </mjl:dt>
    
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.HouseholdController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.edit.button" />
  </dl>
</mjl:form>
<dl>
  <dt></dt>
  <dd>
    <table class="displayTable" width="33%">
      <tr>
        <th><fmt:message key="People" /></th>
        <th></th>
      </tr>
      <c:forEach items="${people}" var="current" varStatus="status">
       <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
         <td>
           ${current.personId}
         </td>
         <td>
           <mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.view.mojo" name="Person.view.link">
             <fmt:message key="View"/>
             <mjl:property name="id" value="${current.concreteId}"/>
           </mjl:commandLink>
         </td>
        </tr>
      </c:forEach>
    </table>
  </dd>
  <mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.newInstance.mojo" name="Person.newInstance.link">
    <fmt:message key="Add_Person"/>
    <mjl:property name="householdId" value="${item.concreteId}"/>
  </mjl:commandLink>
</dl>

<dl>
  <dt> </dt>
  <dd>
    <table class="displayTable" width="33%">
      <tr>
        <th><fmt:message key="ITNs" /></th>
        <th></th>
      </tr>    
      <c:forEach items="${itns}" var="current" varStatus="status">
       <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
         <td>
           ${current.netId}
         </td>
         <td>
           <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNInstanceController.view.mojo" name="ITN.view.link">
             <fmt:message key="View"/>
             <mjl:property name="id" value="${current.concreteId}"/>
           </mjl:commandLink>
         </td>
        </tr>
      </c:forEach>
    </table>
  </dd>
  <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNInstanceController.newInstance.mojo" name="Person.newInstance.link">
    <fmt:message key="Add_ITN_Instance"/>
    <mjl:property name="householdId" value="${item.concreteId}"/>
  </mjl:commandLink>
</dl>

<mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyPointController.view.mojo" name="Household.view.link">
  <fmt:message key="Back_To_Survey_Point"/>
  <mjl:property name="id" value="${item.surveyPoint.id}" />
</mjl:commandLink>
