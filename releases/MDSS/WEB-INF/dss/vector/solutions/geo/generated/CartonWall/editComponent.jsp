<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_CartonWall" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.CartonWall.form.name" id="dss.vector.solutions.geo.generated.CartonWall.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.CartonWallController.update.mojo" name="dss.vector.solutions.geo.generated.CartonWall.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.CartonWallController.delete.mojo" name="dss.vector.solutions.geo.generated.CartonWall.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.CartonWallController.cancel.mojo" name="dss.vector.solutions.geo.generated.CartonWall.form.cancel.button" />
  </mjl:form>
</dl>
