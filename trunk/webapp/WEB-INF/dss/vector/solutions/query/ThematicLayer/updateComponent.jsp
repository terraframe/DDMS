<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.ThematicLayer.form.name" id="dss.vector.solutions.query.ThematicLayer.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.ThematicLayerController.update.mojo" name="dss.vector.solutions.query.ThematicLayer.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.ThematicLayerController.delete.mojo" name="dss.vector.solutions.query.ThematicLayer.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.ThematicLayerController.cancel.mojo" name="dss.vector.solutions.query.ThematicLayer.form.cancel.button" />
  </mjl:form>
</dl>
