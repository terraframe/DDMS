<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.FeverTreatment.form.name" id="dss.vector.solutions.intervention.FeverTreatment.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
        <dt>
          <label>
            ${item.displayLabel.defaultLocaleMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.displayLabel.defaultLocale}
        </dd>
      </dl>
    </dd>
    <dt>
      <label>
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>
    <dt>
      <label>
        ${item.treatmentNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.treatmentName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.FeverTreatmentController.edit.mojo" name="dss.vector.solutions.intervention.FeverTreatment.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.FeverTreatmentController.viewAll.mojo" name="dss.vector.solutions.intervention.FeverTreatment.viewAll.link" />
