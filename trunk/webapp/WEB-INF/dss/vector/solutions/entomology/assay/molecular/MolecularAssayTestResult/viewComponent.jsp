<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult.form.name" id="dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResultController.edit.mojo" name="dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResultController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.molecular.MolecularAssayTestResult.viewAll.link" />
