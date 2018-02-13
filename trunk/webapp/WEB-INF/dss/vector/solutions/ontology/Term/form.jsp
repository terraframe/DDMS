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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mdss:localize key="Choice_Yes" var="TrueLabel" />
<mdss:localize key="Choice_No" var="FalseLabel" />

<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="name">
    <mjl:input type="text" param="name" />
  </mjl:dt>
  <mjl:dt attribute="termDisplayLabel">
    <mjl:input type="text" param="termDisplayLabel" />
  </mjl:dt>
  <mjl:dt attribute="termId">
    <mjl:input type="text" param="termId" />
  </mjl:dt>
  <mjl:dt attribute="namespace">
    <mjl:input type="text" param="namespace" />
  </mjl:dt>
  <mjl:dt attribute="def">
    <mjl:input type="text" param="def" />
  </mjl:dt>
  <mjl:dt attribute="comment">
    <mjl:input type="text" param="comment" />
  </mjl:dt>
</mjl:component>
<c:choose>
  <c:when test="${isRoot}">
    <mjl:input param="inactive" value="${inactive}" type="hidden" />
  </c:when>
  <c:otherwise>
  <dt>
   <label for="inactive">${inactiveLabel}</label>
  </dt>
  <dd>
    <mjl:boolean param="inactive" falseLabel="${FalseLabel}" trueLabel="${TrueLabel}" value="${inactive}"  />
  </dd>
  </c:otherwise>
</c:choose>
