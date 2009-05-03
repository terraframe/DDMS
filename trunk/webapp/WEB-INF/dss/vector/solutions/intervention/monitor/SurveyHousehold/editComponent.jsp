<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.SurveyHousehold.form.name" id="dss.vector.solutions.intervention.monitor.SurveyHousehold.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.SurveyHouseholdController.update.mojo" name="dss.vector.solutions.intervention.monitor.SurveyHousehold.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.SurveyHouseholdController.delete.mojo" name="dss.vector.solutions.intervention.monitor.SurveyHousehold.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.SurveyHouseholdController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.SurveyHousehold.form.cancel.button" />
</mjl:form>
