<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Roof" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Roof.form.name" id="dss.vector.solutions.geo.generated.Roof.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.RoofController.update.mojo" name="dss.vector.solutions.geo.generated.Roof.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.RoofController.delete.mojo" name="dss.vector.solutions.geo.generated.Roof.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.RoofController.cancel.mojo" name="dss.vector.solutions.geo.generated.Roof.form.cancel.button" />
  </mjl:form>
</dl>
