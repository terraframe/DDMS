<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_SticksAndMud" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.SticksAndMud.form.name" id="dss.vector.solutions.geo.generated.SticksAndMud.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.SticksAndMudController.update.mojo" name="dss.vector.solutions.geo.generated.SticksAndMud.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.SticksAndMudController.delete.mojo" name="dss.vector.solutions.geo.generated.SticksAndMud.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.SticksAndMudController.cancel.mojo" name="dss.vector.solutions.geo.generated.SticksAndMud.form.cancel.button" />
  </mjl:form>
</dl>
