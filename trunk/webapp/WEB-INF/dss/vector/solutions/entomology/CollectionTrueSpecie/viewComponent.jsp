<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.CollectionTrueSpecie.form.name" id="dss.vector.solutions.entomology.CollectionTrueSpecie.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Abstract Mosquito Collection
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.entomology.AbstractMosquitoCollectionController.view.mojo" name="dss.vector.solutions.entomology.AbstractMosquitoCollection.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        True Specie Collection
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.entomology.TrueSpecieEntityController.view.mojo" name="dss.vector.solutions.entomology.TrueSpecieEntity.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.CollectionTrueSpecieController.edit.mojo" name="dss.vector.solutions.entomology.CollectionTrueSpecie.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.CollectionTrueSpecieController.viewAll.mojo" name="dss.vector.solutions.entomology.CollectionTrueSpecie.viewAll.link" />
