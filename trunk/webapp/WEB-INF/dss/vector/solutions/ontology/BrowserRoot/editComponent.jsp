<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_BrowserRoot" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.BrowserRoot.form.name" id="dss.vector.solutions.ontology.BrowserRoot.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.ontology.BrowserRootController.update.mojo" name="dss.vector.solutions.ontology.BrowserRoot.form.update.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.BrowserRootController.cancel.mojo" name="dss.vector.solutions.ontology.BrowserRoot.form.cancel.button" />
  </mjl:form>
</dl>
