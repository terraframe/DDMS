<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.AllowedIn.form.name" id="dss.vector.solutions.geo.AllowedIn.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.geo.AllowedInController.update.mojo" name="dss.vector.solutions.geo.AllowedIn.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.geo.AllowedInController.delete.mojo" name="dss.vector.solutions.geo.AllowedIn.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.geo.AllowedInController.cancel.mojo" name="dss.vector.solutions.geo.AllowedIn.form.cancel.button" />
</mjl:form>
