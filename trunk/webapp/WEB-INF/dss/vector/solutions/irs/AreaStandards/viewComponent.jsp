<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.AreaStandards.form.name" id="dss.vector.solutions.irs.AreaStandards.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.houseMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.house}
    </dd>
    <dt>
      <label>
        ${item.householdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.household}
    </dd>
    <dt>
      <label>
        ${item.roomMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.room}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.AreaStandardsController.edit.mojo" name="dss.vector.solutions.irs.AreaStandards.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.AreaStandardsController.viewAll.mojo" name="dss.vector.solutions.irs.AreaStandards.viewAll.link" />
