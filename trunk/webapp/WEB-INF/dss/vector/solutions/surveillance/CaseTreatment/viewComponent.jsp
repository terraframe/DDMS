<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.CaseTreatment.form.name" id="dss.vector.solutions.surveillance.CaseTreatment.form.id" method="POST">
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
        Treatment Grid
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.surveillance.TreatmentGridController.view.mojo" name="dss.vector.solutions.surveillance.TreatmentGrid.form.view.link">
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
  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.CaseTreatmentController.edit.mojo" name="dss.vector.solutions.surveillance.CaseTreatment.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.surveillance.CaseTreatmentController.viewAll.mojo" name="dss.vector.solutions.surveillance.CaseTreatment.viewAll.link" />
