<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.DefinesCategories.form.name" id="dss.vector.solutions.query.DefinesCategories.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Thematic Layer
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.query.ThematicLayerController.view.mojo" name="dss.vector.solutions.query.ThematicLayer.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Abstract Category
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.query.AbstractCategoryController.view.mojo" name="dss.vector.solutions.query.AbstractCategory.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.query.DefinesCategoriesController.edit.mojo" name="dss.vector.solutions.query.DefinesCategories.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.query.DefinesCategoriesController.viewAll.mojo" name="dss.vector.solutions.query.DefinesCategories.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
