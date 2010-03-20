<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page_title" value="Edit_Person"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.id" name="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.SurveyedPerson.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.SurveyedPersonController.cancel.mojo" />
  </mjl:form>
</dl>
