<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="ivcc.mrc.csu.mdss.entomology.ConcreteMosquitoCollection.form.name" id="ivcc.mrc.csu.mdss.entomology.ConcreteMosquitoCollection.form.id" method="POST">
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
      <mjl:commandLink display="${item.geoEntity.keyName}" action="ivcc.mrc.csu.mdss.geo.GeoEntityController.view.mojo" name="ivcc.mrc.csu.mdss.geo.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="ivcc.mrc.csu.mdss.entomology.ConcreteMosquitoCollectionController.edit.mojo" name="ivcc.mrc.csu.mdss.entomology.ConcreteMosquitoCollection.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="ivcc.mrc.csu.mdss.entomology.ConcreteMosquitoCollectionController.viewAll.mojo" name="ivcc.mrc.csu.mdss.entomology.ConcreteMosquitoCollection.viewAll.link" />
