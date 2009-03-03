<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.assay.molecular.GABATestResult.form.name" id="ivcc.mrc.csu.mdss.entomology.assay.molecular.GABATestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.mosquitoMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.mosquito.keyName}" action="ivcc.mrc.csu.mdss.entomology.MosquitoController.view.mojo" name="ivcc.mrc.csu.mdss.entomology.Mosquito.form.view.link">
        <mjl:property value="${item.mosquito.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.testResultMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testResult.keyName}" action="ivcc.mrc.csu.mdss.mo.MolecularAssayResultController.view.mojo" name="ivcc.mrc.csu.mdss.mo.MolecularAssayResult.form.view.link">
        <mjl:property value="${item.testResult.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.GABATestResultController.edit.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.GABATestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.GABATestResultController.viewAll.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.GABATestResult.viewAll.link" />
