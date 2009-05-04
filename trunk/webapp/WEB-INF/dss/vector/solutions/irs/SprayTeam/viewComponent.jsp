<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.teamCodeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.teamCode}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.SprayTeamController.edit.mojo" name="dss.vector.solutions.irs.SprayTeam.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="Leader" action="dss.vector.solutions.irs.LeadTeamController.parentQuery.mojo" name="dss.vector.solutions.irs.LeadTeam.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
      <li>
        <mjl:commandLink display="Operators" action="dss.vector.solutions.irs.InTeamController.parentQuery.mojo" name="dss.vector.solutions.irs.InTeam.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.SprayTeamController.viewAll.mojo" name="dss.vector.solutions.irs.SprayTeam.viewAll.link" />
