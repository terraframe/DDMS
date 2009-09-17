<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_BrowserField" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.BrowserField.form.name" id="dss.vector.solutions.ontology.BrowserField.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="mdAttribute">
        ${item.mdAttribute.id}
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.ontology.BrowserFieldController.edit.mojo" name="dss.vector.solutions.ontology.BrowserField.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.BrowserFieldController.viewAll.mojo" name="dss.vector.solutions.ontology.BrowserField.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>
