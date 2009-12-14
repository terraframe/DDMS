<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Create_RangeCategory" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.RangeCategory.form.name" id="dss.vector.solutions.query.RangeCategory.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.AbstractCategoryController.saveCategory.mojo" name="dss.vector.solutions.query.AbstractCategoryController.form.saveCategory.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.RangeCategoryController.cancel.mojo" name="dss.vector.solutions.query.RangeCategoryController.form.cancel.button" />
  </mjl:form>
</dl>
