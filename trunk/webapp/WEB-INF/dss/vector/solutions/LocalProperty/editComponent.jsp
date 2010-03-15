<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_LocalProperty" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.LocalProperty.form.id" name="dss.vector.solutions.LocalProperty.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.LocalProperty.form.update.button" value="Update" action="dss.vector.solutions.LocalPropertyController.update.mojo" />
    <mjl:command name="dss.vector.solutions.LocalProperty.form.delete.button" value="Delete" action="dss.vector.solutions.LocalPropertyController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.LocalProperty.form.cancel.button" value="Cancel" action="dss.vector.solutions.LocalPropertyController.cancel.mojo" />
  </mjl:form>
</dl>
