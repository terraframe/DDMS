<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Create_Patient_Grid"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.PatientGrid.form.name" id="dss.vector.solutions.intervention.monitor.PatientGrid.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.PatientGridController.create.mojo" name="dss.vector.solutions.intervention.monitor.PatientGrid.form.create.button" />
  </dl>
</mjl:form>
