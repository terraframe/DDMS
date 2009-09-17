<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_FieldRoot" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.FieldRoot.form.name" id="dss.vector.solutions.ontology.FieldRoot.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.ontology.FieldRootController.update.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.ontology.FieldRootController.delete.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.FieldRootController.cancel.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.cancel.button" />
  </mjl:form>
</dl>
