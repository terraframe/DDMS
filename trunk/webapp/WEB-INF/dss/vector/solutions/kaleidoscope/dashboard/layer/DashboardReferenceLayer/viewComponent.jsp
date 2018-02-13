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
<c:set var="page_title" scope="request" value="View_DashboardReferenceLayer" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayer.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayer.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="universal">
        ${item.universal.keyName}
      </mjl:dt>
      <mjl:dt attribute="BBoxIncluded">
        ${item.BBoxIncluded ? item.BBoxIncludedMd.positiveDisplayLabel : item.BBoxIncludedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="activeByDefault">
        ${item.activeByDefault ? item.activeByDefaultMd.positiveDisplayLabel : item.activeByDefaultMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="dashboardLegend">
        <mjl:struct param="dashboardLegend">
          <mjl:dt attribute="groupedInLegend">
            ${item.dashboardLegend.groupedInLegend ? item.dashboardLegend.groupedInLegendMd.positiveDisplayLabel : item.dashboardLegend.groupedInLegendMd.negativeDisplayLabel}
          </mjl:dt>
          <mjl:dt attribute="legendXPosition">
            ${item.dashboardLegend.legendXPosition}
          </mjl:dt>
          <mjl:dt attribute="legendYPosition">
            ${item.dashboardLegend.legendYPosition}
          </mjl:dt>
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="dashboardMap">
        ${item.dashboardMap.keyName}
      </mjl:dt>
      <mjl:dt attribute="displayInLegend">
        ${item.displayInLegend ? item.displayInLegendMd.positiveDisplayLabel : item.displayInLegendMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="lastPublishDate">
        <span class="formatDate">
          ${item.lastPublishDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="layerEnabled">
        ${item.layerEnabled ? item.layerEnabledMd.positiveDisplayLabel : item.layerEnabledMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="layerType">
        <ul>
          <c:forEach var="enumName" items="${item.layerTypeEnumNames}">
            <li>
              ${item.layerTypeMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="nameLabel">
        ${item.nameLabel}
      </mjl:dt>
      <mjl:dt attribute="viewName">
        ${item.viewName}
      </mjl:dt>
      <mjl:dt attribute="virtual">
        ${item.virtual ? item.virtualMd.positiveDisplayLabel : item.virtualMd.negativeDisplayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayer.form.edit.button" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayer.viewAll.link" action="dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardReferenceLayerController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
