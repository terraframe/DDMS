<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.query.DefinesLayers.form.name" id="dss.vector.solutions.query.DefinesLayers.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Saved Query
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
        Universal Layer
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
  <mdss:localize key="New_Instance" var="Localized_New_Instance" />
  <mjl:command value="${Localized_New_Instance}" action="dss.vector.solutions.query.DefinesLayersController.newInstance.mojo" name="dss.vector.solutions.query.DefinesLayers.form.newInstance.button" />
</mjl:form>
