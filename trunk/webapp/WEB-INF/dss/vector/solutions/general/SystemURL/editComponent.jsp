<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_SystemURL" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.SystemURL.form.id" name="dss.vector.solutions.general.SystemURL.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command localize="false" name="dss.vector.solutions.general.SystemURL.form.update.button" value="Update" action="dss.vector.solutions.general.SystemURLController.update.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.general.SystemURL.form.delete.button" value="Delete" action="dss.vector.solutions.general.SystemURLController.delete.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.general.SystemURL.form.cancel.button" value="Cancel" action="dss.vector.solutions.general.SystemURLController.cancel.mojo" />
  </mjl:form>
</dl>
