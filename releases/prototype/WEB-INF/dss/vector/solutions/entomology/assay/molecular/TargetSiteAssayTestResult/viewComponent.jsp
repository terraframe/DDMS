<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult.form.name" id="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.testMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testMethod.keyName}" action="dss.vector.solutions.mo.InsecticideMethodologyController.view.mojo" name="dss.vector.solutions.mo.InsecticideMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.mosquitoMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.mosquito.keyName}" action="dss.vector.solutions.entomology.MosquitoController.view.mojo" name="dss.vector.solutions.entomology.Mosquito.form.view.link">
        <mjl:property value="${item.mosquito.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResultController.edit.mojo" name="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResultController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.molecular.TargetSiteAssayTestResult.viewAll.link" />
