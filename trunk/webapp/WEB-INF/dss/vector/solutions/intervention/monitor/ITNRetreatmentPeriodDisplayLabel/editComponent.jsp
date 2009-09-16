<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_ITNRetreatmentPeriodDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabel.form.id" name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabel.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabelController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabel.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabelController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabel.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
