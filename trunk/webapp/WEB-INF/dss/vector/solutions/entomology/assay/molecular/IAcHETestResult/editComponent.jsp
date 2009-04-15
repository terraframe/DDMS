<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.molecular.IAcHETestResult.form.name" id="dss.vector.solutions.entomology.assay.molecular.IAcHETestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="testResult">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_molecular_IAcHETestResult_testResult}" param="testResult">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="testMethod">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_molecular_TargetSiteAssayTestResult_testMethod}" param="testMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="mosquito">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AssayTestResult_mosquito}" param="mosquito">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.molecular.IAcHETestResultController.update.mojo" name="dss.vector.solutions.entomology.assay.molecular.IAcHETestResult.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.molecular.IAcHETestResultController.delete.mojo" name="dss.vector.solutions.entomology.assay.molecular.IAcHETestResult.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.molecular.IAcHETestResultController.cancel.mojo" name="dss.vector.solutions.entomology.assay.molecular.IAcHETestResult.form.cancel.button" />
</mjl:form>
