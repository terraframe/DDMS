<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.FeverTreatmentDisplayLabel.form.name" id="dss.vector.solutions.intervention.FeverTreatmentDisplayLabel.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.defaultLocaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.defaultLocale}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.FeverTreatmentDisplayLabelController.edit.mojo" name="dss.vector.solutions.intervention.FeverTreatmentDisplayLabel.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.FeverTreatmentDisplayLabelController.viewAll.mojo" name="dss.vector.solutions.intervention.FeverTreatmentDisplayLabel.viewAll.link" />
