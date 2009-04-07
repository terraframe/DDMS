<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.PeriodTypeMaster.form.name" id="dss.vector.solutions.surveillance.PeriodTypeMaster.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.maximumPeriodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="maximumPeriod" />
        <mjl:messages attribute="maximumPeriod">
          <mjl:message />
        </mjl:messages>
      </dd>
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
  <mjl:command value="Update" action="dss.vector.solutions.surveillance.PeriodTypeMasterController.update.mojo" name="dss.vector.solutions.surveillance.PeriodTypeMaster.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.surveillance.PeriodTypeMasterController.delete.mojo" name="dss.vector.solutions.surveillance.PeriodTypeMaster.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.surveillance.PeriodTypeMasterController.cancel.mojo" name="dss.vector.solutions.surveillance.PeriodTypeMaster.form.cancel.button" />
</mjl:form>
