<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.name" id="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.endPointMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint}" param="endPoint">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.startPointMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint}" param="startPoint">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.LarvaeAgeRangeController.update.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.LarvaeAgeRangeController.delete.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.LarvaeAgeRangeController.cancel.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.cancel.button" />
</mjl:form>
