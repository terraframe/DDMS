<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResult.form.name" id="dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResultController.create.mojo" name="dss.vector.solutions.entomology.assay.biochemical.BiochemicalAssayTestResult.form.create.button" />
</mjl:form>
