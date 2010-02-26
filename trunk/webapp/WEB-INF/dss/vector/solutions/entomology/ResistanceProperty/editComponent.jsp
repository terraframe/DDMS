<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_Property" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.entomology.ResistanceProperty.form.id" name="dss.vector.solutions.entomology.ResistanceProperty.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.entomology.ResistanceProperty.form.update.button" value="Update" action="dss.vector.solutions.entomology.ResistancePropertyController.update.mojo" />
    <mjl:command name="dss.vector.solutions.entomology.ResistanceProperty.form.cancel.button" value="Cancel" action="dss.vector.solutions.entomology.ResistancePropertyController.cancel.mojo" />
  </mjl:form>
</dl>
