<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.name" id="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.update.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.delete.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.cancel.button" />
</mjl:form>
