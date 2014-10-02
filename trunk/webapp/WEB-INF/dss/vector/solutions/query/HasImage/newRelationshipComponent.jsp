<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Select Has Image Participants"/>
<mjl:form id="dss.vector.solutions.query.HasImage.form.id" name="dss.vector.solutions.query.HasImage.form.name" method="POST">
  <dl>
    <dt>
      <label>
        Saved map
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
        MapImage
      </label>
    </dt>
    <dd>
      <mjl:select param="childId" items="${childList}" var="current" valueAttribute="id">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="dss.vector.solutions.query.HasImage.form.newInstance.button" value="New Instance" action="dss.vector.solutions.query.HasImageController.newInstance.mojo" />
  </dl>
</mjl:form>
