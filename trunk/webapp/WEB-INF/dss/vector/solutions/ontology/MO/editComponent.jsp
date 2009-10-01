<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_MO" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.MO.form.name" id="dss.vector.solutions.ontology.MO.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.ontology.MOController.update.mojo" name="dss.vector.solutions.ontology.MO.form.update.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.MOController.cancel.mojo" name="dss.vector.solutions.ontology.MO.form.cancel.button" />
  </mjl:form>
</dl>
