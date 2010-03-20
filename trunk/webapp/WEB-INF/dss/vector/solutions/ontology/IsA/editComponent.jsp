<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_IsA" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.IsA.form.name" id="dss.vector.solutions.ontology.IsA.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.ontology.IsAController.update.mojo" name="dss.vector.solutions.ontology.IsA.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.ontology.IsAController.delete.mojo" name="dss.vector.solutions.ontology.IsA.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.IsAController.cancel.mojo" name="dss.vector.solutions.ontology.IsA.form.cancel.button" />
  </mjl:form>
</dl>
