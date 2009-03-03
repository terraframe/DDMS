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
  <mjl:command value="Create" action="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResultController.create.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.molecular.MolecularAssayTestResult.form.create.button" />
</mjl:form>
