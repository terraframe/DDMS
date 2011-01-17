<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.GeoHierarchy.form.name" id="dss.vector.solutions.geo.GeoHierarchy.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.geoEntityClassMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoEntityClass.keyName}" action="com.runwaysdk.system.metadata.MdBusinessController.view.mojo" name="com.runwaysdk.system.metadata.MdBusiness.form.view.link">
        <mjl:property value="${item.geoEntityClass.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.politicalMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.political}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.geo.GeoHierarchyController.edit.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.geo.AllowedInController.parentQuery.mojo" name="dss.vector.solutions.geo.AllowedIn.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.geo.AllowedInController.childQuery.mojo" name="dss.vector.solutions.geo.AllowedIn.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink action="dss.vector.solutions.geo.GeoHierarchyController.viewAll.mojo" name="dss.vector.solutions.geo.GeoHierarchy.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
