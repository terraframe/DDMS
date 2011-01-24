<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_OntologyDefinition" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.OntologyDefinition.form.name" id="dss.vector.solutions.ontology.OntologyDefinition.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="namespace">
        ${item.namespace}
      </mjl:dt>
      <mjl:dt attribute="ontologyName">
        ${item.ontologyName}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.ontology.OntologyDefinitionController.edit.mojo" name="dss.vector.solutions.ontology.OntologyDefinition.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.OntologyDefinitionController.viewAll.mojo" name="dss.vector.solutions.ontology.OntologyDefinition.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
