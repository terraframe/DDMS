<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_GeoUniversal" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.ontology.GeoUniversal.form.id" name="dss.vector.solutions.ontology.GeoUniversal.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.ontology.GeoUniversal.form.update.button" value="Update" action="dss.vector.solutions.ontology.GeoUniversalController.update.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.GeoUniversal.form.delete.button" value="Delete" action="dss.vector.solutions.ontology.GeoUniversalController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.ontology.GeoUniversal.form.cancel.button" value="Cancel" action="dss.vector.solutions.ontology.GeoUniversalController.cancel.mojo" />
  </mjl:form>
</dl>
