<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.StyleRule.form.name" id="dss.vector.solutions.query.StyleRule.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.query.StyleRuleController.update.mojo" name="dss.vector.solutions.query.StyleRule.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.query.StyleRuleController.delete.mojo" name="dss.vector.solutions.query.StyleRule.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.query.StyleRuleController.cancel.mojo" name="dss.vector.solutions.query.StyleRule.form.cancel.button" />
</mjl:form>
