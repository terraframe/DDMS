<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.OntologyRelationship.form.name" id="dss.vector.solutions.ontology.OntologyRelationship.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.ontology.OntologyRelationshipController.update.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.ontology.OntologyRelationshipController.delete.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.OntologyRelationshipController.cancel.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.cancel.button" />
  </mjl:form>
</dl>
