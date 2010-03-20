<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_IndividualCase" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualCase.form.name" id="dss.vector.solutions.intervention.monitor.IndividualCase.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.update.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.delete.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.form.cancel.button" />
  </mjl:form>
</dl>
