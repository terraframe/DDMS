<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.name" id="dss.vector.solutions.entomology.assay.EfficacyAssay.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <mjl:component item="${item}" param="dto">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <dl>
      <dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
      <dd>${item.geoEntity.geoId}</dd>
      <dt><label> ${item.testDateMd.displayLabel} </label></dt>
      <dd class="formatDate">${item.testDate}</dd>
      <mjl:dt attribute="testMethod">
        <mjl:commandLink display="${item.testMethod.displayLabel}" action="dss.vector.solutions.mo.ResistanceMethodologyController.view.mojo"
          name="dss.vector.solutions.mo.ResistanceMethodology.form.view.link">
          <mjl:property value="${item.testMethod.id}" name="id" />
        </mjl:commandLink>
      </mjl:dt>
      <mjl:dt attribute="specie">
        <mjl:commandLink display="${item.specie.displayLabel}" action="dss.vector.solutions.mo.SpecieController.view.mojo" name="dss.vector.solutions.mo.Specie.form.view.link">
          <mjl:property value="${item.specie.id}" name="id" />
        </mjl:commandLink>
      </mjl:dt>
      <mjl:dt attribute="colonyName">
      ${item.colonyName}
</mjl:dt>
      <dt><label> ${item.ageRangeMd.displayLabel} </label></dt>
      <dd>
      <dl>
        <dt><label> ${item.ageRange.startPointMd.displayLabel} </label></dt>
        <dd>${item.ageRange.startPoint}</dd>
        <dt><label> ${item.ageRange.endPointMd.displayLabel} </label></dt>
        <dd>${item.ageRange.endPoint}</dd>
      </dl>
      </dd>
      <dt><label> ${item.sexMd.displayLabel} </label></dt>
      <dd>
      <ul>
        <c:forEach var="enumName" items="${item.sexEnumNames}">
          <li>${item.sexMd.enumItems[enumName]}</li>
        </c:forEach>
      </ul>
      </dd>
      <mjl:dt attribute="gravid">
      ${item.gravid}
</mjl:dt>
      <mjl:dt attribute="fed">
      ${item.fed}
</mjl:dt>
      <mjl:dt attribute="insecticide">
        <mjl:commandLink display="${item.insecticide.displayLabel}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="dss.vector.solutions.mo.ActiveIngredient.form.view.link">
          <mjl:property value="${item.insecticide.id}" name="id" />
        </mjl:commandLink>
      </mjl:dt>
      <mjl:dt attribute="timeOnSurface">
      ${item.timeOnSurface}
</mjl:dt>
      <dt><label> ${item.surfacePostionMd.displayLabel} </label></dt>
      <dd>
      <ul>
        <c:forEach var="enumName" items="${item.surfacePostionEnumNames}">
          <li>${item.surfacePostionMd.enumItems[enumName]}</li>
        </c:forEach>
      </ul>
      </dd>
            <mjl:dt attribute="exposureTime">
      ${item.exposureTime}
</mjl:dt>
      <mjl:dt attribute="holdingTime">
      ${item.holdingTime}
</mjl:dt>
      <mjl:dt attribute="quantityTested">
      ${item.quantityTested}
</mjl:dt>
      <mjl:dt attribute="quantityLive">
      ${item.quantityLive}
</mjl:dt>
      <mjl:dt attribute="quantityDead">
      ${item.quantityDead}
</mjl:dt>
      <mjl:dt attribute="mortality">
      ${item.mortality}
</mjl:dt>
      <dt><label> <fmt:message key="Overall_Mortality_for_surface"/>: ${item.geoEntity.entityName} </label></dt>
      <dd>${item.overallMortalityRate}</dd>
    </dl>
  </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.edit.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.form.edit.button" />
    <mjl:command value="Copy_Assay" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.cloneAssay.mojo" name="form.cloneAssay.button" />
</mjl:form>


<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.EfficacyAssayController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.EfficacyAssay.viewAll.link" />
