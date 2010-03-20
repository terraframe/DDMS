<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.SavedSearch.form.name" id="dss.vector.solutions.query.SavedSearch.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command value="Update" action="dss.vector.solutions.query.SavedSearchController.update.mojo" name="dss.vector.solutions.query.SavedSearch.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.query.SavedSearchController.delete.mojo" name="dss.vector.solutions.query.SavedSearch.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.query.SavedSearchController.cancel.mojo" name="dss.vector.solutions.query.SavedSearch.form.cancel.button" />
  </mjl:form>
</dl>
