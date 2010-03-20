<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_ITNCommunityDistribution" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.id" name="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.update.button" value="Update" action="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionController.update.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.delete.button" value="Delete" action="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.cancel.button" value="Cancel" action="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionController.cancel.mojo" />
  </mjl:form>
</dl>
