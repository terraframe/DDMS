<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_ReedOrBambooRoof" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.ReedOrBambooRoof.form.name" id="dss.vector.solutions.geo.generated.ReedOrBambooRoof.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.ReedOrBambooRoofController.update.mojo" name="dss.vector.solutions.geo.generated.ReedOrBambooRoof.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.ReedOrBambooRoofController.delete.mojo" name="dss.vector.solutions.geo.generated.ReedOrBambooRoof.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.ReedOrBambooRoofController.cancel.mojo" name="dss.vector.solutions.geo.generated.ReedOrBambooRoof.form.cancel.button" />
  </mjl:form>
</dl>
