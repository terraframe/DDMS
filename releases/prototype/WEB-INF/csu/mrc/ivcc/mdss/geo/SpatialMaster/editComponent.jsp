<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.name" id="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.enumNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="enumName" />
        <mjl:messages attribute="enumName">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="csu.mrc.ivcc.mdss.geo.SpatialMasterController.update.mojo" name="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.update.button" />
  <mjl:command value="Delete" action="csu.mrc.ivcc.mdss.geo.SpatialMasterController.delete.mojo" name="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.delete.button" />
  <mjl:command value="Cancel" action="csu.mrc.ivcc.mdss.geo.SpatialMasterController.cancel.mojo" name="csu.mrc.ivcc.mdss.geo.SpatialMaster.form.cancel.button" />
</mjl:form>
