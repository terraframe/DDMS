<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.TeamSpray.form.name" id="dss.vector.solutions.irs.TeamSpray.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.sprayTeamMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.sprayTeam.keyName}" action="dss.vector.solutions.irs.TeamController.view.mojo" name="dss.vector.solutions.irs.Team.form.view.link">
        <mjl:property value="${item.sprayTeam.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.targetMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.target}
    </dd>
    <dt>
      <label>
        ${item.teamSprayWeekMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.teamSprayWeek}
    </dd>
    <dt>
      <label>
        ${item.sprayDataMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.sprayData.keyName}" action="dss.vector.solutions.irs.SprayDataController.view.mojo" name="dss.vector.solutions.irs.SprayData.form.view.link">
        <mjl:property value="${item.sprayData.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.TeamSprayController.edit.mojo" name="dss.vector.solutions.irs.TeamSpray.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.TeamSprayController.viewAll.mojo" name="dss.vector.solutions.irs.TeamSpray.viewAll.link" />
