<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.TrueSpecieEntity.form.name" id="ivcc.mrc.csu.mdss.entomology.TrueSpecieEntity.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.collectionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collection.keyName}" action="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollectionController.view.mojo" name="ivcc.mrc.csu.mdss.entomology.AbstractMosquitoCollection.form.view.link">
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
      <mjl:commandLink display="${item.identificationMethod.keyName}" action="ivcc.mrc.csu.mdss.entomology.IdentificationMethodController.view.mojo" name="ivcc.mrc.csu.mdss.entomology.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.keyName}" action="ivcc.mrc.csu.mdss.entomology.SpecieController.view.mojo" name="ivcc.mrc.csu.mdss.entomology.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.entomology.TrueSpecieEntityController.edit.mojo" name="ivcc.mrc.csu.mdss.entomology.TrueSpecieEntity.form.edit.button" />
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
        <mjl:commandLink display="Abstract Mosqito Collection" action="ivcc.mrc.csu.mdss.entomology.CollectionTrueSpecieController.childQuery.mojo" name="ivcc.mrc.csu.mdss.entomology.CollectionTrueSpecie.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.entomology.TrueSpecieEntityController.viewAll.mojo" name="ivcc.mrc.csu.mdss.entomology.TrueSpecieEntity.viewAll.link" />
