<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Create_SystemAlertDisplayName" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.SystemAlertDisplayName.form.id" name="dss.vector.solutions.general.SystemAlertDisplayName.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.general.SystemAlertDisplayName.form.create.button" value="Create" action="dss.vector.solutions.general.SystemAlertDisplayNameController.create.mojo" />
  </mjl:form>
</dl>
