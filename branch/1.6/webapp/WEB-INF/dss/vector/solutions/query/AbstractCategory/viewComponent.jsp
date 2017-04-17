<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_AbstractCategory" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.AbstractCategory.form.id" name="dss.vector.solutions.query.AbstractCategory.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="styles">
        ${item.styles.keyName}
      </mjl:dt>
      <mjl:dt attribute="thematicColor">
        ${item.thematicColor}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.AbstractCategory.form.edit.button" value="Edit" action="dss.vector.solutions.query.AbstractCategoryController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.AbstractCategory.viewAll.link" action="dss.vector.solutions.query.AbstractCategoryController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
