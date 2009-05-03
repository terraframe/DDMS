<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.Targeter.form.name" id="dss.vector.solutions.irs.Targeter.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.TargeterController.update.mojo" name="dss.vector.solutions.irs.Targeter.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.TargeterController.delete.mojo" name="dss.vector.solutions.irs.Targeter.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.TargeterController.cancel.mojo" name="dss.vector.solutions.irs.Targeter.form.cancel.button" />
</mjl:form>
