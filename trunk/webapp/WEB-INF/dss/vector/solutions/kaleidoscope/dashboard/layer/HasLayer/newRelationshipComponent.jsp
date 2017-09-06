<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Select Has Layer Participants"/>
<mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.id">
  <dl>
    <dt>
      <label>
        Map
      </label>
    </dt>
    <dd>
      <mjl:select valueAttribute="id" param="parentId" var="current" items="${parentList}">
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
      <mjl:select valueAttribute="id" param="childId" var="current" items="${childList}">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayer.form.newInstance.button" action="dss.vector.solutions.kaleidoscope.dashboard.layer.HasLayerController.newInstance.mojo" value="New Instance" />
  </dl>
</mjl:form>
