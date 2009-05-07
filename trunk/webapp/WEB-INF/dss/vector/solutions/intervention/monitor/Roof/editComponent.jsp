<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Roof.form.name" id="dss.vector.solutions.intervention.monitor.Roof.form.id" method="POST">
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
          ${item.roofNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="roofName" />
        <mjl:messages attribute="roofName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.RoofController.update.mojo" name="dss.vector.solutions.intervention.monitor.Roof.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.RoofController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Roof.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.RoofController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Roof.form.cancel.button" />
</mjl:form>
