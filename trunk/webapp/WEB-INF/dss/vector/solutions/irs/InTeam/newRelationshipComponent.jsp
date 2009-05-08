<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.irs.InTeam.form.name" id="dss.vector.solutions.irs.InTeam.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Spray Operator
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${parentList}" param="parentId">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label>
        MDSS User
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${childList}" param="childId">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
  </dl>
  <mjl:command value="New_Instance" action="dss.vector.solutions.irs.InTeamController.newInstance.mojo" name="dss.vector.solutions.irs.InTeam.form.newInstance.button" />
</mjl:form>
