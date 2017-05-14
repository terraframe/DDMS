<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_KaleidoscopeReportReportLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabel.form.name" id="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabel.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="dENGUE_defaultLocale">
        ${item.dENGUE_defaultLocale}
      </mjl:dt>
      <mjl:dt attribute="defaultLocale">
        ${item.defaultLocale}
      </mjl:dt>
      <mjl:dt attribute="mALARIA_defaultLocale">
        ${item.mALARIA_defaultLocale}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabel.form.edit.button" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabel.viewAll.link" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
