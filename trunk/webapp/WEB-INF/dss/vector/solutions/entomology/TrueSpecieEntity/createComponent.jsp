<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.TrueSpecieEntity.form.name" id="dss.vector.solutions.entomology.TrueSpecieEntity.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
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
  <mjl:command value="Create" action="dss.vector.solutions.entomology.TrueSpecieEntityController.create.mojo" name="dss.vector.solutions.entomology.TrueSpecieEntity.form.create.button" />
</mjl:form>
