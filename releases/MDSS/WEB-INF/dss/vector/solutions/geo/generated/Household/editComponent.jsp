<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Household" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.geo.generated.Household.form.name" id="dss.vector.solutions.geo.generated.Household.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.geo.generated.HouseholdController.update.mojo" name="dss.vector.solutions.geo.generated.Household.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.geo.generated.HouseholdController.delete.mojo" name="dss.vector.solutions.geo.generated.Household.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.generated.HouseholdController.cancel.mojo" name="dss.vector.solutions.geo.generated.Household.form.cancel.button" />
  </mjl:form>
</dl>
