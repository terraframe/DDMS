<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_MO" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.ontology.MO.form.id" name="dss.vector.solutions.ontology.MO.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.ontology.MO.form.update.button" value="Update" action="dss.vector.solutions.ontology.MOController.update.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.MO.form.delete.button" value="Delete" action="dss.vector.solutions.ontology.MOController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.MO.form.cancel.button" value="Cancel" action="dss.vector.solutions.ontology.MOController.cancel.mojo" />
  </mjl:form>
</dl>
