<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_LocalProperty" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.LocalProperty.form.id" name="dss.vector.solutions.LocalProperty.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="description">
        ${item.description}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="editable">
        ${item.editable ? item.editableMd.positiveDisplayLabel : item.editableMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="propertyName">
        ${item.propertyName}
      </mjl:dt>
      <mjl:dt attribute="propertyPackage">
        ${item.propertyPackage}
      </mjl:dt>
      <mjl:dt attribute="propertyType">
        ${item.propertyType}
      </mjl:dt>
      <mjl:dt attribute="propertyValidator">
        ${item.propertyValidator}
      </mjl:dt>
      <mjl:dt attribute="propertyValue">
        ${item.propertyValue}
      </mjl:dt>
      <mjl:dt attribute="validValues">
        ${item.validValues}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.LocalProperty.form.edit.button" value="Edit" action="dss.vector.solutions.LocalPropertyController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.LocalProperty.viewAll.link" action="dss.vector.solutions.LocalPropertyController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
