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
<c:set scope="request" var="page_title" value="View_AreaStandards" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.irs.AreaStandards.form.id" name="dss.vector.solutions.irs.AreaStandards.form.name" method="POST">
    <mjl:input param="id" value="${item.areaStandardsId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="startDate">
        <span class="formatDate">${item.startDate}</span>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <span class="formatDate">${item.endDate}</span>
      </mjl:dt>
      <mjl:dt attribute="targetUnit">
        <ul>
          <c:forEach items="${item.targetUnitEnumNames}" var="enumName">
            <li>
              ${item.targetUnitMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>      
      <mjl:dt attribute="unitNozzleAreaCoverage">
        <fmt:formatNumber minFractionDigits="2" value="${item.unitNozzleAreaCoverage}" />
      </mjl:dt>      
      <mjl:dt attribute="room">
        <fmt:formatNumber minFractionDigits="2" value="${item.room}" />
      </mjl:dt>
      <mjl:dt attribute="structureArea">
        <fmt:formatNumber minFractionDigits="2" value="${item.structureArea}" />        
      </mjl:dt>
      <mjl:dt attribute="household">
        <fmt:formatNumber minFractionDigits="2" value="${item.household}" />        
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.irs.AreaStandards.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.irs.AreaStandardsController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.irs.AreaStandards.viewAll.link" action="dss.vector.solutions.irs.AreaStandardsController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
