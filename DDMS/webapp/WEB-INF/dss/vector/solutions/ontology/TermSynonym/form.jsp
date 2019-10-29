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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<jsp:include page="/WEB-INF/selectSearchTerm.jsp" />

<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="entityName">
    <mjl:input param="entityName" />
    <mjl:string param="entityName" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="collectionDate" type="text" />
  <mjl:dt attribute="collectionId">
    <mjl:input param="collectionId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="collectionMethod">
    <mdss:mo param="collectionMethod" value="${collectionMethod}" />
  </mjl:dt>
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" value="${item.geoEntity}" />
  </mjl:dt>
  <mjl:dt attribute="lifeStage">
    <mjl:select param="lifeStage" items="${lifeStage}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.lifeStageEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.lifeStageMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="lifeStageName">
    <mjl:input param="lifeStageName" type="text" />
  </mjl:dt>
</mjl:component>
