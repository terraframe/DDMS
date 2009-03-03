<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRange.form.name" id="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRange.form.id" method="POST">
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
  <mjl:command value="Update" action="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRangeController.update.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRange.form.update.button" />
  <mjl:command value="Delete" action="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRangeController.delete.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRange.form.delete.button" />
  <mjl:command value="Cancel" action="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRangeController.cancel.mojo" name="ivcc.mrc.csu.mdss.entomology.assay.AdultAgeRange.form.cancel.button" />
</mjl:form>
