<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_SavedSearch" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.SavedSearch.form.id" name="dss.vector.solutions.query.SavedSearch.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="config">
        ${item.config}
      </mjl:dt>
      <mjl:dt attribute="csvFile">
        ${item.csvFile}
      </mjl:dt>
      <mjl:dt attribute="disease">
        ${item.disease.keyName}
      </mjl:dt>
      <mjl:dt attribute="mappable">
        ${item.mappable ? item.mappableMd.positiveDisplayLabel : item.mappableMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="queryName">
        ${item.queryName}
      </mjl:dt>
      <mjl:dt attribute="queryType">
        ${item.queryType}
      </mjl:dt>
      <mjl:dt attribute="queryXml">
        ${item.queryXml}
      </mjl:dt>
      <mjl:dt attribute="templateFile">
        ${item.templateFile}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.SavedSearch.form.edit.button" value="Edit" action="dss.vector.solutions.query.SavedSearchController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.SavedSearch.viewAll.link" action="dss.vector.solutions.query.SavedSearchController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
