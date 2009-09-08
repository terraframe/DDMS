<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_PopulatedArea" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.PopulatedArea.form.name" id="dss.vector.solutions.geo.generated.PopulatedArea.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.PopulatedAreaController.update.mojo" name="dss.vector.solutions.geo.generated.PopulatedArea.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.PopulatedAreaController.delete.mojo" name="dss.vector.solutions.geo.generated.PopulatedArea.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.PopulatedAreaController.cancel.mojo" name="dss.vector.solutions.geo.generated.PopulatedArea.form.cancel.button" />
  </mjl:form>
</dl>
