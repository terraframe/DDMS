<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_CommercialITNProviderDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabel.form.id" name="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabel.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabelController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabel.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabelController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabel.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
