<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.Mosquito.form.name" id="dss.vector.solutions.entomology.Mosquito.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="generation">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_Mosquito_generation}" param="generation">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="isofemale">
<mjl:boolean param="isofemale" />
</mjl:dt>
      <mjl:dt attribute="sex">
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
</mjl:dt>
      <mjl:dt attribute="testDate">
<mjl:input type="text" param="testDate" />
</mjl:dt>
      <mjl:dt attribute="collection">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="collectionId">
<mjl:input type="text" param="collectionId" />
</mjl:dt>
      <mjl:dt attribute="identificationMethod">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="specie">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_TrueSpecieEntity_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.MosquitoController.update.mojo" name="dss.vector.solutions.entomology.Mosquito.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.MosquitoController.delete.mojo" name="dss.vector.solutions.entomology.Mosquito.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.MosquitoController.cancel.mojo" name="dss.vector.solutions.entomology.Mosquito.form.cancel.button" />
</mjl:form>
