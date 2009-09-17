<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_MOField" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.MOField.form.name" id="dss.vector.solutions.ontology.MOField.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.ontology.MOFieldController.update.mojo" name="dss.vector.solutions.ontology.MOField.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.ontology.MOFieldController.delete.mojo" name="dss.vector.solutions.ontology.MOField.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.MOFieldController.cancel.mojo" name="dss.vector.solutions.ontology.MOField.form.cancel.button" />
  </mjl:form>
</dl>
