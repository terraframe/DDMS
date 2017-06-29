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
