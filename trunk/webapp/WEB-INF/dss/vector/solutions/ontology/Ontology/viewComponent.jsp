<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.Ontology.form.name" id="dss.vector.solutions.ontology.Ontology.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="defaultNamespace">
        ${item.defaultNamespace}
      </mjl:dt>
      <mjl:dt attribute="keyName">
        ${item.keyName}
      </mjl:dt>
      <mjl:dt attribute="title">
        ${item.title}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.ontology.OntologyController.edit.mojo" name="dss.vector.solutions.ontology.Ontology.form.edit.button" />
  </mjl:form>
</dl>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.ontology.OntologyHasRelationshipController.parentQuery.mojo" name="dss.vector.solutions.ontology.OntologyHasRelationship.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.OntologyController.viewAll.mojo" name="dss.vector.solutions.ontology.Ontology.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
