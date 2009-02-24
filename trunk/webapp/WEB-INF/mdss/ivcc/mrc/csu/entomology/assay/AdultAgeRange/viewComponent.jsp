<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.name" id="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.endPointMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.endPoint}
    </dd>
    <dt>
      <label>
        ${item.startPointMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.startPoint}
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRangeController.edit.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRangeController.viewAll.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.viewAll.link" />
