<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.util.Facade.form.name" id="dss.vector.solutions.util.Facade.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.util.FacadeController.update.mojo" name="dss.vector.solutions.util.Facade.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.util.FacadeController.delete.mojo" name="dss.vector.solutions.util.Facade.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.util.FacadeController.cancel.mojo" name="dss.vector.solutions.util.Facade.form.cancel.button" />
</mjl:form>
