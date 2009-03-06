<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.name" id="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.id" method="POST">
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
  <mjl:command value="Edit" action="csu.mrc.ivcc.mdss.geo.SpatialMasterController.edit.mojo" name="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.edit.button" />
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
        <mjl:commandLink display="" action="com.terraframe.mojo.system.metadata.EnumerationAttributeItemController.childQuery.mojo" name="com.terraframe.mojo.system.metadata.EnumerationAttributeItem.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="csu.mrc.ivcc.mdss.geo.SpatialMasterController.viewAll.mojo" name="csu.mrc.ivcc.mdss.geo.SpatialMaster.viewAll.link" />
