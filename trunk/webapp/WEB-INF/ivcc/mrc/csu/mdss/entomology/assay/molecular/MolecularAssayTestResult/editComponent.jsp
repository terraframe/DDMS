<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.name" id="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResultController.update.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.update.button" />
  <mjl:command value="Delete" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResultController.delete.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.delete.button" />
  <mjl:command value="Cancel" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResultController.cancel.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.cancel.button" />
</mjl:form>
