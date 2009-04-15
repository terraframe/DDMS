<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.name" id="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="assay">
<mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_entomology_assay_LarvaeTestInterval_assay}" param="assay">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
</mjl:dt>
      <mjl:dt attribute="period">
<mjl:input type="text" param="period" />
</mjl:dt>
      <mjl:dt attribute="quantityDead">
<mjl:input type="text" param="quantityDead" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.update.mojo" name="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.delete.mojo" name="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.cancel.mojo" name="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.cancel.button" />
</mjl:form>
