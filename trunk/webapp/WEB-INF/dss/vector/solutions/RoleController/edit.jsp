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
<c:set var="page_title" value="Roles_Edit" scope="request"/>

<mjl:form id="saveForm" name="dss.vector.solutions.RoleController.save.form" method="POST">
  <mjl:input type="hidden" param="id" value="${id}"/>
  
  <dl>
    <dt>
      <label>${view.usernameMd.displayLabel}</label>
    </dt>
    <dd>
      ${user.username}
    </dd>
    <dt>      
      <label>${view.rolesMd.displayLabel}</label>
    </dt>
    <c:forEach var="current" items="${roles}">
      <dd>
        <c:choose>
          <c:when test="${mjl:contains(assigned, current.id)}">
            <input type="checkbox" name="assigned" value="${current.id}" checked="checked"> ${current.displayLabel}
          </c:when>
          <c:otherwise>
          <input type="checkbox" name="assigned" value="${current.id}"> ${current.displayLabel}
          </c:otherwise>
        </c:choose>
      </dd>
    </c:forEach>        
    <dd>
      <mdss:localize key="Submit" var="Localized_Submit" />
      <mjl:command name="save" action="dss.vector.solutions.RoleController.save.mojo" value="${Localized_Submit}" />
    </dd>    
  </dl>
  
</mjl:form>

