<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.ThematicVariable.form.name" id="dss.vector.solutions.query.ThematicVariable.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.ThematicVariableController.update.mojo" name="dss.vector.solutions.query.ThematicVariable.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.ThematicVariableController.delete.mojo" name="dss.vector.solutions.query.ThematicVariable.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.ThematicVariableController.cancel.mojo" name="dss.vector.solutions.query.ThematicVariable.form.cancel.button" />
  </mjl:form>
</dl>
