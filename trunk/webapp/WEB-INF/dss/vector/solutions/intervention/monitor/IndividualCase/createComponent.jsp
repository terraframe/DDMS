<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_IndividualCase" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.IndividualCase.form.name" id="dss.vector.solutions.intervention.monitor.IndividualCase.form.id" method="POST">
    <mjl:input type="hidden" param="personId" value="${personId}"/>
    <%@include file="form.jsp" %>
    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.IndividualCaseController.create.mojo" name="dss.vector.solutions.intervention.monitor.IndividualCase.form.create.button" />
  </mjl:form>
</dl>
