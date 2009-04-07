<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.TreatmentMethodGrid.form.name" id="dss.vector.solutions.surveillance.TreatmentMethodGrid.form.id" method="POST">
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
          ${item.referralNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="referralName" />
        <mjl:messages attribute="referralName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.surveillance.TreatmentMethodGridController.update.mojo" name="dss.vector.solutions.surveillance.TreatmentMethodGrid.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.surveillance.TreatmentMethodGridController.delete.mojo" name="dss.vector.solutions.surveillance.TreatmentMethodGrid.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.surveillance.TreatmentMethodGridController.cancel.mojo" name="dss.vector.solutions.surveillance.TreatmentMethodGrid.form.cancel.button" />
</mjl:form>
