<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_WeeklyThreshold" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.WeeklyThreshold.form.id" name="dss.vector.solutions.general.WeeklyThreshold.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.general.WeeklyThreshold.form.update.button" value="Update" action="dss.vector.solutions.general.WeeklyThresholdController.update.mojo" />
    <mjl:command name="dss.vector.solutions.general.WeeklyThreshold.form.delete.button" value="Delete" action="dss.vector.solutions.general.WeeklyThresholdController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.general.WeeklyThreshold.form.cancel.button" value="Cancel" action="dss.vector.solutions.general.WeeklyThresholdController.cancel.mojo" />
  </mjl:form>
</dl>
