<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.PatientGrid.form.name" id="dss.vector.solutions.intervention.monitor.PatientGrid.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.activeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="active" />
        <mjl:messages attribute="active">
          <mjl:message />
        </mjl:messages>
      </dd>
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
          ${item.optionNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="optionName" />
        <mjl:messages attribute="optionName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.PatientGridController.update.mojo" name="dss.vector.solutions.intervention.monitor.PatientGrid.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.PatientGridController.delete.mojo" name="dss.vector.solutions.intervention.monitor.PatientGrid.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.PatientGridController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.PatientGrid.form.cancel.button" />
</mjl:form>
