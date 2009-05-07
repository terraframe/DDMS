<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.RoofHeiarchy.form.name" id="dss.vector.solutions.intervention.monitor.RoofHeiarchy.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.RoofHeiarchyController.create.mojo" name="dss.vector.solutions.intervention.monitor.RoofHeiarchy.form.create.button" />
</mjl:form>
