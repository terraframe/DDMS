<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.CollectionAssay.form.name" id="dss.vector.solutions.entomology.assay.CollectionAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="amount">
<mjl:input type="text" param="amount" />
</mjl:dt>
      <mjl:dt attribute="collection">
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="exposureTime">
<mjl:input type="text" param="exposureTime" />
</mjl:dt>
      <mjl:dt attribute="generation">
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_generation}" param="generation">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="identificationMethod">
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_identificationMethod}" param="identificationMethod">
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
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_CollectionAssay_testMethod}" param="testMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="units">
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
</mjl:dt>
      <mjl:dt attribute="genericName">
<mjl:input type="text" param="genericName" />
</mjl:dt>
      <mjl:dt attribute="insecticide">
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_insecticide}" param="insecticide">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="specie">
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_AbstractAssay_specie}" param="specie">
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
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.CollectionAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.CollectionAssay.form.create.button" />
</mjl:form>
