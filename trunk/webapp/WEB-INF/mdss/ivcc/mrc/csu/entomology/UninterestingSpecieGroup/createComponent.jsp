<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.UninterestingSpecieGroup.form.name" id="mdss.ivcc.mrc.csu.entomology.UninterestingSpecieGroup.form.id" method="POST">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_TrueSpecieEntity_collection}" param="collection">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_TrueSpecieEntity_identificationMethod}" param="identificationMethod">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_TrueSpecieEntity_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="mdss.ivcc.mrc.csu.entomology.UninterestingSpecieGroupController.create.mojo" name="mdss.ivcc.mrc.csu.entomology.UninterestingSpecieGroup.form.create.button" />
</mjl:form>
