<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayOperator.form.name" id="dss.vector.solutions.irs.SprayOperator.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.operatorIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="operatorId" />
        <mjl:messages attribute="operatorId">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.SprayOperatorController.update.mojo" name="dss.vector.solutions.irs.SprayOperator.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.SprayOperatorController.delete.mojo" name="dss.vector.solutions.irs.SprayOperator.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.SprayOperatorController.cancel.mojo" name="dss.vector.solutions.irs.SprayOperator.form.cancel.button" />
</mjl:form>
