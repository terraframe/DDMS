<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_ITN" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.ITN.form.name" id="dss.vector.solutions.geo.generated.ITN.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.ITNController.update.mojo" name="dss.vector.solutions.geo.generated.ITN.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.ITNController.delete.mojo" name="dss.vector.solutions.geo.generated.ITN.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.ITNController.cancel.mojo" name="dss.vector.solutions.geo.generated.ITN.form.cancel.button" />
  </mjl:form>
</dl>
