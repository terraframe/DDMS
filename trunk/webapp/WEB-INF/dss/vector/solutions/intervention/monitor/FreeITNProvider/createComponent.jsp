<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Create_FreeITNProvider" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.id" name="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.FreeITNProvider.form.create.button" value="Create" action="dss.vector.solutions.intervention.monitor.FreeITNProviderController.create.mojo" />
  </mjl:form>
</dl>
