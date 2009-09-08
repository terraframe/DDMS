<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_RuralHealthCentre1" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.RuralHealthCentre1.form.name" id="dss.vector.solutions.geo.generated.RuralHealthCentre1.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.RuralHealthCentre1Controller.update.mojo" name="dss.vector.solutions.geo.generated.RuralHealthCentre1.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.RuralHealthCentre1Controller.delete.mojo" name="dss.vector.solutions.geo.generated.RuralHealthCentre1.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.RuralHealthCentre1Controller.cancel.mojo" name="dss.vector.solutions.geo.generated.RuralHealthCentre1.form.cancel.button" />
  </mjl:form>
</dl>
