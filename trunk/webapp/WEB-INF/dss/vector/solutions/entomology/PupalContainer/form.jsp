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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="containerId">
    <mjl:input param="containerId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="containerLength">
    <mjl:input param="containerLength" type="text" />
  </mjl:dt>
  <mjl:dt attribute="containerType">
    <mdss:mo param="containerType" value="${containerType}" />
  </mjl:dt>
  <mjl:dt attribute="diameter">
    <mjl:input param="diameter" type="text" />
  </mjl:dt>
  <mjl:dt attribute="drawdownFrequency">
    <mdss:mo param="drawdownFrequency" value="${drawdownFrequency}" />
  </mjl:dt>
  <mjl:dt attribute="drawdownPercent">
    <mjl:input param="drawdownPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fillFrequency">
    <mdss:mo param="fillFrequency" value="${fillFrequency}" />
  </mjl:dt>
  <mjl:dt attribute="fillMethod">
    <mdss:mo param="fillMethod" value="${fillMethod}" />
  </mjl:dt>
  <mjl:dt attribute="height">
    <mjl:input param="height" type="text" />
  </mjl:dt>
  <mjl:dt attribute="lid">
    <mdss:mo param="lid" value="${lid}" />
  </mjl:dt>
  <mjl:dt attribute="openingDiameter">
    <mjl:input param="openingDiameter" type="text" />
  </mjl:dt>
  <mjl:dt attribute="openingLength">
    <mjl:input param="openingLength" type="text" />
  </mjl:dt>
  <mjl:dt attribute="openingWidth">
    <mjl:input param="openingWidth" type="text" />
  </mjl:dt>
  <mjl:dt attribute="premise">
    <mjl:select param="premise" items="${_premise}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="roof">
    <mdss:mo param="roof" value="${roof}" />
  </mjl:dt>
  <mjl:dt attribute="shading">
    <mdss:mo param="shading" value="${shading}" />
  </mjl:dt>
  <mjl:dt attribute="shape">
    <mjl:select param="shape" items="${_shape}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.shapeEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.shapeMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="width">
    <mjl:input param="width" type="text" />
  </mjl:dt>
</mjl:component>
