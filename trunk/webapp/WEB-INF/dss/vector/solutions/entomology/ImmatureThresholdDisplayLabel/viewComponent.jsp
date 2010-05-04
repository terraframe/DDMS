<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_ImmatureThresholdDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.id" name="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="defaultLocale">
        ${item.defaultLocale}
      </mjl:dt>
    </mjl:component>
    <mjl:command localize="false" name="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.form.edit.button" value="Edit" action="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabel.viewAll.link" action="dss.vector.solutions.entomology.ImmatureThresholdDisplayLabelController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
