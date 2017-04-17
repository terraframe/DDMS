<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_CategoryGen" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.CategoryGen.form.id" name="dss.vector.solutions.query.CategoryGen.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="categoryCount">
        ${item.categoryCount}
      </mjl:dt>
      <mjl:dt attribute="factoryType">
        ${item.factoryType}
      </mjl:dt>
      <mjl:dt attribute="layerId">
        ${item.layerId}
      </mjl:dt>
      <mjl:dt attribute="polygonStrokeEnd">
        ${item.polygonStrokeEnd}
      </mjl:dt>
      <mjl:dt attribute="polygonStrokeStart">
        ${item.polygonStrokeStart}
      </mjl:dt>
      <mjl:dt attribute="precisionFigures">
        ${item.precisionFigures}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.query.CategoryGen.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.query.CategoryGenController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.CategoryGen.viewAll.link" action="dss.vector.solutions.query.CategoryGenController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
