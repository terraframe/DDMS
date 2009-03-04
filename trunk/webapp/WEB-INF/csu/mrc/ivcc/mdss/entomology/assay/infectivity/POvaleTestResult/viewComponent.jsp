<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.infectivity.POvaleTestResult.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.infectivity.POvaleTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.mosquitoMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.mosquito.keyName}" action="csu.mrc.ivcc.mdss.entomology.MosquitoController.view.mojo" name="csu.mrc.ivcc.mdss.entomology.Mosquito.form.view.link">
        <mjl:property value="${item.mosquito.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.testResultMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.testResult}
    </dd>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.entomology.assay.infectivity.POvaleTestResultController.edit.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.infectivity.POvaleTestResult.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.entomology.assay.infectivity.POvaleTestResultController.viewAll.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.infectivity.POvaleTestResult.viewAll.link" />
