<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.form.name" id="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResultController.create.mojo" name="dss.vector.solutions.entomology.assay.infectivity.InfectivityAssayTestResult.form.create.button" />
</mjl:form>
