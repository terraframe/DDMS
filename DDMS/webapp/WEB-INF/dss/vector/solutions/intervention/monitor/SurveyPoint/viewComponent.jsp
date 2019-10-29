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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_Survey_Point"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.name" id="dss.vector.solutions.intervention.monitor.SurveyPoint.form.id" method="POST">
  <mjl:input value="${item.concreteId}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.geoIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${entity.displayString}
    </dd>
    <dt>
      <label> ${item.surveyDateMd.displayLabel} </label>
    </dt>
    <dd class="formatDate">${item.surveyDate}</dd>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.intervention.monitor.SurveyPointController.edit.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.form.edit.button" />
  </dl>
  <br />
</mjl:form>

<dl>
  <dd>
    <table class="displayTable" width="33%">
      <tr>
        <th><mdss:localize key="Households" /></th>
        <th></th>
      </tr>    
  
      <c:forEach items="${households}" var="current" varStatus="status">
        <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
          <td>${current.householdName}</td>
          <td><mjl:commandLink action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="Household.view.link">
            <mdss:localize key="View" />
            <mjl:property name="id" value="${current.concreteId}" />
          </mjl:commandLink></td>
        </tr>
      </c:forEach>
    </table>
  </dd>
  <mjl:commandLink action="dss.vector.solutions.intervention.monitor.HouseholdController.newInstance.mojo" name="Household.newInstance.link">
    <mdss:localize key="Add_a_household" />
    <mjl:property name="surveyId" value="${item.concreteId}" />
  </mjl:commandLink>
</dl>

<mjl:commandLink action="dss.vector.solutions.intervention.monitor.SurveyPointController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.SurveyPoint.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>