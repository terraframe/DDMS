<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_Team_Spray" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.TeamSpray.form.name" id="dss.vector.solutions.irs.TeamSpray.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
      
    <mdss:localize key="Update" var="Localized_Update" />
      
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.irs.TeamSprayController.update.mojo" name="dss.vector.solutions.irs.TeamSpray.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.irs.TeamSprayController.delete.mojo" name="dss.vector.solutions.irs.TeamSpray.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.irs.TeamSprayController.cancel.mojo" name="dss.vector.solutions.irs.TeamSpray.form.cancel.button" />
  </dl>
</mjl:form>
