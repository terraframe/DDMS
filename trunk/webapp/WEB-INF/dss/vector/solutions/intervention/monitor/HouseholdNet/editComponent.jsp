<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.HouseholdNet.form.name" id="dss.vector.solutions.intervention.monitor.HouseholdNet.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="amount">
        <mjl:input type="text" param="amount" />
        
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.HouseholdNetController.update.mojo" name="dss.vector.solutions.intervention.monitor.HouseholdNet.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.HouseholdNetController.delete.mojo" name="dss.vector.solutions.intervention.monitor.HouseholdNet.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.HouseholdNetController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.HouseholdNet.form.cancel.button" />
</mjl:form>
