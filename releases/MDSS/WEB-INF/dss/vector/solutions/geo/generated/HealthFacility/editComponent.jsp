<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_HealthFacility" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.HealthFacility.form.name" id="dss.vector.solutions.geo.generated.HealthFacility.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.HealthFacilityController.update.mojo" name="dss.vector.solutions.geo.generated.HealthFacility.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.HealthFacilityController.delete.mojo" name="dss.vector.solutions.geo.generated.HealthFacility.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.HealthFacilityController.cancel.mojo" name="dss.vector.solutions.geo.generated.HealthFacility.form.cancel.button" />
  </mjl:form>
</dl>
