<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_IsA" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.ontology.IsA.form.id" name="dss.vector.solutions.ontology.IsA.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.ontology.IsA.form.update.button" value="Update" action="dss.vector.solutions.ontology.IsAController.update.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.IsA.form.delete.button" value="Delete" action="dss.vector.solutions.ontology.IsAController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.IsA.form.cancel.button" value="Cancel" action="dss.vector.solutions.ontology.IsAController.cancel.mojo" />
  </mjl:form>
</dl>
