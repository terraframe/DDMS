<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<mjl:form name="MdWebFormAdminController.editCondition.form" id="MdWebFormAdminController.editCondition.form.id" method="POST">

  <%@include file="conditionForm.jsp" %>

  <div class="form-action-row" id="formActionRow">
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.form.MdFormAdminController.updateCondition.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.updateCondition.button"/>
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.form.MdFormAdminController.cancelCondition.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.cancelCondition.button" />
  </div>
  
</mjl:form>