<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.GeometryStyle.form.name" id="dss.vector.solutions.query.GeometryStyle.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.GeometryStyleController.update.mojo" name="dss.vector.solutions.query.GeometryStyle.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.GeometryStyleController.delete.mojo" name="dss.vector.solutions.query.GeometryStyle.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.GeometryStyleController.cancel.mojo" name="dss.vector.solutions.query.GeometryStyle.form.cancel.button" />
  </mjl:form>
</dl>
