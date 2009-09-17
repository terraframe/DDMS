<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_BrowserRoot" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.BrowserRoot.form.name" id="dss.vector.solutions.ontology.BrowserRoot.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Create" action="dss.vector.solutions.ontology.BrowserRootController.create.mojo" name="dss.vector.solutions.ontology.BrowserRoot.form.create.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.ontology.BrowserRootController.cancel.mojo" name="dss.vector.solutions.ontology.BrowserRoot.form.cancel.button" />
  </mjl:form>
</dl>
