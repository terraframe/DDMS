<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.HouseholdPerson.form.name" id="dss.vector.solutions.intervention.monitor.HouseholdPerson.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.HouseholdPersonController.create.mojo" name="dss.vector.solutions.intervention.monitor.HouseholdPerson.form.create.button" />
</mjl:form>
