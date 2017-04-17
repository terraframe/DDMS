<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.Ontology.form.name" id="dss.vector.solutions.ontology.Ontology.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.ontology.OntologyController.create.mojo" name="dss.vector.solutions.ontology.Ontology.form.create.button" />
  </mjl:form>
</dl>
