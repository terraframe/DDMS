<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroup.form.name" id="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroup.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.quanityMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quanity}
    </dd>
    <dt>
      <label>
        ${item.collectionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collection.keyName}" action="csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollectionController.view.mojo" name="csu.mrc.ivcc.mdss.entomology.AbstractMosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.collectionIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.collectionId}
    </dd>
    <dt>
      <label>
        ${item.identificationMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.identificationMethod.keyName}" action="csu.mrc.ivcc.mdss.entomology.IdentificationMethodController.view.mojo" name="csu.mrc.ivcc.mdss.entomology.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.keyName}" action="csu.mrc.ivcc.mdss.entomology.SpecieController.view.mojo" name="csu.mrc.ivcc.mdss.entomology.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupController.edit.mojo" name="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroup.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="Abstract Mosqito Collection" action="csu.mrc.ivcc.mdss.entomology.CollectionTrueSpecieController.childQuery.mojo" name="csu.mrc.ivcc.mdss.entomology.CollectionTrueSpecie.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroupController.viewAll.mojo" name="csu.mrc.ivcc.mdss.entomology.UninterestingSpecieGroup.viewAll.link" />
