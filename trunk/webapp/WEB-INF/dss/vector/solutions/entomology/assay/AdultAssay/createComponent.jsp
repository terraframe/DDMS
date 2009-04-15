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
      <mjl:dt attribute="fed">
<mjl:input type="text" param="fed" />
</mjl:dt>
      <mjl:dt attribute="gravid">
<mjl:input type="text" param="gravid" />
</mjl:dt>
      <mjl:dt attribute="sex">
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
</mjl:dt>
      <mjl:dt attribute="collection">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="exposureTime">
<mjl:input type="text" param="exposureTime" />
</mjl:dt>
      <mjl:dt attribute="generation">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_generation}" param="generation">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="identificationMethod">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="intervalTime">
<mjl:input type="text" param="intervalTime" />
</mjl:dt>
      <mjl:dt attribute="isofemale">
<mjl:boolean param="isofemale" />
</mjl:dt>
      <mjl:dt attribute="quantityTested">
<mjl:input type="text" param="quantityTested" />
</mjl:dt>
      <mjl:dt attribute="testMethod">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_CollectionAssay_testMethod}" param="testMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="insecticide">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AbstractAssay_insecticide}" param="insecticide">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="specie">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_AbstractAssay_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="testDate">
<mjl:input type="text" param="testDate" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.AdultAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.AdultAssay.form.create.button" />
</mjl:form>
