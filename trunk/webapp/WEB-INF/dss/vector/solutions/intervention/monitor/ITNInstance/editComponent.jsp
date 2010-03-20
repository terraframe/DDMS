<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_ITNInstance" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNInstance.form.id" name="dss.vector.solutions.intervention.monitor.ITNInstance.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNInstance.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.ITNInstanceController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNInstance.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.ITNInstanceController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNInstance.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.ITNInstanceController.cancel.mojo" />
  </mjl:form>
</dl>
