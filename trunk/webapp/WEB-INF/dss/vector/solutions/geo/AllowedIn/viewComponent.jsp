<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.AllowedIn.form.name" id="dss.vector.solutions.geo.AllowedIn.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Geo Hierarchy
      </label>
    </dt>
    <dd>
      <mjl:commandLink action="dss.vector.solutions.geo.GeoHierarchyController.view.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Geo Hierarchy
      </label>
    </dt>
    <dd>
      <mjl:commandLink action="dss.vector.solutions.geo.GeoHierarchyController.view.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.geo.AllowedInController.edit.mojo" name="dss.vector.solutions.geo.AllowedIn.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink action="dss.vector.solutions.geo.AllowedInController.viewAll.mojo" name="dss.vector.solutions.geo.AllowedIn.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
