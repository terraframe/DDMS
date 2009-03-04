<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroup.form.name" id="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroup.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.collectionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.identificationMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.quantityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="quantity" />
        <mjl:messages attribute="quantity">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.specieMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupController.update.mojo" name="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroup.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupController.delete.mojo" name="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroup.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroupController.cancel.mojo" name="csu.mrc.ivcc.mdss.entomology.MorphologicalSpecieGroup.form.cancel.button" />
</mjl:form>
