<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
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
  <mjl:command value="Cancel" action="dss.vector.solutions.entomology.QueryController.cancelQuery.mojo" name="dss.vector.solutions.entomology.QueryController.cancelQuery.button" />
  <mjl:command value="Create" action="dss.vector.solutions.entomology.QueryController.saveQuery.mojo" name="dss.vector.solutions.entomology.QueryController.saveQuery.button" />
</mjl:form>