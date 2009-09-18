<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_ITNDistribution" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.name" id="dss.vector.solutions.intervention.monitor.ITNDistribution.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.update.mojo" name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.delete.mojo" name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.cancel.button" />
  </mjl:form>
</dl>
