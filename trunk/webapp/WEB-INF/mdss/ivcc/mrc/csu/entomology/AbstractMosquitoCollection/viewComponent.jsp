<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollection.form.name" id="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollection.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.dateCollectedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.dateCollected}
    </dd>
    <dt>
      <label>
        ${item.geoEntityMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoEntity.keyName}" action="mdss.ivcc.mrc.csu.geo.GeoEntityController.view.mojo" name="mdss.ivcc.mrc.csu.geo.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionController.edit.mojo" name="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollection.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="True Specie Collection" action="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecieController.parentQuery.mojo" name="mdss.ivcc.mrc.csu.entomology.CollectionTrueSpecie.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollectionController.viewAll.mojo" name="mdss.ivcc.mrc.csu.entomology.AbstractMosquitoCollection.viewAll.link" />
