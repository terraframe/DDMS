<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.name" id="csu.mrc.ivcc.mdss.geo.AllowedIn.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        Geo Hierarchy
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.view.mojo" name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Geo Hierarchy
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="csu.mrc.ivcc.mdss.geo.GeoHierarchyController.view.mojo" name="csu.mrc.ivcc.mdss.geo.GeoHierarchy.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.geo.AllowedInController.edit.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.geo.AllowedInController.viewAll.mojo" name="csu.mrc.ivcc.mdss.geo.AllowedIn.viewAll.link" />
