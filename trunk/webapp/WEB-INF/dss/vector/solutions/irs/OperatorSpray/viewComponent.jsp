<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.OperatorSpray.form.name" id="dss.vector.solutions.irs.OperatorSpray.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.operatorSprayWeekMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.operatorSprayWeek}
    </dd>
    <dt>
      <label>
        ${item.receivedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.received}
    </dd>
    <dt>
      <label>
        ${item.refillsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.refills}
    </dd>
    <dt>
      <label>
        ${item.returnedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.returned}
    </dd>
    <dt>
      <label>
        ${item.sprayOperatorMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.sprayOperator.keyName}" action="dss.vector.solutions.irs.SprayOperatorController.view.mojo" name="dss.vector.solutions.irs.SprayOperator.form.view.link">
        <mjl:property value="${item.sprayOperator.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.usedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.used}
    </dd>
    <dt>
      <label>
        ${item.targetMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.target}
    </dd>
    <dt>
      <label>
        ${item.teamSprayWeekMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.teamSprayWeek}
    </dd>
    <dt>
      <label>
        ${item.sprayDataMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.sprayData.keyName}" action="dss.vector.solutions.irs.SprayDataController.view.mojo" name="dss.vector.solutions.irs.SprayData.form.view.link">
        <mjl:property value="${item.sprayData.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.OperatorSprayController.edit.mojo" name="dss.vector.solutions.irs.OperatorSpray.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.OperatorSprayController.viewAll.mojo" name="dss.vector.solutions.irs.OperatorSpray.viewAll.link" />
