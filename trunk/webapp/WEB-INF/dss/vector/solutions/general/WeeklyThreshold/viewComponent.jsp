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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_WeeklyThreshold" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.WeeklyThreshold.form.id" name="dss.vector.solutions.general.WeeklyThreshold.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          Population Data
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.general.ThresholdData.form.view.link" action="dss.vector.solutions.general.ThresholdDataController.view.mojo" >
          <mjl:property name="id" value="${item.parentId}" />
          ${item.parent.keyName}
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Epi Week
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.general.EpiWeek.form.view.link" action="dss.vector.solutions.general.EpiWeekController.view.mojo">
          <mjl:property name="id" value="${item.parentId}" />
          ${item.parent.keyName}
        </mjl:commandLink>
      </dd>
      <mjl:dt attribute="identification">
        ${item.identification}
      </mjl:dt>
      <mjl:dt attribute="lastIdentification">
        ${item.lastIdentification.id}
      </mjl:dt>
      <mjl:dt attribute="lastNotification">
        ${item.lastNotification.id}
      </mjl:dt>
      <mjl:dt attribute="notification">
        ${item.notification}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.general.WeeklyThreshold.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.general.WeeklyThresholdController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.WeeklyThreshold.viewAll.link" action="dss.vector.solutions.general.WeeklyThresholdController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
