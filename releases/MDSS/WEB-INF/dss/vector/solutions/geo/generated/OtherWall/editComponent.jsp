<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_OtherWall" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.OtherWall.form.name" id="dss.vector.solutions.geo.generated.OtherWall.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.OtherWallController.update.mojo" name="dss.vector.solutions.geo.generated.OtherWall.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.OtherWallController.delete.mojo" name="dss.vector.solutions.geo.generated.OtherWall.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.OtherWallController.cancel.mojo" name="dss.vector.solutions.geo.generated.OtherWall.form.cancel.button" />
  </mjl:form>
</dl>
