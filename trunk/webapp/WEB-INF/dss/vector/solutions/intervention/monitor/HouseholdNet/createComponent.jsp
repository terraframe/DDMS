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
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.HouseholdNetController.create.mojo" name="dss.vector.solutions.intervention.monitor.HouseholdNet.form.create.button" />
</mjl:form>
