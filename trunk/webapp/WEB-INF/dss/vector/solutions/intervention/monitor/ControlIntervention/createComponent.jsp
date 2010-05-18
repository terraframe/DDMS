<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Create_ControlIntervention" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ControlIntervention.form.id" name="dss.vector.solutions.intervention.monitor.ControlIntervention.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command localize="false" name="dss.vector.solutions.intervention.monitor.ControlIntervention.form.create.button" value="Create" action="dss.vector.solutions.intervention.monitor.ControlInterventionController.create.mojo" />
  </mjl:form>
</dl>
