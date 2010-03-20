<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Edit_HasCategories" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.HasCategories.form.name" id="dss.vector.solutions.query.HasCategories.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.HasCategoriesController.update.mojo" name="dss.vector.solutions.query.HasCategories.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.HasCategoriesController.delete.mojo" name="dss.vector.solutions.query.HasCategories.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.HasCategoriesController.cancel.mojo" name="dss.vector.solutions.query.HasCategories.form.cancel.button" />
  </mjl:form>
</dl>
