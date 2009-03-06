<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.name" id="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.geoEntityClassMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoEntityClass.keyName}" action="com.terraframe.mojo.system.metadata.MdBusinessController.view.mojo" name="com.terraframe.mojo.system.metadata.MdBusiness.form.view.link">
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
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.edit.mojo" name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.edit.button" />
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
        <mjl:commandLink display="" action="csu.mrc.ivcc.mdss.geo.AllowedInController.parentQuery.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.parentQuery.link">
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
        <mjl:commandLink display="" action="csu.mrc.ivcc.mdss.geo.AllowedInController.childQuery.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.viewAll.mojo" name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.viewAll.link" />
