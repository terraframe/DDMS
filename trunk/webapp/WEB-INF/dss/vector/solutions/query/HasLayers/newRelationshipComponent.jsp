<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.query.HasLayers.form.name" id="dss.vector.solutions.query.HasLayers.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Saved Map
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
        Layer
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${childList}" param="childId">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command value="New_Instance" action="dss.vector.solutions.query.HasLayersController.newInstance.mojo" name="dss.vector.solutions.query.HasLayers.form.newInstance.button" />
  </dl>
</mjl:form>
