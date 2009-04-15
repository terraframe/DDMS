<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.molecular.GABASTestResult.form.name" id="dss.vector.solutions.entomology.assay.molecular.GABASTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="testResult">
<mjl:commandLink display="${item.testResult.keyName}" action="dss.vector.solutions.mo.MolecularAssayResultController.view.mojo" name="dss.vector.solutions.mo.MolecularAssayResult.form.view.link">
        <mjl:property value="${item.testResult.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="testMethod">
<mjl:commandLink display="${item.testMethod.keyName}" action="dss.vector.solutions.mo.InsecticideMethodologyController.view.mojo" name="dss.vector.solutions.mo.InsecticideMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="mosquito">
<mjl:commandLink display="${item.mosquito.keyName}" action="dss.vector.solutions.entomology.MosquitoController.view.mojo" name="dss.vector.solutions.entomology.Mosquito.form.view.link">
        <mjl:property value="${item.mosquito.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.molecular.GABASTestResultController.edit.mojo" name="dss.vector.solutions.entomology.assay.molecular.GABASTestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.molecular.GABASTestResultController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.molecular.GABASTestResult.viewAll.link" />
