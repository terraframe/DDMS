<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_ITNHouseholdSurvey" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.id" name="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.cancel.mojo" />
  </mjl:form>
</dl>
