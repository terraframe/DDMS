<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.Mosquito.form.name" id="dss.vector.solutions.entomology.Mosquito.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="generation">
<mjl:commandLink display="${item.generation.keyName}" action="dss.vector.solutions.entomology.GenerationController.view.mojo" name="dss.vector.solutions.entomology.Generation.form.view.link">
        <mjl:property value="${item.generation.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="isofemale">
      ${item.isofemale}
</mjl:dt>
    <dt>
      <label>
        ${item.sexMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.sexEnumNames}">
          <li>
            ${item.sexMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <mjl:dt attribute="testDate">
      ${item.testDate}
</mjl:dt>
    <mjl:dt attribute="collection">
<mjl:commandLink display="${item.collection.keyName}" action="dss.vector.solutions.entomology.AbstractMosquitoCollectionController.view.mojo" name="dss.vector.solutions.entomology.AbstractMosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="collectionId">
      ${item.collectionId}
</mjl:dt>
    <mjl:dt attribute="identificationMethod">
<mjl:commandLink display="${item.identificationMethod.keyName}" action="dss.vector.solutions.entomology.IdentificationMethodController.view.mojo" name="dss.vector.solutions.entomology.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="specie">
<mjl:commandLink display="${item.specie.keyName}" action="dss.vector.solutions.entomology.SpecieController.view.mojo" name="dss.vector.solutions.entomology.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.MosquitoController.edit.mojo" name="dss.vector.solutions.entomology.Mosquito.form.edit.button" />
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
        <mjl:commandLink display="Abstract Mosqito Collection" action="dss.vector.solutions.entomology.CollectionTrueSpecieController.childQuery.mojo" name="dss.vector.solutions.entomology.CollectionTrueSpecie.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.MosquitoController.viewAll.mojo" name="dss.vector.solutions.entomology.Mosquito.viewAll.link" />
