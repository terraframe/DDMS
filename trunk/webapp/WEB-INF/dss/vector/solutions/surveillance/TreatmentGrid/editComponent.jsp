<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Treatment_Grid_Edit" scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.TreatmentGrid.form.name" id="dss.vector.solutions.surveillance.TreatmentGrid.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mjl:command value="Update" action="dss.vector.solutions.surveillance.TreatmentGridController.update.mojo" name="dss.vector.solutions.surveillance.TreatmentGrid.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.surveillance.TreatmentGridController.delete.mojo" name="dss.vector.solutions.surveillance.TreatmentGrid.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.surveillance.TreatmentGridController.cancel.mojo" name="dss.vector.solutions.surveillance.TreatmentGrid.form.cancel.button" />
  </dl>
</mjl:form>
