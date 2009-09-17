<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_GeoUniversal" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.GeoUniversal.form.name" id="dss.vector.solutions.ontology.GeoUniversal.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.ontology.GeoUniversalController.update.mojo" name="dss.vector.solutions.ontology.GeoUniversal.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.ontology.GeoUniversalController.delete.mojo" name="dss.vector.solutions.ontology.GeoUniversal.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.GeoUniversalController.cancel.mojo" name="dss.vector.solutions.ontology.GeoUniversal.form.cancel.button" />
  </mjl:form>
</dl>
