<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_IndividualInstance" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.name" id="dss.vector.solutions.intervention.monitor.IndividualInstance.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.update.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.delete.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.intervention.monitor.IndividualInstanceController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.IndividualInstance.form.cancel.button" />
  </mjl:form>
</dl>
