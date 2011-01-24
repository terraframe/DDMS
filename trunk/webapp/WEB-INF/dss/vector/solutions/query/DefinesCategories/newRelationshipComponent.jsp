<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:form name="dss.vector.solutions.query.DefinesCategories.form.name" id="dss.vector.solutions.query.DefinesCategories.form.id" method="POST">
  <dl>
    <dt>
      <label>
        Thematic Layer
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
  </dl>
  <mdss:localize key="New_Instance" var="Localized_New_Instance" />
  <mjl:command value="${Localized_New_Instance}" action="dss.vector.solutions.query.DefinesCategoriesController.newInstance.mojo" name="dss.vector.solutions.query.DefinesCategories.form.newInstance.button" />
</mjl:form>
