<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResult.form.name" id="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.mosquitoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_biochemical_AEsteraseTestResult_mosquito}" param="mosquito">
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
  <mjl:command value="Update" action="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResultController.update.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResult.form.update.button" />
  <mjl:command value="Delete" action="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResultController.delete.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResultController.cancel.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.biochemical.AEsteraseTestResult.form.cancel.button" />
</mjl:form>
