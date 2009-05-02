<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.teamCodeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="teamCode" />
        <mjl:messages attribute="teamCode">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.SprayTeamController.update.mojo" name="dss.vector.solutions.irs.SprayTeam.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.SprayTeamController.delete.mojo" name="dss.vector.solutions.irs.SprayTeam.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.SprayTeamController.cancel.mojo" name="dss.vector.solutions.irs.SprayTeam.form.cancel.button" />
</mjl:form>
