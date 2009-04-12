<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.DiagnosticGrid.form.name" id="dss.vector.solutions.surveillance.DiagnosticGrid.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.displayLabel}
    </dd>
    <dt>
      <label>
        ${item.referralNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.referralName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.DiagnosticGridController.edit.mojo" name="dss.vector.solutions.surveillance.DiagnosticGrid.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.surveillance.CaseDiagnosticController.childQuery.mojo" name="dss.vector.solutions.surveillance.CaseDiagnostic.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.surveillance.DiagnosticGridController.viewAll.mojo" name="dss.vector.solutions.surveillance.DiagnosticGrid.viewAll.link" />
