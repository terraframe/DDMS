<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResult.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.mosquitoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_biochemical_PNPATestResult_mosquito}" param="mosquito">
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
        <mjl:input type="text" param="testResult" />
        <mjl:messages attribute="testResult">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResultController.update.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResult.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResultController.delete.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResult.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResultController.cancel.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.biochemical.PNPATestResult.form.cancel.button" />
</mjl:form>
