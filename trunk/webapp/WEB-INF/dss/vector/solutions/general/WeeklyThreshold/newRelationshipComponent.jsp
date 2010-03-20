<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form id="dss.vector.solutions.general.WeeklyThreshold.form.id" name="dss.vector.solutions.general.WeeklyThreshold.form.name" method="POST">
  <dl>
    <dt>
      <label>
        Population Data
      </label>
    </dt>
    <dd>
      <mjl:select param="parentId" items="${parentList}" var="current" valueAttribute="id">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label>
        Epi Week
      </label>
    </dt>
    <dd>
      <mjl:select param="childId" items="${childList}" var="current" valueAttribute="id">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="dss.vector.solutions.general.WeeklyThreshold.form.newInstance.button" value="New_Instance" action="dss.vector.solutions.general.WeeklyThresholdController.newInstance.mojo" />
  </dl>
</mjl:form>
