<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_GeoEntity" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.GeoEntity.form.name" id="dss.vector.solutions.geo.generated.GeoEntity.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.GeoEntityController.update.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.GeoEntityController.delete.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.GeoEntityController.cancel.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.cancel.button" />
  </mjl:form>
</dl>
