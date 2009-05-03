<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.name" id="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.id" method="POST">
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
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.ConcreteMosquitoCollectionController.edit.mojo" name="dss.vector.solutions.entomology.ConcreteMosquitoCollection.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.ConcreteMosquitoCollectionController.viewAll.mojo" name="dss.vector.solutions.entomology.ConcreteMosquitoCollection.viewAll.link" />
