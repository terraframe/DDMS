<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.WindowMaster.form.name" id="dss.vector.solutions.intervention.monitor.WindowMaster.form.id" method="POST">
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
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.WindowMasterController.update.mojo" name="dss.vector.solutions.intervention.monitor.WindowMaster.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.WindowMasterController.delete.mojo" name="dss.vector.solutions.intervention.monitor.WindowMaster.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.WindowMasterController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.WindowMaster.form.cancel.button" />
</mjl:form>
