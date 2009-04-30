<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.InsecticideNozzle.form.name" id="dss.vector.solutions.irs.InsecticideNozzle.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.coverageMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="coverage" />
        <mjl:messages attribute="coverage">
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
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.InsecticideNozzleController.update.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.InsecticideNozzleController.delete.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.InsecticideNozzleController.cancel.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.form.cancel.button" />
</mjl:form>
