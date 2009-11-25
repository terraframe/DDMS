<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_HasLayers" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.HasLayers.form.name" id="dss.vector.solutions.query.HasLayers.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.HasLayersController.update.mojo" name="dss.vector.solutions.query.HasLayers.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.HasLayersController.delete.mojo" name="dss.vector.solutions.query.HasLayers.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.HasLayersController.cancel.mojo" name="dss.vector.solutions.query.HasLayers.form.cancel.button" />
  </mjl:form>
</dl>
