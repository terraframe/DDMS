<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_TermTermDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.ontology.TermTermDisplayLabel.form.id" name="dss.vector.solutions.ontology.TermTermDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command localize="false" name="dss.vector.solutions.ontology.TermTermDisplayLabel.form.update.button" value="Update" action="dss.vector.solutions.ontology.TermTermDisplayLabelController.update.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.ontology.TermTermDisplayLabel.form.delete.button" value="Delete" action="dss.vector.solutions.ontology.TermTermDisplayLabelController.delete.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.ontology.TermTermDisplayLabel.form.cancel.button" value="Cancel" action="dss.vector.solutions.ontology.TermTermDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
