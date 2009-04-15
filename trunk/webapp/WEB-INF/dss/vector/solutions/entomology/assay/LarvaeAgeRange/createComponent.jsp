<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.name" id="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="endPoint">
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_endPoint}" param="endPoint">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="startPoint">
<mjl:select var="current" valueAttribute="id" items="${csu_mrc_ivcc_mdss_entomology_assay_LarvaeAgeRange_startPoint}" param="startPoint">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.LarvaeAgeRangeController.create.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAgeRange.form.create.button" />
</mjl:form>
