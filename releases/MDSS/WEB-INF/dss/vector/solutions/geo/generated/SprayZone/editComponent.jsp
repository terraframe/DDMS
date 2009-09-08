<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_SprayZone" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.SprayZone.form.name" id="dss.vector.solutions.geo.generated.SprayZone.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.SprayZoneController.update.mojo" name="dss.vector.solutions.geo.generated.SprayZone.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.SprayZoneController.delete.mojo" name="dss.vector.solutions.geo.generated.SprayZone.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.SprayZoneController.cancel.mojo" name="dss.vector.solutions.geo.generated.SprayZone.form.cancel.button" />
  </mjl:form>
</dl>
