<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_IndividualInstance" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.name" id="dss.vector.solutions.intervention.monitor.IndividualInstance.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.update.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.delete.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.cancel.button" />
  </mjl:form>
</dl>
