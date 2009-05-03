<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.form.name" id="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultController.edit.mojo" name="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.viewAll.link" />
