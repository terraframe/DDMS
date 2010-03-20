<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Default_Geo_Root" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.DefaultGeoEntity.form.id" name="dss.vector.solutions.DefaultGeoEntity.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.DefaultGeoEntity.form.update.button" value="Update" action="dss.vector.solutions.DefaultGeoEntityController.update.mojo" />
    <mjl:command name="dss.vector.solutions.DefaultGeoEntity.form.cancel.button" value="Cancel" action="dss.vector.solutions.DefaultGeoEntityController.cancel.mojo" />
  </mjl:form>
</dl>
