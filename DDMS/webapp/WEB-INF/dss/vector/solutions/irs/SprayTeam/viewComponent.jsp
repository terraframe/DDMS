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
<c:set var="page_title" value="Spray_Team_View" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:component item="${view}" param="dto">
      <mjl:dt attribute="teamId">
        ${item.teamId}
      </mjl:dt>
      <mjl:dt attribute="sprayZone">
        ${item.sprayZone.displayString}
      </mjl:dt>
      <mjl:dt attribute="teamLeader">
        <c:if test="${leader != null}">
          ${leader.firstName} ${leader.lastName}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="currentOperators">
        <c:forEach items="${operators}" var="operator">
          ${operator.firstName} ${operator.lastName} <br/>
        </c:forEach>      
      </mjl:dt>      
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.irs.SprayTeamController.edit.mojo" name="dss.vector.solutions.irs.SprayTeam.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.irs.SprayTeamController.viewAll.mojo" name="dss.vector.solutions.irs.SprayTeam.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
