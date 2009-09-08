<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_CoveredAdobe" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.CoveredAdobe.form.name" id="dss.vector.solutions.geo.generated.CoveredAdobe.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.CoveredAdobeController.update.mojo" name="dss.vector.solutions.geo.generated.CoveredAdobe.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.CoveredAdobeController.delete.mojo" name="dss.vector.solutions.geo.generated.CoveredAdobe.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.CoveredAdobeController.cancel.mojo" name="dss.vector.solutions.geo.generated.CoveredAdobe.form.cancel.button" />
  </mjl:form>
</dl>
