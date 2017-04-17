<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_HasCategories" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.HasCategories.form.id" name="dss.vector.solutions.query.HasCategories.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          Layer
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.query.Layer.form.view.link" action="dss.vector.solutions.query.LayerController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Abstract category
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.query.AbstractCategory.form.view.link" action="dss.vector.solutions.query.AbstractCategoryController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.HasCategories.form.edit.button" value="Edit" action="dss.vector.solutions.query.HasCategoriesController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.HasCategories.viewAll.link" action="dss.vector.solutions.query.HasCategoriesController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
