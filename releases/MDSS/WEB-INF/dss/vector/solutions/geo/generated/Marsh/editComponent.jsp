<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Marsh" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Marsh.form.name" id="dss.vector.solutions.geo.generated.Marsh.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.MarshController.update.mojo" name="dss.vector.solutions.geo.generated.Marsh.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.MarshController.delete.mojo" name="dss.vector.solutions.geo.generated.Marsh.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.MarshController.cancel.mojo" name="dss.vector.solutions.geo.generated.Marsh.form.cancel.button" />
  </mjl:form>
</dl>
