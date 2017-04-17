<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_ITN_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.ITNData.form.name" id="dss.vector.solutions.intervention.monitor.ITNData.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mdss:localize key="Update" var="Localized_Update" />
  
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.intervention.monitor.ITNDataController.update.mojo" name="dss.vector.solutions.intervention.monitor.ITNData.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.intervention.monitor.ITNDataController.delete.mojo" name="dss.vector.solutions.intervention.monitor.ITNData.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.intervention.monitor.ITNDataController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.ITNData.form.cancel.button" />
  </dl>
</mjl:form>
