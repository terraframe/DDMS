<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultAssay.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.ageRangeMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
        <dt>
          <label>
            ${item.ageRange.endPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.endPoint}
        </dd>
        <dt>
          <label>
            ${item.ageRange.startPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.startPoint}
        </dd>
      </dl>
    </dd>
    <dt>
      <label>
        ${item.fedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.fed}
    </dd>
    <dt>
      <label>
        ${item.gravidMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.gravid}
    </dd>
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
    <dt>
      <label>
        ${item.collectionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collection.keyName}" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.exposureTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.exposureTime}
    </dd>
    <dt>
      <label>
        ${item.generationMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.generation.keyName}" action="dss.vector.solutions.mo.GenerationController.view.mojo" name="dss.vector.solutions.mo.Generation.form.view.link">
        <mjl:property value="${item.generation.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.identificationMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.identificationMethod.keyName}" action="dss.vector.solutions.mo.IdentificationMethodController.view.mojo" name="dss.vector.solutions.mo.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.intervalTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.intervalTime}
    </dd>
    <dt>
      <label>
        ${item.isofemaleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.isofemale}
    </dd>
    <dt>
      <label>
        ${item.quantityTestedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityTested}
    </dd>
    <dt>
      <label>
        ${item.testMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testMethod.keyName}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo" name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.insecticideMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.insecticide.keyName}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="dss.vector.solutions.general.Insecticide.form.view.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.keyName}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.testDateMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.testDate}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.AdultAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.AdultAssay.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.AdultAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.AdultAssay.viewAll.link" />
