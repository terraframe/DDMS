<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_RenderTypes" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.RenderTypes.form.id" name="dss.vector.solutions.query.RenderTypes.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.query.RenderTypes.form.update.button" value="Update" action="dss.vector.solutions.query.RenderTypesController.update.mojo" />
    <mjl:command name="dss.vector.solutions.query.RenderTypes.form.delete.button" value="Delete" action="dss.vector.solutions.query.RenderTypesController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.query.RenderTypes.form.cancel.button" value="Cancel" action="dss.vector.solutions.query.RenderTypesController.cancel.mojo" />
  </mjl:form>
</dl>
