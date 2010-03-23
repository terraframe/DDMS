<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayOperator.form.name" id="dss.vector.solutions.irs.SprayOperator.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.operatorIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.operatorId}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.SprayOperatorController.edit.mojo" name="dss.vector.solutions.irs.SprayOperator.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink action="dss.vector.solutions.irs.SprayOperatorController.viewAll.mojo" name="dss.vector.solutions.irs.SprayOperator.viewAll.link">
<fmt:message key="View_All" />
</mjl:commandLink>
