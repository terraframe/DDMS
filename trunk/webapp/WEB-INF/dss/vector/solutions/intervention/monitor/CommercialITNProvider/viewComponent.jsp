<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_CommercialITNProvider" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.id" name="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.name" method="POST">
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
      <mjl:dt attribute="providerName">
        ${item.providerName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.intervention.monitor.CommercialITNProvider.viewAll.link" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
