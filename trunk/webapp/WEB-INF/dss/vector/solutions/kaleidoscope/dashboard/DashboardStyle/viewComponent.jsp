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
<c:set var="page_title" scope="request" value="View_DashboardStyle" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="basicPointSize">
        ${item.basicPointSize}
      </mjl:dt>
      <mjl:dt attribute="enableLabel">
        ${item.enableLabel ? item.enableLabelMd.positiveDisplayLabel : item.enableLabelMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="enableValue">
        ${item.enableValue ? item.enableValueMd.positiveDisplayLabel : item.enableValueMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="labelColor">
        ${item.labelColor}
      </mjl:dt>
      <mjl:dt attribute="labelFont">
        ${item.labelFont}
      </mjl:dt>
      <mjl:dt attribute="labelHalo">
        ${item.labelHalo}
      </mjl:dt>
      <mjl:dt attribute="labelHaloWidth">
        ${item.labelHaloWidth}
      </mjl:dt>
      <mjl:dt attribute="labelSize">
        ${item.labelSize}
      </mjl:dt>
      <mjl:dt attribute="lineOpacity">
        ${item.lineOpacity}
      </mjl:dt>
      <mjl:dt attribute="lineStroke">
        ${item.lineStroke}
      </mjl:dt>
      <mjl:dt attribute="lineStrokeCap">
        ${item.lineStrokeCap}
      </mjl:dt>
      <mjl:dt attribute="lineStrokeWidth">
        ${item.lineStrokeWidth}
      </mjl:dt>
      <mjl:dt attribute="name">
        ${item.name}
      </mjl:dt>
      <mjl:dt attribute="pointFill">
        ${item.pointFill}
      </mjl:dt>
      <mjl:dt attribute="pointOpacity">
        ${item.pointOpacity}
      </mjl:dt>
      <mjl:dt attribute="pointRotation">
        ${item.pointRotation}
      </mjl:dt>
      <mjl:dt attribute="pointStroke">
        ${item.pointStroke}
      </mjl:dt>
      <mjl:dt attribute="pointStrokeOpacity">
        ${item.pointStrokeOpacity}
      </mjl:dt>
      <mjl:dt attribute="pointStrokeWidth">
        ${item.pointStrokeWidth}
      </mjl:dt>
      <mjl:dt attribute="pointWellKnownName">
        ${item.pointWellKnownName}
      </mjl:dt>
      <mjl:dt attribute="polygonFill">
        ${item.polygonFill}
      </mjl:dt>
      <mjl:dt attribute="polygonFillOpacity">
        ${item.polygonFillOpacity}
      </mjl:dt>
      <mjl:dt attribute="polygonStroke">
        ${item.polygonStroke}
      </mjl:dt>
      <mjl:dt attribute="polygonStrokeOpacity">
        ${item.polygonStrokeOpacity}
      </mjl:dt>
      <mjl:dt attribute="polygonStrokeWidth">
        ${item.polygonStrokeWidth}
      </mjl:dt>
      <mjl:dt attribute="valueColor">
        ${item.valueColor}
      </mjl:dt>
      <mjl:dt attribute="valueFont">
        ${item.valueFont}
      </mjl:dt>
      <mjl:dt attribute="valueHalo">
        ${item.valueHalo}
      </mjl:dt>
      <mjl:dt attribute="valueHaloWidth">
        ${item.valueHaloWidth}
      </mjl:dt>
      <mjl:dt attribute="valueSize">
        ${item.valueSize}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle.form.edit.button" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle.viewAll.link" action="dss.vector.solutions.kaleidoscope.dashboard.DashboardStyleController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
