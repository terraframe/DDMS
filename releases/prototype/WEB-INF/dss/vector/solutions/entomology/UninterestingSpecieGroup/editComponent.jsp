<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.UninterestingSpecieGroup.form.name" id="dss.vector.solutions.entomology.UninterestingSpecieGroup.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.quanityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="quanity" />
        <mjl:messages attribute="quanity">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.collectionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.collectionIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="collectionId" />
        <mjl:messages attribute="collectionId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.identificationMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.specieMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.UninterestingSpecieGroupController.update.mojo" name="dss.vector.solutions.entomology.UninterestingSpecieGroup.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.UninterestingSpecieGroupController.delete.mojo" name="dss.vector.solutions.entomology.UninterestingSpecieGroup.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.UninterestingSpecieGroupController.cancel.mojo" name="dss.vector.solutions.entomology.UninterestingSpecieGroup.form.cancel.button" />
</mjl:form>
