<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_EmailTemplateVariables" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.EmailTemplateVariables.form.id" name="dss.vector.solutions.general.EmailTemplateVariables.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.general.EmailTemplateVariables.form.update.button" value="Update" action="dss.vector.solutions.general.EmailTemplateVariablesController.update.mojo" />
    <mjl:command name="dss.vector.solutions.general.EmailTemplateVariables.form.delete.button" value="Delete" action="dss.vector.solutions.general.EmailTemplateVariablesController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.general.EmailTemplateVariables.form.cancel.button" value="Cancel" action="dss.vector.solutions.general.EmailTemplateVariablesController.cancel.mojo" />
  </mjl:form>
</dl>
