<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Select Has Style Participants"/>
<mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.name" id="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.id">
  <dl>
    <dt>
      <label>
        Layer
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
        Style
      </label>
    </dt>
    <dd>
      <mjl:select valueAttribute="id" param="childId" var="current" items="${childList}">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="dss.vector.solutions.kaleidoscope.dashboard.HasStyle.form.newInstance.button" action="dss.vector.solutions.kaleidoscope.dashboard.HasStyleController.newInstance.mojo" value="New Instance" />
  </dl>
</mjl:form>
