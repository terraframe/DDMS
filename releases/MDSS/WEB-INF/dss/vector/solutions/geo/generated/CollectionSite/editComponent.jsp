<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_CollectionSite" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.CollectionSite.form.name" id="dss.vector.solutions.geo.generated.CollectionSite.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.CollectionSiteController.update.mojo" name="dss.vector.solutions.geo.generated.CollectionSite.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.CollectionSiteController.delete.mojo" name="dss.vector.solutions.geo.generated.CollectionSite.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.CollectionSiteController.cancel.mojo" name="dss.vector.solutions.geo.generated.CollectionSite.form.cancel.button" />
  </mjl:form>
</dl>
