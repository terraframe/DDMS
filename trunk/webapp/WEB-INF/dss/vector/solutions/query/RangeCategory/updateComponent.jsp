<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.RangeCategory.form.name" id="dss.vector.solutions.query.RangeCategory.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.RangeCategoryController.update.mojo" name="dss.vector.solutions.query.RangeCategory.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.RangeCategoryController.delete.mojo" name="dss.vector.solutions.query.RangeCategory.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.RangeCategoryController.cancel.mojo" name="dss.vector.solutions.query.RangeCategory.form.cancel.button" />
  </mjl:form>
</dl>
