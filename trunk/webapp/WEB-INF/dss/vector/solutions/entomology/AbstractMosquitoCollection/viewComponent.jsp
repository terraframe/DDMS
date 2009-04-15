<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.AbstractMosquitoCollection.form.name" id="dss.vector.solutions.entomology.AbstractMosquitoCollection.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="dateCollected">
      ${item.dateCollected}
</mjl:dt>
    <mjl:dt attribute="geoEntity">
<mjl:commandLink display="${item.geoEntity.keyName}" action="dss.vector.solutions.geo.GeoEntityController.view.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.AbstractMosquitoCollectionController.edit.mojo" name="dss.vector.solutions.entomology.AbstractMosquitoCollection.form.edit.button" />
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
        <mjl:commandLink display="True Specie Collection" action="dss.vector.solutions.entomology.CollectionTrueSpecieController.parentQuery.mojo" name="dss.vector.solutions.entomology.CollectionTrueSpecie.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.AbstractMosquitoCollectionController.viewAll.mojo" name="dss.vector.solutions.entomology.AbstractMosquitoCollection.viewAll.link" />
