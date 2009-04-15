<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultAgeRange.form.name" id="dss.vector.solutions.entomology.assay.AdultAgeRange.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="endPoint">
<mjl:input type="text" param="endPoint" />
</mjl:dt>
      <mjl:dt attribute="startPoint">
<mjl:input type="text" param="startPoint" />
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.entomology.assay.AdultAgeRangeController.update.mojo" name="dss.vector.solutions.entomology.assay.AdultAgeRange.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.entomology.assay.AdultAgeRangeController.delete.mojo" name="dss.vector.solutions.entomology.assay.AdultAgeRange.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.assay.AdultAgeRangeController.cancel.mojo" name="dss.vector.solutions.entomology.assay.AdultAgeRange.form.cancel.button" />
</mjl:form>
