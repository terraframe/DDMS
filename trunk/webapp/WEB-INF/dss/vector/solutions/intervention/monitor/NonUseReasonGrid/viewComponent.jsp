<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_NonUseReasonGrid" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.NonUseReasonGrid.form.id" name="dss.vector.solutions.intervention.monitor.NonUseReasonGrid.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="active">
        ${item.active ? item.activeMd.positiveDisplayLabel : item.activeMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        <mjl:struct param="displayLabel">
          <mjl:dt attribute="defaultLocale">
            ${item.displayLabel.defaultLocale}
          </mjl:dt>
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="optionName">
        ${item.optionName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.intervention.monitor.NonUseReasonGrid.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.NonUseReasonGridController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.intervention.monitor.NonUseReasonGrid.viewAll.link" action="dss.vector.solutions.intervention.monitor.NonUseReasonGridController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
