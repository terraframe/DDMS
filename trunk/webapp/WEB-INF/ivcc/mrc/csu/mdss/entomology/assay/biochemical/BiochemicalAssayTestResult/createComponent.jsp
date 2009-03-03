<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult.form.name" id="ivcc.mrc.csu.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="ivcc.mrc.csu.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultController.create.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult.form.create.button" />
</mjl:form>
