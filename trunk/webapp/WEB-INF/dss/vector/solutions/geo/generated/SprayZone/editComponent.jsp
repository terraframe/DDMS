<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Edit_SprayZone" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.geo.generated.SprayZone.form.id" name="dss.vector.solutions.geo.generated.SprayZone.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize var="Update_Localize" key="Update" />
    <mjl:command name="dss.vector.solutions.geo.generated.SprayZone.form.update.button" value="${Update_Localize}" action="dss.vector.solutions.geo.generated.SprayZoneController.update.mojo" />
    <mdss:localize var="Delete_Localize" key="Delete" />
    <mjl:command name="dss.vector.solutions.geo.generated.SprayZone.form.delete.button" value="${Delete_Localize}" action="dss.vector.solutions.geo.generated.SprayZoneController.delete.mojo" />
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="dss.vector.solutions.geo.generated.SprayZone.form.cancel.button" value="${Cancel_Localize}" action="dss.vector.solutions.geo.generated.SprayZoneController.cancel.mojo" />
  </mjl:form>
</dl>
