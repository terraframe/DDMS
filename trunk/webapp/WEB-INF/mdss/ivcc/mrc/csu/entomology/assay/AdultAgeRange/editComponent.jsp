<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.name" id="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.endPointMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="endPoint" />
        <mjl:messages attribute="endPoint">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.startPointMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="startPoint" />
        <mjl:messages attribute="startPoint">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRangeController.update.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.update.button" />
  <mjl:command value="Delete" action="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRangeController.delete.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.delete.button" />
  <mjl:command value="Cancel" action="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRangeController.cancel.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.AdultAgeRange.form.cancel.button" />
</mjl:form>
