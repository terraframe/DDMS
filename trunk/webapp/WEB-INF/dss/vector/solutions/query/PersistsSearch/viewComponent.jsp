<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_PersistsSearch" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.PersistsSearch.form.id" name="dss.vector.solutions.query.PersistsSearch.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          System user
        </label>
      </dt>
      <dd>
        ${item.parent.keyName}
      </dd>
      <dt>
        <label>
          Saved query
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="dss.vector.solutions.query.SavedSearch.form.view.link" action="dss.vector.solutions.query.SavedSearchController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.PersistsSearch.form.edit.button" value="Edit" action="dss.vector.solutions.query.PersistsSearchController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.PersistsSearch.viewAll.link" action="dss.vector.solutions.query.PersistsSearchController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
