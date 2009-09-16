<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_FreeITNProviderDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.id" name="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabelController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabelController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabel.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.FreeITNProviderDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
