<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_BrickWall" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.BrickWall.form.name" id="dss.vector.solutions.geo.generated.BrickWall.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.BrickWallController.update.mojo" name="dss.vector.solutions.geo.generated.BrickWall.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.BrickWallController.delete.mojo" name="dss.vector.solutions.geo.generated.BrickWall.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.BrickWallController.cancel.mojo" name="dss.vector.solutions.geo.generated.BrickWall.form.cancel.button" />
  </mjl:form>
</dl>
