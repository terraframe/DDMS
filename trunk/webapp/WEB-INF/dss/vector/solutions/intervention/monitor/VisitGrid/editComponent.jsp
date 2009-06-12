<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_Visit_Grid"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.VisitGrid.form.name" id="dss.vector.solutions.intervention.monitor.VisitGrid.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.VisitGridController.update.mojo" name="dss.vector.solutions.intervention.monitor.VisitGrid.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.VisitGridController.delete.mojo" name="dss.vector.solutions.intervention.monitor.VisitGrid.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.VisitGridController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.VisitGrid.form.cancel.button" />
  </dl>
</mjl:form>
