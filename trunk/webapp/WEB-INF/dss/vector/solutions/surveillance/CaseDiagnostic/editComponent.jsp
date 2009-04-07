<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.CaseDiagnostic.form.name" id="dss.vector.solutions.surveillance.CaseDiagnostic.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.amountMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="amount" />
        <mjl:messages attribute="amount">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.surveillance.CaseDiagnosticController.update.mojo" name="dss.vector.solutions.surveillance.CaseDiagnostic.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.surveillance.CaseDiagnosticController.delete.mojo" name="dss.vector.solutions.surveillance.CaseDiagnostic.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.surveillance.CaseDiagnosticController.cancel.mojo" name="dss.vector.solutions.surveillance.CaseDiagnostic.form.cancel.button" />
</mjl:form>
