<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.DDATestInterval.form.name" id="dss.vector.solutions.entomology.assay.DDATestInterval.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="assay">
<mjl:select var="current" valueAttribute="id" items="${mdss_ivcc_mrc_csu_entomology_assay_ADDATestInterval_assay}" param="assay">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="knockedDown">
<mjl:input type="text" param="knockedDown" />
</mjl:dt>
      <mjl:dt attribute="period">
<mjl:input type="text" param="period" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.DDATestIntervalController.update.mojo" name="dss.vector.solutions.entomology.assay.DDATestInterval.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.DDATestIntervalController.delete.mojo" name="dss.vector.solutions.entomology.assay.DDATestInterval.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.DDATestIntervalController.cancel.mojo" name="dss.vector.solutions.entomology.assay.DDATestInterval.form.cancel.button" />
</mjl:form>
