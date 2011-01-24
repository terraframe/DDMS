<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.PersistsSearch.form.name" id="dss.vector.solutions.query.PersistsSearch.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        MDSS User
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.MDSSUserController.view.mojo" name="dss.vector.solutions.MDSSUser.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Saved Query
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.query.SavedSearchController.view.mojo" name="dss.vector.solutions.query.SavedSearch.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.PersistsSearchController.edit.mojo" name="dss.vector.solutions.query.PersistsSearch.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.query.PersistsSearchController.viewAll.mojo" name="dss.vector.solutions.query.PersistsSearch.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
