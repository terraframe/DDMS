<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.LocatedIn.form.name" id="dss.vector.solutions.geo.LocatedIn.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.geo.LocatedInController.update.mojo" name="dss.vector.solutions.geo.LocatedIn.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.geo.LocatedInController.delete.mojo" name="dss.vector.solutions.geo.LocatedIn.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.geo.LocatedInController.cancel.mojo" name="dss.vector.solutions.geo.LocatedIn.form.cancel.button" />
</mjl:form>
