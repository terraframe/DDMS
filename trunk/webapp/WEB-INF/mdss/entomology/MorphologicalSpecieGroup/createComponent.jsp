<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.MorphologicalSpecieGroup.form.name" id="mdss.entomology.MorphologicalSpecieGroup.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.collectionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_MorphologicalSpecieGroup_collection}" param="collection">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_MorphologicalSpecieGroup_identificationMethod}" param="identificationMethod">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_entomology_MorphologicalSpecieGroup_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="mdss.entomology.MorphologicalSpecieGroupController.create.mojo" name="mdss.entomology.MorphologicalSpecieGroup.form.create.button" />
</mjl:form>
