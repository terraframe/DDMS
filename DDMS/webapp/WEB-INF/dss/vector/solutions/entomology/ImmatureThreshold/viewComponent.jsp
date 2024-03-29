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
<c:set scope="request" var="page_title" value="Configure_immature_thresholds" />
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ImmatureThreshold.form.id" name="dss.vector.solutions.entomology.ImmatureThreshold.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel.value}
      </mjl:dt>
      <mjl:dt attribute="thresholdValue">
        <fmt:formatNumber minFractionDigits="2">${item.thresholdValue}</fmt:formatNumber>
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.entomology.ImmatureThreshold.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.entomology.ImmatureThresholdController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.entomology.ImmatureThreshold.viewAll.link" action="dss.vector.solutions.entomology.ImmatureThresholdController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
