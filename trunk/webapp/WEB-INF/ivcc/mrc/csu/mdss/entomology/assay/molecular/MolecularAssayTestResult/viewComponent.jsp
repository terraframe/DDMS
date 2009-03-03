<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.name" id="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResultController.edit.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResultController.viewAll.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.viewAll.link" />
