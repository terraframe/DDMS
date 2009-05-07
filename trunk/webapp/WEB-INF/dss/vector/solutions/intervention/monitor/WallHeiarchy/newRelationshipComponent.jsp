<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.name" id="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Roof
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
        Roof
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
  <mjl:command value="New Instance" action="dss.vector.solutions.intervention.monitor.WallHeiarchyController.newInstance.mojo" name="dss.vector.solutions.intervention.monitor.WallHeiarchy.form.newInstance.button" />
</mjl:form>
