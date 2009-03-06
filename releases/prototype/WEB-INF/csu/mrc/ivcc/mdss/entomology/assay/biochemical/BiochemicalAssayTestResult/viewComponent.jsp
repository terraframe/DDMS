<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultController.edit.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResultController.viewAll.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.biochemical.BiochemicalAssayTestResult.viewAll.link" />
