<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_FreeITNProvider" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.id" name="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.FreeITNProviderController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.FreeITNProviderController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.FreeITNProviderController.cancel.mojo" />
  </mjl:form>
</dl>
