<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.endPointMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.endPoint.keyName}" action="csu.mrc.ivcc.mdss.mo.LarvaeAgeController.view.mojo" name="csu.mrc.ivcc.mdss.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.endPoint.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.startPointMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.startPoint.keyName}" action="csu.mrc.ivcc.mdss.mo.LarvaeAgeController.view.mojo" name="csu.mrc.ivcc.mdss.mo.LarvaeAge.form.view.link">
        <mjl:property value="${item.startPoint.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeController.edit.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeController.viewAll.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.viewAll.link" />
