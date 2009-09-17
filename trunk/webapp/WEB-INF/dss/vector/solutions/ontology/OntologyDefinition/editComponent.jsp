<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_OntologyDefinition" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.ontology.OntologyDefinition.form.id" name="dss.vector.solutions.ontology.OntologyDefinition.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.ontology.OntologyDefinition.form.update.button" value="Update" action="dss.vector.solutions.ontology.OntologyDefinitionController.update.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.OntologyDefinition.form.delete.button" value="Delete" action="dss.vector.solutions.ontology.OntologyDefinitionController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.OntologyDefinition.form.cancel.button" value="Cancel" action="dss.vector.solutions.ontology.OntologyDefinitionController.cancel.mojo" />
  </mjl:form>
</dl>
