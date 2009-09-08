<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Puddle" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Puddle.form.name" id="dss.vector.solutions.geo.generated.Puddle.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.PuddleController.update.mojo" name="dss.vector.solutions.geo.generated.Puddle.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.PuddleController.delete.mojo" name="dss.vector.solutions.geo.generated.Puddle.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.PuddleController.cancel.mojo" name="dss.vector.solutions.geo.generated.Puddle.form.cancel.button" />
  </mjl:form>
</dl>
