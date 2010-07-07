<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_InsecticideBrand" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.irs.InsecticideBrand.form.id" name="dss.vector.solutions.irs.InsecticideBrand.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command localize="false" name="dss.vector.solutions.irs.InsecticideBrand.form.update.button" value="Update" action="dss.vector.solutions.irs.InsecticideBrandController.update.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.irs.InsecticideBrand.form.delete.button" value="Delete" action="dss.vector.solutions.irs.InsecticideBrandController.delete.mojo" />
    <mjl:command localize="false" name="dss.vector.solutions.irs.InsecticideBrand.form.cancel.button" value="Cancel" action="dss.vector.solutions.irs.InsecticideBrandController.cancel.mojo" />
  </mjl:form>
</dl>
