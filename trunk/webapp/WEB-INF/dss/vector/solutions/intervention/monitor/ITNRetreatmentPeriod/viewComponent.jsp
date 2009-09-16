<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_ITNRetreatmentPeriod" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.id" name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="displayLabel">
        <mjl:struct param="displayLabel">
          <mjl:dt attribute="defaultLocale">
            ${item.displayLabel.defaultLocale}
          </mjl:dt>
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="enabled">
        ${item.enabled ? item.enabledMd.positiveDisplayLabel : item.enabledMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="periodName">
        ${item.periodName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.viewAll.link" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
