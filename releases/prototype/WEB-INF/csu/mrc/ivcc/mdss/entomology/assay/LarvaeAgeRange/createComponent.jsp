<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.form.name" id="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.form.id" method="POST">
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
  <mjl:command value="Create" action="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRangeController.create.mojo" name="csu.mrc.ivcc.mdss.entomology.assay.LarvaeAgeRange.form.create.button" />
</mjl:form>
