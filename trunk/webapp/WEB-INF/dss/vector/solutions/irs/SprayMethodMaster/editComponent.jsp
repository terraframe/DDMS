<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayMethodMaster.form.name" id="dss.vector.solutions.irs.SprayMethodMaster.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.enumNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="enumName" />
        <mjl:messages attribute="enumName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.SprayMethodMasterController.update.mojo" name="dss.vector.solutions.irs.SprayMethodMaster.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.SprayMethodMasterController.delete.mojo" name="dss.vector.solutions.irs.SprayMethodMaster.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.SprayMethodMasterController.cancel.mojo" name="dss.vector.solutions.irs.SprayMethodMaster.form.cancel.button" />
</mjl:form>
