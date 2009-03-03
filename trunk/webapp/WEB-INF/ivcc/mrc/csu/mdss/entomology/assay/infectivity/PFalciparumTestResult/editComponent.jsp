<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResult.form.name" id="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.mosquitoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_infectivity_PFalciparumTestResult_mosquito}" param="mosquito">
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
  <mjl:command value="Update" action="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResultController.update.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResult.form.update.button" />
  <mjl:command value="Delete" action="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResultController.delete.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResultController.cancel.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.infectivity.PFalciparumTestResult.form.cancel.button" />
</mjl:form>
