<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultTestInterval.form.name" id="dss.vector.solutions.entomology.assay.AdultTestInterval.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.assayMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AdultTestInterval_assay}" param="assay">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.knockedDownMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="knockedDown" />
        <mjl:messages attribute="knockedDown">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.periodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="period" />
        <mjl:messages attribute="period">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.AdultTestIntervalController.update.mojo" name="dss.vector.solutions.entomology.assay.AdultTestInterval.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.AdultTestIntervalController.delete.mojo" name="dss.vector.solutions.entomology.assay.AdultTestInterval.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.AdultTestIntervalController.cancel.mojo" name="dss.vector.solutions.entomology.assay.AdultTestInterval.form.cancel.button" />
</mjl:form>
