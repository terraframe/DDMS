<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultAssay.form.name" id="dss.vector.solutions.entomology.assay.AdultAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.ageRangeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <dl>
          <mjl:struct param="ageRange">
            <dt>
              <label>
                ${item.ageRange.endPointMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="endPoint" />
              <mjl:messages attribute="endPoint">
                <mjl:message />
              </mjl:messages>
            </dd>
            <dt>
              <label>
                ${item.ageRange.startPointMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="startPoint" />
              <mjl:messages attribute="startPoint">
                <mjl:message />
              </mjl:messages>
            </dd>
          </mjl:struct>
        </dl>
      </dd>
      <dt>
        <label>
          ${item.fedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fed" />
        <mjl:messages attribute="fed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.gravidMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="gravid" />
        <mjl:messages attribute="gravid">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sexMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${dss_vector_solutions_entomology_assay_AdultAssay_sex}" param="sex">
          <c:choose>
            <c:when test="${mjl:contains(item.sexEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.sexMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.sexMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.collectionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_collection}" param="collection">
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
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_generation}" param="generation">
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
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_identificationMethod}" param="identificationMethod">
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
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_testMethod}" param="testMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.insecticideMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AbstractAssay_insecticide}" param="insecticide">
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
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AbstractAssay_specie}" param="specie">
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
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.AdultAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.AdultAssay.form.create.button" />
</mjl:form>
