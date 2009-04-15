<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.AdultAgeRange.form.name" id="dss.vector.solutions.entomology.assay.AdultAgeRange.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:dt attribute="endPoint">
      ${item.endPoint}
</mjl:dt>
    <mjl:dt attribute="startPoint">
      ${item.startPoint}
</mjl:dt>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.AdultAgeRangeController.edit.mojo" name="dss.vector.solutions.entomology.assay.AdultAgeRange.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.AdultAgeRangeController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.AdultAgeRange.viewAll.link" />
