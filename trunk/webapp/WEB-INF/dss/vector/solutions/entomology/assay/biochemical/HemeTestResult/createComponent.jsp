<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.name" id="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="testResult">
<mjl:boolean param="testResult" />
</mjl:dt>
      <mjl:dt attribute="mosquito">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AssayTestResult_mosquito}" param="mosquito">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.biochemical.HemeTestResultController.create.mojo" name="dss.vector.solutions.entomology.assay.biochemical.HemeTestResult.form.create.button" />
</mjl:form>
