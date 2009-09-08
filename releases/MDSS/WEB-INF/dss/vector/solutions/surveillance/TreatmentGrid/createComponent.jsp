<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Treatment_Grid_Create" scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.TreatmentGrid.form.name" id="dss.vector.solutions.surveillance.TreatmentGrid.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    
    <mjl:command value="Create" action="dss.vector.solutions.surveillance.TreatmentGridController.create.mojo" name="dss.vector.solutions.surveillance.TreatmentGrid.form.create.button" />
  </dl>
</mjl:form>
