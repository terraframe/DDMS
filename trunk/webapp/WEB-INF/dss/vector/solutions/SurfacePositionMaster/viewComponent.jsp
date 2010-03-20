<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.SurfacePositionMaster.form.name" id="dss.vector.solutions.SurfacePositionMaster.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.displayLabel}
    </dd>
    <dt>
      <label>
        ${item.enumNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enumName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.SurfacePositionMasterController.edit.mojo" name="dss.vector.solutions.SurfacePositionMaster.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="com.runwaysdk.system.metadata.EnumerationAttributeItemController.childQuery.mojo" name="com.runwaysdk.system.metadata.EnumerationAttributeItem.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.SurfacePositionMasterController.viewAll.mojo" name="dss.vector.solutions.SurfacePositionMaster.viewAll.link" />
