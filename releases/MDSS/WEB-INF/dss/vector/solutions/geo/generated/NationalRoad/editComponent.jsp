<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_NationalRoad" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.NationalRoad.form.name" id="dss.vector.solutions.geo.generated.NationalRoad.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.NationalRoadController.update.mojo" name="dss.vector.solutions.geo.generated.NationalRoad.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.NationalRoadController.delete.mojo" name="dss.vector.solutions.geo.generated.NationalRoad.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.NationalRoadController.cancel.mojo" name="dss.vector.solutions.geo.generated.NationalRoad.form.cancel.button" />
  </mjl:form>
</dl>
