<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" scope="request" value="Select Has categories Participants"/>
<mjl:form id="dss.vector.solutions.query.HasCategories.form.id" name="dss.vector.solutions.query.HasCategories.form.name" method="POST">
  <dl>
    <dt>
      <label>
        Layer
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
        Abstract category
      </label>
    </dt>
    <dd>
      <mjl:select param="childId" items="${childList}" var="current" valueAttribute="id">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command name="dss.vector.solutions.query.HasCategories.form.newInstance.button" value="New Instance" action="dss.vector.solutions.query.HasCategoriesController.newInstance.mojo" />
  </dl>
</mjl:form>
