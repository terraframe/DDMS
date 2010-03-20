<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_PopulationData" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.PopulationData.form.id" name="dss.vector.solutions.general.PopulationData.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.general.PopulationData.form.update.button" value="Update" action="dss.vector.solutions.general.PopulationDataController.update.mojo" />
    <mjl:command name="dss.vector.solutions.general.PopulationData.form.delete.button" value="Delete" action="dss.vector.solutions.general.PopulationDataController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.general.PopulationData.form.cancel.button" value="Cancel" action="dss.vector.solutions.general.PopulationDataController.cancel.mojo" />
  </mjl:form>
</dl>
