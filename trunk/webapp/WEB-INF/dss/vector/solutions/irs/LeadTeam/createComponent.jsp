<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.LeadTeam.form.name" id="dss.vector.solutions.irs.LeadTeam.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.irs.LeadTeamController.create.mojo" name="dss.vector.solutions.irs.LeadTeam.form.create.button" />
</mjl:form>
