<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.assay.AssayTestResult.form.name" id="ivcc.mrc.csu.mdss.entomology.assay.AssayTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.mosquitoMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.mosquito.keyName}" action="ivcc.mrc.csu.mdss.entomology.MosquitoController.view.mojo" name="ivcc.mrc.csu.mdss.entomology.Mosquito.form.view.link">
        <mjl:property value="${item.mosquito.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.entomology.assay.AssayTestResultController.edit.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.AssayTestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.entomology.assay.AssayTestResultController.viewAll.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.AssayTestResult.viewAll.link" />
