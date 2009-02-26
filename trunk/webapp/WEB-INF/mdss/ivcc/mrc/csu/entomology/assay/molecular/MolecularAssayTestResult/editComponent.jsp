<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResult.form.name" id="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResultController.update.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResult.form.update.button" />
  <mjl:command value="Delete" action="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResultController.delete.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResultController.cancel.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.molecular.MolecularAssayTestResult.form.cancel.button" />
</mjl:form>
