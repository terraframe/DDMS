<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_WeeklyThreshold" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.general.WeeklyThreshold.form.name" id="dss.vector.solutions.general.WeeklyThreshold.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.general.WeeklyThresholdController.update.mojo" name="dss.vector.solutions.general.WeeklyThreshold.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.general.WeeklyThresholdController.delete.mojo" name="dss.vector.solutions.general.WeeklyThreshold.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.general.WeeklyThresholdController.cancel.mojo" name="dss.vector.solutions.general.WeeklyThreshold.form.cancel.button" />
  </mjl:form>
</dl>
