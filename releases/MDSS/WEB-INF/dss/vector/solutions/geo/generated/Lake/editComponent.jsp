<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Lake" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Lake.form.name" id="dss.vector.solutions.geo.generated.Lake.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.LakeController.update.mojo" name="dss.vector.solutions.geo.generated.Lake.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.LakeController.delete.mojo" name="dss.vector.solutions.geo.generated.Lake.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.LakeController.cancel.mojo" name="dss.vector.solutions.geo.generated.Lake.form.cancel.button" />
  </mjl:form>
</dl>
