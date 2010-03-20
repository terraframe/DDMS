<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_IndividualIPTCase" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.id" name="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.IndividualIPTCase.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.IndividualIPTCaseController.cancel.mojo" />
  </mjl:form>
</dl>
