<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.OntologyRelationship.form.name" id="dss.vector.solutions.ontology.OntologyRelationship.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.ontology.OntologyRelationshipController.update.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.ontology.OntologyRelationshipController.delete.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.ontology.OntologyRelationshipController.cancel.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.cancel.button" />
  </mjl:form>
</dl>
