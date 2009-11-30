<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Create_WeeklyThreshold" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.WeeklyThreshold.form.id" name="dss.vector.solutions.general.WeeklyThreshold.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.general.WeeklyThreshold.form.create.button" value="Create" action="dss.vector.solutions.general.WeeklyThresholdController.create.mojo" />
  </mjl:form>
</dl>
