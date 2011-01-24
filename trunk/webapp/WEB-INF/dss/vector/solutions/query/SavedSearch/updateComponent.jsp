<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.query.SavedSearch.form.name" id="dss.vector.solutions.query.SavedSearch.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Update" var="Localized_Update" />
    <mjl:command value="${Localized_Update}" action="dss.vector.solutions.query.SavedSearchController.update.mojo" name="dss.vector.solutions.query.SavedSearch.form.update.button" />
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.query.SavedSearchController.delete.mojo" name="dss.vector.solutions.query.SavedSearch.form.delete.button" />
    <mdss:localize key="Cancel" var="Localized_Cancel" />
    <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.query.SavedSearchController.cancel.mojo" name="dss.vector.solutions.query.SavedSearch.form.cancel.button" />
  </mjl:form>
</dl>
