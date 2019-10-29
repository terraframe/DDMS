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
  <mjl:dt attribute="productName">
    <mdss:mo param="productName" value="${productName}" />
  </mjl:dt>
  <mjl:dt attribute="activeIngredient">
    <mdss:mo param="activeIngredient" value="${activeIngredient}" />
  </mjl:dt>
  <mjl:dt attribute="concentrationQuantifier">
    <fmt:formatNumber minFractionDigits="2" var="formatConcentrationQuantifier" value="${item.concentrationQuantifier}" />  
    <mjl:input param="concentrationQuantifier" type="text" value="${formatConcentrationQuantifier}"/>
  </mjl:dt>
  <mjl:dt attribute="concentrationQualifier">
    <mjl:select param="concentrationQualifier" items="${_concentrationQualifier}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.concentrationQualifierEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.concentrationQualifierMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="insecticideUse">
    <mjl:select param="insecticideUse" items="${_insecticideUse}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.insecticideUseEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.insecticideUseMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="useDetail">
    <mdss:mo param="useDetail" value="${useDetail}" />
  </mjl:dt>
  <mjl:dt attribute="unitsPerApplication">
    <mjl:input param="unitsPerApplication" type="text" />
  </mjl:dt>
  <mjl:dt attribute="unitQuantifier">
    <fmt:formatNumber minFractionDigits="2" var="formatUnitQuantifier" value="${item.unitQuantifier}" />    
    <mjl:input param="unitQuantifier" type="text"  value="${formatUnitQuantifier}"/>
  </mjl:dt>
  <mjl:dt attribute="unitQualifier">
    <mjl:select param="unitQualifier" items="${_unitQualifier}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.unitQualifierEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.unitQualifierMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="enabled">
    <mjl:boolean param="enabled" />
  </mjl:dt>
</mjl:component>
