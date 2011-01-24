<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<mjl:form name="dss.vector.solutions.query.SavedSearch.name" id="dss.vector.solutions.query.SavedSearch.id" method="POST">
  <mjl:component item="${savedSearch}" param="savedQueryView">
    <dl>
      <dt>
        <label>
          ${savedSearch.queryNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="queryName" />
        <mjl:messages attribute="queryName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mdss:localize key="Cancel" var="Localized_Cancel" />
  <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.query.QueryController.cancelQuery.mojo" name="dss.vector.solutions.query.QueryController.cancelQuery.button" />
  <mdss:localize key="Create" var="Localized_Create" />
  <mjl:command value="${Localized_Create}" action="dss.vector.solutions.query.QueryController.saveQuery.mojo" name="dss.vector.solutions.query.QueryController.saveQuery.button" />
</mjl:form>