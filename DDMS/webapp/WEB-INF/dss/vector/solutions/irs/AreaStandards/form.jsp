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
<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="areaStandardsId" value="${item.areaStandardsId}"/>
  <mjl:dt attribute="startDate">
    <mjl:input param="startDate" type="text" classes="DatePick" id="startDate"/>
  </mjl:dt>
  <mjl:dt attribute="targetUnit">
    <mjl:select param="targetUnit" items="${targetUnit}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.targetUnitEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.targetUnitMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="unitNozzleAreaCoverage" >
    <fmt:formatNumber minFractionDigits="2" var="formatUnitNozzleAreaCoverage" value="${item.unitNozzleAreaCoverage}" />
    <mjl:input type="text" param="unitNozzleAreaCoverage" value="${formatUnitNozzleAreaCoverage}" />              
  </mjl:dt>
  <mjl:dt attribute="room" >
    <fmt:formatNumber minFractionDigits="2" var="formatRoom" value="${item.room}" />
    <mjl:input type="text" param="room" value="${formatRoom}" />        
  </mjl:dt>
  <mjl:dt attribute="structureArea">
    <fmt:formatNumber minFractionDigits="2" var="formatStructureArea" value="${item.structureArea}" />
    <mjl:input type="text" param="structureArea" value="${formatStructureArea}" />              
  </mjl:dt>
  <mjl:dt attribute="household">
    <fmt:formatNumber minFractionDigits="2" var="formatHousehold" value="${item.household}" />
    <mjl:input type="text" param="household" value="${formatHousehold}" />        
  </mjl:dt>
</mjl:component>
