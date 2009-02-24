<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssay.form.name" id="mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssay.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.collectionMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.collection.keyName}" action="mdss.ivcc.mrc.csu.entomology.MosquitoCollectionController.view.mojo" name="mdss.ivcc.mrc.csu.entomology.MosquitoCollection.form.view.link">
        <mjl:property value="${item.collection.id}" name="id" />
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
    <dt>
      <label>
        ${item.controlTestMortalityMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.controlTestMortality}
    </dd>
    <dt>
      <label>
        ${item.testMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.testMethod.displayLabel}" action="mdss.ivcc.mrc.csu.mo.ResistanceMethodologyController.view.mojo" name="mdss.ivcc.mrc.csu.mo.ResistanceMethodology.form.view.link">
        <mjl:property value="${item.testMethod.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.generationMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.generation.displayLabel}" action="mdss.ivcc.mrc.csu.mo.GenerationController.view.mojo" name="mdss.ivcc.mrc.csu.mo.Generation.form.view.link">
        <mjl:property value="${item.generation.id}" name="id" />
      </mjl:commandLink>
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
        ${item.sexMd.displayLabel}
      </label>
    </dt>
    <dd>
        <c:forEach var="enumName" items="${item.sexEnumNames}">     
            ${item.sexMd.enumItems[enumName]}    
        </c:forEach>
    </dd>
    <dt>
      <label>
        ${item.specieMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.specie.displayLabel}" action="mdss.ivcc.mrc.csu.mo.SpecieController.view.mojo" name="mdss.ivcc.mrc.csu.mo.Specie.form.view.link">
        <mjl:property value="${item.specie.id}" name="id" />
      </mjl:commandLink>
    </dd>
     <dt>
      <label>
        ${item.identificationMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.identificationMethod.displayLabel}" action="mdss.ivcc.mrc.csu.mo.IdentificationMethodController.view.mojo" name="mdss.ivcc.mrc.csu.mo.IdentificationMethod.form.view.link">
        <mjl:property value="${item.identificationMethod.id}" name="id" />
      </mjl:commandLink>
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
        ${item.exposureTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.exposureTime}
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
        ${item.insecticideMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.insecticide.displayLabel}" action="mdss.ivcc.mrc.csu.mo.InsecticideController.view.mojo" name="mdss.ivcc.mrc.csu.mo.Insecticide.form.view.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.genericNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.genericName}
    </dd>
    <dt>
      <label>
        ${item.amountMd.displayLabel}
      </label>
        
    </dt>
    <dd>
      ${item.amount} 
      <c:forEach var="enumName" items="${item.unitsEnumNames}">
            ${item.unitsMd.enumItems[enumName]}
        </c:forEach>
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
        ${item.quantityDeadMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityDead}
    </dd>
    
      <dt>
      <label>
        ${item.intervalTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.intervalTime}
    </dd>
  </dl>
  <mjl:command value="Edit" action="mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayController.edit.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssay.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssayController.viewAll.mojo" name="mdss.ivcc.mrc.csu.entomology.assay.AdultDiscriminatingDoseAssay.viewAll.link" />
