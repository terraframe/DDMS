<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.NonRangeCategory.form.name" id="dss.vector.solutions.query.NonRangeCategory.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.exactValueMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.exactValue}
    </dd>
  </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.NonRangeCategoryController.edit.mojo" name="dss.vector.solutions.query.NonRangeCategory.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink action="dss.vector.solutions.query.DefinesCategoriesController.childQuery.mojo" name="dss.vector.solutions.query.DefinesCategories.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.NonRangeCategoryController.viewAll.mojo" name="dss.vector.solutions.query.NonRangeCategory.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
