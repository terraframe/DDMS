<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Edit_Fever_Treatment"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.FeverTreatment.form.name" id="dss.vector.solutions.intervention.FeverTreatment.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>

    <mjl:command value="Update" action="dss.vector.solutions.intervention.FeverTreatmentController.update.mojo" name="dss.vector.solutions.intervention.FeverTreatment.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.FeverTreatmentController.delete.mojo" name="dss.vector.solutions.intervention.FeverTreatment.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.FeverTreatmentController.cancel.mojo" name="dss.vector.solutions.intervention.FeverTreatment.form.cancel.button" />
  </dl>
</mjl:form>
