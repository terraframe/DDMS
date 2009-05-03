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
    <mjl:dt attribute="fed">
      ${item.fed}
</mjl:dt>
    <mjl:dt attribute="gravid">
      ${item.gravid}
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
    <mjl:dt attribute="collection">
<mjl:commandLink display="${item.collection.keyName}" action="dss.vector.solutions.entomology.MosquitoCollectionController.view.mojo" name="dss.vector.solutions.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="exposureTime">
      ${item.exposureTime}
</mjl:dt>
    <mjl:dt attribute="generation">
<mjl:commandLink display="${item.generation.keyName}" action="dss.vector.solutions.mo.GenerationController.view.mojo" name="dss.vector.solutions.mo.Generation.form.view.link">
        <mjl:property value="${item.generation.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="identificationMethod">
<mjl:commandLink display="${item.identificationMethod.keyName}" action="dss.vector.solutions.mo.IdentificationMethodController.view.mojo" name="dss.vector.solutions.mo.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="intervalTime">
      ${item.intervalTime}
</mjl:dt>
    <mjl:dt attribute="isofemale">
      ${item.isofemale}
</mjl:dt>
    <mjl:dt attribute="quantityTested">
      ${item.quantityTested}
</mjl:dt>
    <mjl:dt attribute="testMethod">
<mjl:commandLink display="${item.testMethod.keyName}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo" name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="insecticide">
<mjl:commandLink display="${item.insecticide.keyName}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="dss.vector.solutions.general.Insecticide.form.view.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="specie">
<mjl:commandLink display="${item.specie.keyName}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
</mjl:dt>
    <mjl:dt attribute="testDate">
      ${item.testDate}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.AdultAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.AdultAssay.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.AdultAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.AdultAssay.viewAll.link" />
