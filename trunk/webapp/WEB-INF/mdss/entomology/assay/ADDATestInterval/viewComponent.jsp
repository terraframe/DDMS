<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.assay.ADDATestInterval.form.name" id="mdss.entomology.assay.ADDATestInterval.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.assayMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.assay.keyName}" action="mdss.entomology.assay.AdultDiscriminatingDoseAssayController.view.mojo" name="mdss.entomology.assay.AdultDiscriminatingDoseAssay.form.view.link">
        <mjl:property value="${item.assay.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.knockedDownMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.knockedDown}
    </dd>
    <dt>
      <label>
        ${item.periodMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.period}
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.entomology.assay.ADDATestIntervalController.edit.mojo" name="mdss.entomology.assay.ADDATestInterval.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.entomology.assay.ADDATestIntervalController.viewAll.mojo" name="mdss.entomology.assay.ADDATestInterval.viewAll.link" />
