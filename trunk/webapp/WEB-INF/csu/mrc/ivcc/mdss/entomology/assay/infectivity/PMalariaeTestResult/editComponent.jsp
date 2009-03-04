<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResult.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.mosquitoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_infectivity_PMalariaeTestResult_mosquito}" param="mosquito">
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
        <mjl:boolean param="testResult" />
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResultController.update.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResult.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResultController.delete.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResultController.cancel.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.infectivity.PMalariaeTestResult.form.cancel.button" />
</mjl:form>
