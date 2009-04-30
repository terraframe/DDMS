<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.Nozzle.form.name" id="dss.vector.solutions.irs.Nozzle.form.id" method="POST">
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
          ${item.ratioMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="ratio" />
        <mjl:messages attribute="ratio">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.NozzleController.update.mojo" name="dss.vector.solutions.irs.Nozzle.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.NozzleController.delete.mojo" name="dss.vector.solutions.irs.Nozzle.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.NozzleController.cancel.mojo" name="dss.vector.solutions.irs.Nozzle.form.cancel.button" />
</mjl:form>
