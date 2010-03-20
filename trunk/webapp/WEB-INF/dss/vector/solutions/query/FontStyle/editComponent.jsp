<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Edit_FontStyle" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.FontStyle.form.id" name="dss.vector.solutions.query.FontStyle.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.query.FontStyle.form.update.button" value="Update" action="dss.vector.solutions.query.FontStyleController.update.mojo" />
    <mjl:command name="dss.vector.solutions.query.FontStyle.form.delete.button" value="Delete" action="dss.vector.solutions.query.FontStyleController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.query.FontStyle.form.cancel.button" value="Cancel" action="dss.vector.solutions.query.FontStyleController.cancel.mojo" />
  </mjl:form>
</dl>
