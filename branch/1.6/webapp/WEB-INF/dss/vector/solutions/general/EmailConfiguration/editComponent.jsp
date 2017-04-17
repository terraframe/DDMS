<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_EmailConfiguration" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.EmailConfiguration.form.id" name="dss.vector.solutions.general.EmailConfiguration.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command name="dss.vector.solutions.general.EmailConfiguration.form.update.button" value="${Localized_Update}" action="dss.vector.solutions.general.EmailConfigurationController.update.mojo" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command name="dss.vector.solutions.general.EmailConfiguration.form.cancel.button" value="${Localized_Cancel}" action="dss.vector.solutions.general.EmailConfigurationController.cancel.mojo" />
  </mjl:form>
</dl>
