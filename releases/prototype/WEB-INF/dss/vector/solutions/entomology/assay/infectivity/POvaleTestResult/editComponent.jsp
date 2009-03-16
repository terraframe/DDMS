<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult.form.name" id="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.mosquitoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_infectivity_POvaleTestResult_mosquito}" param="mosquito">
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
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultController.update.mojo" name="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultController.delete.mojo" name="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResultController.cancel.mojo" name="dss.vector.solutions.entomology.assay.infectivity.POvaleTestResult.form.cancel.button" />
</mjl:form>
