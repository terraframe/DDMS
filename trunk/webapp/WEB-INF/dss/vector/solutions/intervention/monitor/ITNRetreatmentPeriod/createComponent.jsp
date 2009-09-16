<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Create_ITNRetreatmentPeriod" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.id" name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriod.form.create.button" value="Create" action="dss.vector.solutions.intervention.monitor.ITNRetreatmentPeriodController.create.mojo" />
  </mjl:form>
</dl>
