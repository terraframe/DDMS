<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_ITNRetreatmentPeriod" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.id" name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.cancel.mojo" />
  </mjl:form>
</dl>
