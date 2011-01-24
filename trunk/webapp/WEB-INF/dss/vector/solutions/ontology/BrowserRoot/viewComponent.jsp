<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_BrowserRoot" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.BrowserRoot.form.name" id="dss.vector.solutions.ontology.BrowserRoot.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="selectable">
        ${item.selectable ? item.selectableMd.positiveDisplayLabel : item.selectableMd.negativeDisplayLabel}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.ontology.BrowserRootController.edit.mojo" name="dss.vector.solutions.ontology.BrowserRoot.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.BrowserRootController.viewAll.mojo" name="dss.vector.solutions.ontology.BrowserRoot.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
