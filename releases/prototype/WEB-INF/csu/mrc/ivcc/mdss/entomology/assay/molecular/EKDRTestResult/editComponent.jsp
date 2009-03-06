<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResult.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.mosquitoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_mosquito}" param="mosquito">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.testResultMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_molecular_EKDRTestResult_testResult}" param="testResult">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultController.update.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResult.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultController.delete.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResultController.cancel.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.molecular.EKDRTestResult.form.cancel.button" />
</mjl:form>
