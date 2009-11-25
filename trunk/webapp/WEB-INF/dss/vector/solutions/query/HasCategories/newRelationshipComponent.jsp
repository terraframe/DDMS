<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.query.HasCategories.form.name" id="dss.vector.solutions.query.HasCategories.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Layer
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
        Abstract Category
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${childList}" param="childId">
        <mjl:option>
          ${current.keyName}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command value="New_Instance" action="dss.vector.solutions.query.HasCategoriesController.newInstance.mojo" name="dss.vector.solutions.query.HasCategories.form.newInstance.button" />
  </dl>
</mjl:form>
