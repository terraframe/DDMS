<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.entomology.MorphologicalSpecieGroup.form.name" id="mdss.entomology.MorphologicalSpecieGroup.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.collectionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collection.keyName}" action="mdss.entomology.AbstractMosquitoCollectionController.view.mojo" name="mdss.entomology.AbstractMosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.identificationMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.identificationMethod.keyName}" action="mdss.entomology.IdentificationMethodController.view.mojo" name="mdss.entomology.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.quantityMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantity}
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.keyName}" action="mdss.entomology.SpecieController.view.mojo" name="mdss.entomology.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.entomology.MorphologicalSpecieGroupController.edit.mojo" name="mdss.entomology.MorphologicalSpecieGroup.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.entomology.MorphologicalSpecieGroupController.viewAll.mojo" name="mdss.entomology.MorphologicalSpecieGroup.viewAll.link" />
