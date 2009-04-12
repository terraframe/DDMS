<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.CaseReferral.form.name" id="dss.vector.solutions.surveillance.CaseReferral.form.id" method="POST">
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
  <mjl:command value="Create" action="dss.vector.solutions.surveillance.CaseReferralController.create.mojo" name="dss.vector.solutions.surveillance.CaseReferral.form.create.button" />
</mjl:form>
