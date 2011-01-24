<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.AllowedIn.form.name" id="dss.vector.solutions.geo.AllowedIn.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mdss:localize key="Update" var="Localized_Update" />
  <mjl:command value="${Localized_Update}" action="dss.vector.solutions.geo.AllowedInController.update.mojo" name="dss.vector.solutions.geo.AllowedIn.form.update.button" />
  <mdss:localize key="Delete" var="Localized_Delete" />
  <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.geo.AllowedInController.delete.mojo" name="dss.vector.solutions.geo.AllowedIn.form.delete.button" />
  <mdss:localize key="Cancel" var="Localized_Cancel" />
  <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.geo.AllowedInController.cancel.mojo" name="dss.vector.solutions.geo.AllowedIn.form.cancel.button" />
</mjl:form>
