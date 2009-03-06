<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResultController.edit.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResultController.viewAll.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.molecular.MolecularAssayTestResult.viewAll.link" />
