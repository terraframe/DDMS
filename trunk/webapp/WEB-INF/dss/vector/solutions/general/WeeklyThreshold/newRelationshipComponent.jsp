<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.general.WeeklyThreshold.form.name" id="dss.vector.solutions.general.WeeklyThreshold.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Population Data
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
        Epi Week
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${childList}" param="childId">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command value="New Instance" action="dss.vector.solutions.general.WeeklyThresholdController.newInstance.mojo" name="dss.vector.solutions.general.WeeklyThreshold.form.newInstance.button" />
  </dl>
</mjl:form>
