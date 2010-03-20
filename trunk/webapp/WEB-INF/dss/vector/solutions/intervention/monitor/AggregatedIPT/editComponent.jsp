<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="page_title" value="Edit_Aggregated_IPT"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.name" id="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.update.mojo" name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.delete.mojo" name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.AggregatedIPTController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.AggregatedIPT.form.cancel.button" />
  </dl>
</mjl:form>
