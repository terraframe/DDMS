<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.NetHeiarchy.form.name" id="dss.vector.solutions.intervention.monitor.NetHeiarchy.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.NetHeiarchyController.update.mojo" name="dss.vector.solutions.intervention.monitor.NetHeiarchy.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.NetHeiarchyController.delete.mojo" name="dss.vector.solutions.intervention.monitor.NetHeiarchy.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.NetHeiarchyController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.NetHeiarchy.form.cancel.button" />
</mjl:form>
