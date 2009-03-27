<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">

  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
      <dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
    <dd><mjl:commandLink display="${item.geoEntity.geoId}" action="mdss.test.GeoEntityController.view.mojo" name="mdss.test.GeoEntity.form.view.link">
      <mjl:property value="${item.geoEntity.id}" name="id" />
    </mjl:commandLink></dd>
      <dt>
      <label>
        ${item.testDateMd.displayLabel}
      </label>
    </dt>
    <dd class="formatDate">
      ${item.testDate}
    </dd>
     <dt>
      <label>
        ${item.testMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testMethod.displayLabel}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo" name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.displayLabel}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.colonyNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.colonyName}
    </dd>
     <dt>
      <label>
        ${item.ageRangeMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
       <dt>
          <label>
            ${item.ageRange.startPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.startPoint}
        </dd>
        <dt>
          <label>
            ${item.ageRange.endPointMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.ageRange.endPoint}
        </dd>
      </dl>
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
        ${item.gravidMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.gravid}
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
        ${item.insecticideMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.insecticide.displayLabel}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="dss.vector.solutions.mo.ActiveIngredient.form.view.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
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
        ${item.surfacePostionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.surfacePostionEnumNames}">
          <li>
            ${item.surfacePostionMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.holdingTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.holdingTime}
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
        ${item.quantityLiveMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityLive}
    </dd>
    <dt>
      <label>
        ${item.quantityDeadMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityDead}
    </dd>
    <dt>
      <label>
        ${item.mortalityMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.mortality}
    </dd>  
  </dl>

  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.viewAll.link" />
