<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.intervention.monitor.HouseholdNet.form.name" id="dss.vector.solutions.intervention.monitor.HouseholdNet.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Household
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
        Net
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
  <mjl:command value="New Instance" action="dss.vector.solutions.intervention.monitor.HouseholdNetController.newInstance.mojo" name="dss.vector.solutions.intervention.monitor.HouseholdNet.form.newInstance.button" />
</mjl:form>
