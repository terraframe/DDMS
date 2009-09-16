<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_FreeITNProviderDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.id" name="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="defaultLocale">
        ${item.defaultLocale}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabelController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.viewAll.link" action="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabelController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
