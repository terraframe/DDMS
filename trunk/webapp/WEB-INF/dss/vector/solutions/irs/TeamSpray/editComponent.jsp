<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_Operator_Spray" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.TeamSpray.form.name" id="dss.vector.solutions.irs.TeamSpray.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
      
    <mjl:command value="Update" action="dss.vector.solutions.irs.TeamSprayController.update.mojo" name="dss.vector.solutions.irs.TeamSpray.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.irs.TeamSprayController.delete.mojo" name="dss.vector.solutions.irs.TeamSpray.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.irs.TeamSprayController.cancel.mojo" name="dss.vector.solutions.irs.TeamSpray.form.cancel.button" />
  </dl>
</mjl:form>
