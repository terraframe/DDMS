<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Edit_Person"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.PersonController.update.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.PersonController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.PersonController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.cancel.button" />
  </dl>
</mjl:form>
