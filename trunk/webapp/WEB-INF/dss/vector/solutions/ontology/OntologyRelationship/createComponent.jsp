<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.OntologyRelationship.form.name" id="dss.vector.solutions.ontology.OntologyRelationship.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Create" action="dss.vector.solutions.ontology.OntologyRelationshipController.create.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.create.button" />
  </mjl:form>
</dl>
