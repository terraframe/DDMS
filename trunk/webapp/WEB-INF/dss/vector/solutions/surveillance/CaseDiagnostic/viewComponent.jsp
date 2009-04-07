<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.CaseDiagnostic.form.name" id="dss.vector.solutions.surveillance.CaseDiagnostic.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Age Group
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.surveillance.AggregatedCaseController.view.mojo" name="dss.vector.solutions.surveillance.AggregatedCase.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Diagnostic Grid
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.surveillance.DiagnosticGridController.view.mojo" name="dss.vector.solutions.surveillance.DiagnosticGrid.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.amountMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.amount}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.CaseDiagnosticController.edit.mojo" name="dss.vector.solutions.surveillance.CaseDiagnostic.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.surveillance.CaseDiagnosticController.viewAll.mojo" name="dss.vector.solutions.surveillance.CaseDiagnostic.viewAll.link" />
