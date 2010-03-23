<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.ThematicLayer.form.name" id="dss.vector.solutions.query.ThematicLayer.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.geoHierarchyMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoHierarchy.keyName}" action="dss.vector.solutions.geo.GeoHierarchyController.view.mojo" name="dss.vector.solutions.geo.GeoHierarchy.form.view.link">
        <mjl:property value="${item.geoHierarchy.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.geometryStyleMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geometryStyle.keyName}" action="dss.vector.solutions.query.GeometryStyleController.view.mojo" name="dss.vector.solutions.query.GeometryStyle.form.view.link">
        <mjl:property value="${item.geometryStyle.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.sldFileMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sldFile}
    </dd>
    <dt>
      <label>
        ${item.textStyleMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.textStyle.keyName}" action="dss.vector.solutions.query.TextStyleController.view.mojo" name="dss.vector.solutions.query.TextStyle.form.view.link">
        <mjl:property value="${item.textStyle.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.query.ThematicLayerController.edit.mojo" name="dss.vector.solutions.query.ThematicLayer.form.edit.button" />
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
        <mjl:commandLink display="" action="dss.vector.solutions.query.DefinesCategoriesController.parentQuery.mojo" name="dss.vector.solutions.query.DefinesCategories.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.ThematicLayerController.viewAll.mojo" name="dss.vector.solutions.query.ThematicLayer.viewAll.link">
<fmt:message key="View_All" />
</mjl:commandLink>
