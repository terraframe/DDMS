<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.molecular.AcHETestResult.form.name" id="dss.vector.solutions.entomology.assay.molecular.AcHETestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="mosquito">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_molecular_AcHETestResult_mosquito}" param="mosquito">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="testResult">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_molecular_AcHETestResult_testResult}" param="testResult">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.molecular.AcHETestResultController.update.mojo" name="dss.vector.solutions.entomology.assay.molecular.AcHETestResult.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.molecular.AcHETestResultController.delete.mojo" name="dss.vector.solutions.entomology.assay.molecular.AcHETestResult.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.molecular.AcHETestResultController.cancel.mojo" name="dss.vector.solutions.entomology.assay.molecular.AcHETestResult.form.cancel.button" />
</mjl:form>
