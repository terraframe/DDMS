<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_CommercialITNProvider" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.id" name="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.CommercialITNProvider.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.CommercialITNProviderController.cancel.mojo" />
  </mjl:form>
</dl>
