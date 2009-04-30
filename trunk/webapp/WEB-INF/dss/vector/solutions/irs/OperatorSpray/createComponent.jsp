<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.OperatorSpray.form.name" id="dss.vector.solutions.irs.OperatorSpray.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.operatorSprayWeekMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="operatorSprayWeek" />
        <mjl:messages attribute="operatorSprayWeek">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.receivedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="received" />
        <mjl:messages attribute="received">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.refillsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="refills" />
        <mjl:messages attribute="refills">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.returnedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="returned" />
        <mjl:messages attribute="returned">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayOperatorMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_OperatorSpray_sprayOperator}" param="sprayOperator">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="sprayOperator">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.usedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="used" />
        <mjl:messages attribute="used">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.targetMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target" />
        <mjl:messages attribute="target">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.teamSprayWeekMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="teamSprayWeek" />
        <mjl:messages attribute="teamSprayWeek">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayDataMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_AbstractSpray_sprayData}" param="sprayData">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="sprayData">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.irs.OperatorSprayController.create.mojo" name="dss.vector.solutions.irs.OperatorSpray.form.create.button" />
</mjl:form>
