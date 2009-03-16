<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.name" id="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.ageRangeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_LarvaeDiscriminatingDoseAssay_ageRange}" param="ageRange">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.controlTestMortalityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="controlTestMortality" />
        <mjl:messages attribute="controlTestMortality">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.holdingTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="holdingTime" />
        <mjl:messages attribute="holdingTime">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.mortalityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="mortality" />
        <mjl:messages attribute="mortality">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.quantityDeadMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="quantityDead" />
        <mjl:messages attribute="quantityDead">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.quantityLiveMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="quantityLive" />
        <mjl:messages attribute="quantityLive">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.amountMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="amount" />
        <mjl:messages attribute="amount">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.collectionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.exposureTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="exposureTime" />
        <mjl:messages attribute="exposureTime">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.generationMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation}" param="generation">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.identificationMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.intervalTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="intervalTime" />
        <mjl:messages attribute="intervalTime">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.isofemaleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="isofemale" />
      </dd>
      <dt>
        <label>
          ${item.quantityTestedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="quantityTested" />
        <mjl:messages attribute="quantityTested">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.testMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod}" param="testMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.unitsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_units}" param="units">
          <c:choose>
            <c:when test="${mjl:contains(item.unitsEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.unitsMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.unitsMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.genericNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="genericName" />
        <mjl:messages attribute="genericName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.insecticideMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide}" param="insecticide">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.specieMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.testDateMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="testDate" />
        <mjl:messages attribute="testDate">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.update.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.delete.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssayController.cancel.mojo" name="dss.vector.solutions.entomology.assay.LarvaeDiscriminatingDoseAssay.form.cancel.button" />
</mjl:form>
