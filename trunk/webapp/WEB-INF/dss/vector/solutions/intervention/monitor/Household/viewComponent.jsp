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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
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
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.intervention.monitor.HouseholdController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.edit.button" />
  </dl>
</mjl:form>

<dl>
  <dt> </dt>
  <dd>
    <table class="displayTable" width="33%">
      <tr>
        <th><mdss:localize key="ITNs" /></th>
        <th></th>
      </tr>    
      <c:forEach items="${itns}" var="current" varStatus="status">
       <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
         <td>
           ${current.netId}
         </td>
         <td>
           <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNInstanceController.view.mojo" name="ITN.view.link">
             <mdss:localize key="View"/>
             <mjl:property name="id" value="${current.concreteId}"/>
           </mjl:commandLink>
         </td>
        </tr>
      </c:forEach>
    </table>
  </dd>
  <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ITNInstanceController.newInstance.mojo" name="Person.newInstance.link">
    <mdss:localize key="Add_ITN_Instance"/>
    <mjl:property name="householdId" value="${item.concreteId}"/>
  </mjl:commandLink>
</dl>

<dl>
  <dt></dt>
  <dd>
    <table class="displayTable" width="33%">
      <tr>
        <th><mdss:localize key="People" /></th>
        <th></th>
      </tr>
      <c:forEach items="${people}" var="current" varStatus="status">
       <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
         <td>
           ${current.personId}
         </td>
         <td>
           <mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.view.mojo" name="Person.view.link">
             <mdss:localize key="View"/>
             <mjl:property name="id" value="${current.concreteId}"/>
           </mjl:commandLink>
         </td>
        </tr>
      </c:forEach>
    </table>
  </dd>
  <mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.newInstance.mojo" name="Person.newInstance.link">
    <mdss:localize key="Add_Person"/>
    <mjl:property name="householdId" value="${item.concreteId}"/>
  </mjl:commandLink>
</dl>


<mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyPointController.view.mojo" name="Household.view.link">
  <mdss:localize key="Back_To_Survey_Point"/>
  <mjl:property name="id" value="${item.surveyPoint.id}" />
</mjl:commandLink>
