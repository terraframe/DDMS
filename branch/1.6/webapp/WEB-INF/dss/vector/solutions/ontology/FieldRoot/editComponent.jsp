<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_FieldRoot" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.FieldRoot.form.name" id="dss.vector.solutions.ontology.FieldRoot.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.ontology.FieldRootController.update.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.ontology.FieldRootController.delete.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.ontology.FieldRootController.cancel.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.cancel.button" />
  </mjl:form>
</dl>
