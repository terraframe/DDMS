<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.name" id="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.testResultMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="testResult" />
      </dd>
      <dt>
        <label>
          ${item.mosquitoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AssayTestResult_mosquito}" param="mosquito">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.biochemical.HemeTestResultController.update.mojo" name="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.biochemical.HemeTestResultController.delete.mojo" name="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.biochemical.HemeTestResultController.cancel.mojo" name="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.cancel.button" />
</mjl:form>
