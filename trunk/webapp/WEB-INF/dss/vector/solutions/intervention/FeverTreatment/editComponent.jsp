<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.FeverTreatment.form.name" id="dss.vector.solutions.intervention.FeverTreatment.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <dl>
          <mjl:struct param="displayLabel">
            <dt>
              <label>
                ${item.displayLabel.defaultLocaleMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="defaultLocale" />
              <mjl:messages attribute="defaultLocale">
                <mjl:message />
              </mjl:messages>
            </dd>
          </mjl:struct>
        </dl>
      </dd>
      <dt>
        <label>
          ${item.enabledMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="enabled" />
        <mjl:messages attribute="enabled">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.treatmentNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="treatmentName" />
        <mjl:messages attribute="treatmentName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.FeverTreatmentController.update.mojo" name="dss.vector.solutions.intervention.FeverTreatment.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.FeverTreatmentController.delete.mojo" name="dss.vector.solutions.intervention.FeverTreatment.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.FeverTreatmentController.cancel.mojo" name="dss.vector.solutions.intervention.FeverTreatment.form.cancel.button" />
</mjl:form>
