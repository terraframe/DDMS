<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.assay.biochemical.BiochemicalAssayTestResult.form.name" id="mdss.ivcc.mrc.csu.entomology.assay.biochemical.BiochemicalAssayTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="mdss.ivcc.mrc.csu.entomology.assay.biochemical.BiochemicalAssayTestResultController.create.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.biochemical.BiochemicalAssayTestResult.form.create.button" />
</mjl:form>
