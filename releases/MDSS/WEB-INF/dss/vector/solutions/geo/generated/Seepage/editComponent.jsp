<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Seepage" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Seepage.form.name" id="dss.vector.solutions.geo.generated.Seepage.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.SeepageController.update.mojo" name="dss.vector.solutions.geo.generated.Seepage.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.SeepageController.delete.mojo" name="dss.vector.solutions.geo.generated.Seepage.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.SeepageController.cancel.mojo" name="dss.vector.solutions.geo.generated.Seepage.form.cancel.button" />
  </mjl:form>
</dl>
