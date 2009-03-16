<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.MosquitoCollectionPoint.form.name" id="dss.vector.solutions.entomology.MosquitoCollectionPoint.form.id" method="POST">
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
      <mjl:commandLink display="${item.geoEntity.keyName}" action="dss.vector.solutions.geo.GeoEntityController.view.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.MosquitoCollectionPointController.edit.mojo" name="dss.vector.solutions.entomology.MosquitoCollectionPoint.form.edit.button" />
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
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.MosquitoCollectionPointController.viewAll.mojo" name="dss.vector.solutions.entomology.MosquitoCollectionPoint.viewAll.link" />
