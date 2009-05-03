<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult.form.name" id="dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="mosquito">
<mjl:commandLink display="${item.mosquito.keyName}" action="dss.vector.solutions.entomology.MosquitoController.view.mojo" name="dss.vector.solutions.entomology.Mosquito.form.view.link">
        <mjl:property value="${item.mosquito.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="testResult">
      ${item.testResult}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResultController.edit.mojo" name="dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResultController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.biochemical.BEsteraseTestResult.viewAll.link" />
