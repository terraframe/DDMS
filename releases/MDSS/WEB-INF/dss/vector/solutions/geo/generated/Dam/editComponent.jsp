<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Dam" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Dam.form.name" id="dss.vector.solutions.geo.generated.Dam.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.DamController.update.mojo" name="dss.vector.solutions.geo.generated.Dam.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.DamController.delete.mojo" name="dss.vector.solutions.geo.generated.Dam.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.DamController.cancel.mojo" name="dss.vector.solutions.geo.generated.Dam.form.cancel.button" />
  </mjl:form>
</dl>
