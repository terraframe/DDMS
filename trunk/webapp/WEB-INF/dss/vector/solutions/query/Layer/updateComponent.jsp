<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.Layer.form.name" id="dss.vector.solutions.query.Layer.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.LayerController.update.mojo" name="dss.vector.solutions.query.Layer.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.LayerController.delete.mojo" name="dss.vector.solutions.query.Layer.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.LayerController.cancel.mojo" name="dss.vector.solutions.query.Layer.form.cancel.button" />
  </mjl:form>
</dl>
