<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.AbstractCategory.form.name" id="dss.vector.solutions.query.AbstractCategory.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.AbstractCategoryController.update.mojo" name="dss.vector.solutions.query.AbstractCategory.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.AbstractCategoryController.delete.mojo" name="dss.vector.solutions.query.AbstractCategory.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.AbstractCategoryController.cancel.mojo" name="dss.vector.solutions.query.AbstractCategory.form.cancel.button" />
  </mjl:form>
</dl>
