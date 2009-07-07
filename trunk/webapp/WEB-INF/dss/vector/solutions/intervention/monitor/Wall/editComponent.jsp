<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_Wall"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Wall.form.name" id="dss.vector.solutions.intervention.monitor.Wall.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%> 
  
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.WallController.update.mojo" name="dss.vector.solutions.intervention.monitor.Wall.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.WallController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Wall.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.WallController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Wall.form.cancel.button" />
  </dl>
</mjl:form>
