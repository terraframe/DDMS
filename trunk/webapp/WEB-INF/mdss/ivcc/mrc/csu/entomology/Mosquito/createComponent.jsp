<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="mdss.ivcc.mrc.csu.entomology.Mosquito.form.name" id="mdss.ivcc.mrc.csu.entomology.Mosquito.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.generationMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_Mosquito_generation}" param="generation">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
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
          ${item.sexMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${mdss_ivcc_mrc_csu_entomology_Mosquito_sex}" param="sex">
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
          ${item.testDateMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="testDate" />
        <mjl:messages attribute="testDate">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.collectionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.collectionIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="collectionId" />
        <mjl:messages attribute="collectionId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.identificationMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_identificationMethod}" param="identificationMethod">
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
        <mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="mdss.ivcc.mrc.csu.entomology.MosquitoController.create.mojo" name="mdss.ivcc.mrc.csu.entomology.Mosquito.form.create.button" />
</mjl:form>
