<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.name" id="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.defaultLocaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="defaultLocale" />
        <mjl:messages attribute="defaultLocale">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.NetDisplayLabelController.update.mojo" name="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.NetDisplayLabelController.delete.mojo" name="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.NetDisplayLabelController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.NetDisplayLabel.form.cancel.button" />
</mjl:form>
