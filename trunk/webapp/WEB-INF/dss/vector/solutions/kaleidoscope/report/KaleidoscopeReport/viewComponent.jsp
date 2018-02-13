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
<c:set var="page_title" scope="request" value="View_KaleidoscopeReport" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReport.form.name" id="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReport.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="cacheDocument">
        ${item.cacheDocument ? item.cacheDocumentMd.positiveDisplayLabel : item.cacheDocumentMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="dashboard">
        ${item.dashboard.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="design">
        ${item.design}
      </mjl:dt>
      <mjl:dt attribute="document">
        ${item.document}
      </mjl:dt>
      <mjl:dt attribute="reportLabel">
        ${item.reportLabel}
      </mjl:dt>
      <mjl:dt attribute="reportName">
        ${item.reportName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReport.form.edit.button" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReport.viewAll.link" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
