<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.MorphologicalSpecieGroup.form.name" id="dss.vector.solutions.entomology.MorphologicalSpecieGroup.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="collection">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_collection}" param="collection">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="identificationMethod">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_identificationMethod}" param="identificationMethod">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="quantity">
<mjl:input type="text" param="quantity" />
</mjl:dt>
      <mjl:dt attribute="specie">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_MorphologicalSpecieGroup_specie}" param="specie">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.MorphologicalSpecieGroupController.create.mojo" name="dss.vector.solutions.entomology.MorphologicalSpecieGroup.form.create.button" />
</mjl:form>
