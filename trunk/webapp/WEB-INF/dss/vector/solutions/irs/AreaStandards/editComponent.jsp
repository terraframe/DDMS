<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.AreaStandards.form.name" id="dss.vector.solutions.irs.AreaStandards.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.houseMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="house" />
        <mjl:messages attribute="house">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.householdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="household" />
        <mjl:messages attribute="household">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.roomMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="room" />
        <mjl:messages attribute="room">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.AreaStandardsController.update.mojo" name="dss.vector.solutions.irs.AreaStandards.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.AreaStandardsController.delete.mojo" name="dss.vector.solutions.irs.AreaStandards.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.AreaStandardsController.cancel.mojo" name="dss.vector.solutions.irs.AreaStandards.form.cancel.button" />
</mjl:form>
