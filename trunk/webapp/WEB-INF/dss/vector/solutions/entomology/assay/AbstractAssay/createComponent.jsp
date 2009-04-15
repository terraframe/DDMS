<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AbstractAssay.form.name" id="dss.vector.solutions.entomology.assay.AbstractAssay.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="collection">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="identificationMethod">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="isofemale">
<mjl:boolean param="isofemale" />
</mjl:dt>
      <mjl:dt attribute="sex">
<mjl:select var="current" valueAttribute="enumName" items="${mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_sex}" param="sex">
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
      <mjl:dt attribute="specie">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="testDate">
<mjl:input type="text" param="testDate" />
</mjl:dt>
      <mjl:dt attribute="testMethod">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_AbstractAssay_testMethod}" param="testMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.AbstractAssayController.create.mojo" name="dss.vector.solutions.entomology.assay.AbstractAssay.form.create.button" />
</mjl:form>
