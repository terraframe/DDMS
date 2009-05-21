<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.DrugDisplayLabel.form.name" id="dss.vector.solutions.intervention.DrugDisplayLabel.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.defaultLocaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="defaultLocale" />
        <mjl:messages attribute="defaultLocale">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.intervention.DrugDisplayLabelController.update.mojo" name="dss.vector.solutions.intervention.DrugDisplayLabel.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.DrugDisplayLabelController.delete.mojo" name="dss.vector.solutions.intervention.DrugDisplayLabel.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.DrugDisplayLabelController.cancel.mojo" name="dss.vector.solutions.intervention.DrugDisplayLabel.form.cancel.button" />
</mjl:form>
