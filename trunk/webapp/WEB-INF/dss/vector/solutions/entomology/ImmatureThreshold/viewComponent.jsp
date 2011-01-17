<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Configure_immature_thresholds" />
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ImmatureThreshold.form.id" name="dss.vector.solutions.entomology.ImmatureThreshold.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel.value}
      </mjl:dt>
      <mjl:dt attribute="thresholdValue">
        ${item.thresholdValue}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.entomology.ImmatureThreshold.form.edit.button" value="Edit" action="dss.vector.solutions.entomology.ImmatureThresholdController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.entomology.ImmatureThreshold.viewAll.link" action="dss.vector.solutions.entomology.ImmatureThresholdController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
