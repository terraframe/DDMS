<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_Styles" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.Styles.form.name" id="dss.vector.solutions.query.Styles.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.StylesController.update.mojo" name="dss.vector.solutions.query.Styles.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.StylesController.delete.mojo" name="dss.vector.solutions.query.Styles.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.StylesController.cancel.mojo" name="dss.vector.solutions.query.Styles.form.cancel.button" />
  </mjl:form>
</dl>
